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
        <div class="row mt-4 d-flex justify-content-center">
            <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/search" style="max-width: 800px;">
                <div class="row g-3">
                    <div class="col-md-6 mt-3">
                        <label for="stationDepart" class="form-label">Station de départ</label>
                        <select id="stationDepart" class="form-select" name="depart" required>
                            <option disabled selected>Sélectionner une station</option>
                            <c:forEach items="${stations}" var="station">
                                <option value="${station.id}">${station.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 mt-3">
                        <label for="stationArrivee" class="form-label">Station d'arrivée</label>
                        <select id="stationArrivee" class="form-select" name="arrivee" required>
                            <option disabled selected>Sélectionner une station</option>
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
                                <button type="button" class="btn btn-primary btn-sm">Reserver</button>
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
</body>
</html>