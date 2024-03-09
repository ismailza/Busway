<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Busway | Gesion des bus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <%@ include file="inc/navbar.jsp" %>

</header>
<div class="container">
    <h1 class="mt-4">
        <h1 class="mt-4">${voyage != null && voyage.id != null ? "Modifier le voyage" : "Ajouter un voyage"}</h1>
    </h1>
    <div class="row mt-4 d-flex justify-content-center">
        <div class="col-md-6">
            <form action="${voyage != null && voyage.id != null ? 'updateVoyage' : 'saveVoyage'}" method="post" class="form-horizontal">
                <input type="hidden" name="id" value="${voyage != null ? voyage.id : ''}"/>
                <div class="row g-3">
                    <div class="mt-3 col-md-8">
                        <label for="bus" class="form-label">Le bus</label>
                        <select id="bus" class="form-select" name="bus" required>
                            <option selected disabled>Sélectionner un bus</option>
                            <c:forEach items="${buses}" var="bus">
                                <option value="${bus.id}">${bus.numBus} - ${bus.placesLimite} : ${bus.conducteur.nom} ${bus.conducteur.prenom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="tarif" class="form-label">Tarif du voyage</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="tarif" name="tarif" placeholder="Tarif" aria-describedby="basic-addon" required>
                            <span class="input-group-text" id="basic-addon">DH</span>
                        </div>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col-md-8 mt-3">
                        <label for="stationDepart" class="form-label">Station de départ</label>
                        <select id="stationDepart" class="form-select" name="stationDepart" required>
                            <option disabled selected>Sélectionner une station</option>
                            <c:forEach items="${stations}" var="station">
                                <option value="${station.id}">${station.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="heureDepart" class="form-label">Heure de départ</label>
                        <input type="time" class="form-control" id="heureDepart" name="heureDepart" required>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="col-md-8 mt-3">
                        <label for="stationArrivee" class="form-label">Station d'arrivée</label>
                        <select id="stationArrivee" class="form-select" name="stationArrivee" required>
                            <option disabled selected>Sélectionner une station</option>
                            <c:forEach items="${stations}" var="station">
                                <option value="${station.id}">${station.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="heureArrivee" class="form-label">Heure d'arrivée</label>
                        <input type="time" class="form-control" id="heureArrivee" name="heureArrivee" required>
                    </div>
                </div>
                <div id="stationsArretContainer">
                    <!-- Les champs des stations d'arrêt seront ajoutés ici -->
                </div>
                <div class="d-flex justify-content-end">
                    <button type="button" id="ajouterStation" class="btn btn-secondary btn-sm mt-3">Ajouter une station d'arrêt</button>
                </div>

                <div class="mt-2 float-end">
                    <a href="${pageContext.request.contextPath}/voyages" type="button" class="btn btn-default">Annuler</a>
                    <button type="submit" class="btn btn-primary">${voyage != null && voyage.id != null ? "Modifier" : "Ajouter"}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    var i = 1;
    const addStopStationField = () => {
        let div = document.createElement("div");
        div.classList.add("input-group", "mt-2");

        let input = document.createElement("input");
        input.type = "text";
        input.classList.add("form-control");
        input.name = "stationsArret[" + i + "][nom]";
        input.placeholder = "Nom de la station d'arrêt";
        div.append(input);

        let timeField = document.createElement("input");
        timeField.type = "time";
        timeField.classList.add("form-control", "ms-2");
        timeField.name = "stationsArret[" + i + "][heure]";
        div.append(timeField);

        let button = document.createElement("button");
        button.type = "button";
        button.innerText = "X";
        button.classList.add("btn", "btn-danger", "ms-2");
        button.onclick = removeStopStation;
        div.append(button);

        document.querySelector('#stationsArretContainer').append(div);
        i++;
    };

    const removeStopStation = (e) => {
        e.target.parentNode.remove();
    };

    document.addEventListener('DOMContentLoaded', function() {
        document.querySelector('#ajouterStation').addEventListener('click', addStopStationField);
    });
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
