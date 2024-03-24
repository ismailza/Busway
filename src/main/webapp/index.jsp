<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Busway</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Busway</a>
        </div>
    </nav>
</header>
<div class="container mt-5">
    <h2 class="text-center mb-4">Trouvez votre voyage ici</h2>
    <c:if test="${not empty sessionScope.success}">
        <div class="alert alert-success" role="alert">
            ${sessionScope.success}
            <% session.removeAttribute("success"); %>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.danger}">
        <div class="alert alert-danger" role="alert">
            ${sessionScope.danger}
            <% session.removeAttribute("danger"); %>
        </div>
    </c:if>
        <div class="row mt-4 d-flex justify-content-right">
        <button> <a href ="chat.jsp">Reserver Automatiquement</a></button
         </div>
    <div class="row mt-4 d-flex justify-content-center">
        <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/search" style="max-width: 800px;">
            <div class="row g-3">
                <div class="">
                    <input type="hidden" id="latitude" name="latitude">
                    <input type="hidden" id="longitude" name="longitude">
                </div>
                <div class="col-md-6 mt-3">
                    <label for="stationArrivee" class="form-label">Station d'arrivée</label>
                    <select id="stationArrivee" class="form-select" name="arrivee" required>
                        <option value="" disabled selected>Sélectionner une station</option>
                        <c:forEach items="${stations}" var="station">
                            <option value="${station.id}">${station.nom}</option>

                        </c:forEach>
                    </select>
                </div>
                <div class="col-12 text-center">
                    <button class="btn btn-primary" type="submit">Recherche</button>
                </div>
            </div>
        </form>
    </div>
    <c:if test="${not empty results}">
        <div class="mt-5">
            <h3 class="text-center mb-4">Résultats de recherche</h3>
            <div class="card-container">
                <c:forEach items="${results}" var="entry">
                    <div class="row mb-3 border border-2 p-3 rounded shadow bg-light text-center">
                        <div class="col-md-3">
                            <h5>${entry.key.depart.nom} - ${entry.key.heureDepart}</h5>
                        </div>
                        <div class="col-md-6">
                            <h4>Departure <span class="text-danger">${entry.value}</span></h4>
                            <h6>Bus n°${entry.key.bus.numBus}</h6>
                            <h6>${entry.key.tarif} DH</h6>
                            <form action="${pageContext.request.contextPath}/reserve" method="post" >
                                <input type="hidden" name="id" value="${entry.key.id}">
                                <input type="hidden" name="depart_id" value="<%= request.getParameter("depart") %>">
                                <input type="hidden" name="arrivee_id" value="<%= request.getParameter("arrivee") %>">
                                <button type="submit" class="btn btn-primary btn-sm">Reserver</button>
                            </form>
                        </div>
                        <div class="col-md-3">
                            <h5>${entry.key.arrivee.nom} - ${entry.key.heureArrivee}</h5>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
// Function to get the current position and set it in hidden input fields
function getPositionAndSetInForm() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            document.getElementById("latitude").value = position.coords.latitude;
            document.getElementById("longitude").value = position.coords.longitude;
        }, function(error) {
            console.error("Error getting current position: ", error);
        });
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

// Call the function to get the current position and set it in the form
getPositionAndSetInForm();

</script>

</body>
</html>
