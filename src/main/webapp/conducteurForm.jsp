<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Busway | Gesion des conducteurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <%@ include file="inc/navbar.jsp" %>

</header>
<div class="container">
    <h1 class="mt-4">
        <h1 class="mt-4">${conducteur != null && conducteur.id != null ? "Modifier le conducteur" : "Ajouter un conducteur"}</h1>
    </h1>
    <div class="row mt-4 d-flex justify-content-center">
        <div class="col-md-6">
            <form action="${conducteur != null && conducteur.id != null ? 'updateConducteur' : 'saveConducteur'}" method="post" class="form-horizontal">
                <input type="hidden" name="id" value="${conducteur != null ? conducteur.id : ''}"/>
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="${conducteur != null ? conducteur.nom : ''}" required>
                </div>
                <div class="mb-3">
                    <label for="prenom" class="form-label">Prénom</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" value="${conducteur != null ? conducteur.prenom : ''}" required>
                </div>
                <div class="mt-2 float-end">
                    <a href="${pageContext.request.contextPath}/conducteurs" type="button" class="btn btn-default">Annuler</a>
                    <button type="submit" class="btn btn-primary">${conducteur != null && conducteur.id != null ? "Modifier" : "Ajouter"}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
