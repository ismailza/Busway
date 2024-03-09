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
        <h1 class="mt-4">${bus != null && bus.id != null ? "Modifier le bus" : "Ajouter un bus"}</h1>
    </h1>
    <div class="row mt-4 d-flex justify-content-center">
        <div class="col-md-6">
            <form action="${bus != null && bus.id != null ? 'updateBus' : 'saveBus'}" method="post" class="form-horizontal">
                <input type="hidden" name="id" value="${bus != null ? bus.id : ''}"/>
                <div class="row g-3">
                    <div class="mb-3 col-md-6">
                        <label for="numBus" class="form-label">Numero du Bus</label>
                        <input type="number" min="1" step="1" class="form-control" id="numBus" name="numBus" placeholder="Numero du bus" value="${bus != null ? bus.numBus : ''}" required>
                    </div>
                    <div class="mb-3 col-md-6">
                        <label for="placesLimite" class="form-label">Places limite</label>
                        <input type="number" min="1" step="1" class="form-control" id="placesLimite" name="placesLimite" placeholder="Places limite" value="${bus != null ? bus.placesLimite : ''}" required>
                    </div>
                </div>
                <div class="mt-3">
                    <label for="conducteur" class="form-label">Station de départ</label>
                    <select id="conducteur" class="form-select" name="conducteur" required>
                        <option selected disabled>Sélectionner un conducteur</option>
                        <c:forEach items="${conducteurs}" var="conducteur">
                            <option value="${conducteur.id}">${conducteur.nom} ${conducteur.prenom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mt-2 float-end">
                    <a href="${pageContext.request.contextPath}/buses" type="button" class="btn btn-default">Annuler</a>
                    <button type="submit" class="btn btn-primary">${bus != null && bus.id != null ? "Modifier" : "Ajouter"}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
