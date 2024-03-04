<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Busway</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1>Installer informations du bus</h1>
    <form class="needs-validation mx-auto mt-4" style="max-width: 800px;" action="${pageContext.request.contextPath}/install" method="POST">

        <h3>Bus</h3>
        <div class="row g-3">
            <div class="col-md-6">
                <label for="numBus" class="form-label">Numéro du bus</label>
                <input type="number" min="1" class="form-control" id="numBus" name="numBus" placeholder="Numéro du bus" required>
            </div>
            <div class="col-md-6">
                <label for="placesLimite" class="form-label">Nombre de places limite</label>
                <input type="number" class="form-control" id="placesLimite" name="placesLimite" placeholder="Nombre de places limite" required>
            </div>
        </div>

        <h3 class="mt-4">Conducteur</h3>
        <div class="row g-3">
            <div class="col-md-6">
                <label for="prenomConducteur" class="form-label">Prénom du conducteur</label>
                <input type="text" class="form-control" id="prenomConducteur" name="prenomConducteur" placeholder="Prénom du conducteur" required>
            </div>
            <div class="col-md-6">
                <label for="nomConducteur" class="form-label">Nom du conducteur</label>
                <input type="text" class="form-control" id="nomConducteur" name="nomConducteur" placeholder="Nom du conducteur" required>
            </div>
        </div>

        <h3 class="mt-4">Voyage</h3>
        <div class="row g-3">
            <div class="col-md-4">
                <label for="tarif" class="form-label">Tarif du voyage</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="tarif" name="tarif" placeholder="Tarif" aria-describedby="basic-addon" required>
                    <span class="input-group-text" id="basic-addon">DH</span>
                </div>
            </div>
            <div class="col-md-4">
                <label for="heureDepart" class="form-label">Heure de départ</label>
                <input type="time" class="form-control" id="heureDepart" name="heureDepart" required>
            </div>
            <div class="col-md-4">
                <label for="heureArrivee" class="form-label">Heure d'arrivée</label>
                <input type="time" class="form-control" id="heureArrivee" name="heureArrivee" required>
            </div>
            <div class="col-md-6 mt-3">
                <label for="stationDepart" class="form-label">Station de départ</label>
                <select id="stationDepart" class="form-select" name="stationDepart" required>
                    <option disabled selected>Sélectionner une station</option>
                    <c:forEach items="${stations}" var="station">
                        <option value="${station.nom}">${station.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6 mt-3">
                <label for="stationArrivee" class="form-label">Station d'arrivée</label>
                <select id="stationArrivee" class="form-select" name="stationArrivee" required>
                    <option disabled selected>Sélectionner une station</option>
                    <c:forEach items="${stations}" var="station">
                        <option value="${station.nom}">${station.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <h5 class="mt-4">Stations d'arrêt</h5>
            <div id="stationsArretContainer">
                <!-- Les champs des stations d'arrêt seront ajoutés ici -->
            </div>
            <div class="d-flex justify-content-end">
                <button type="button" id="ajouterStation" class="btn btn-secondary btn-sm mt-3">Ajouter une station d'arrêt</button>
            </div>
        </div>
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">Installer</button>
        </div>
    </form>
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
