<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Busway | Gesion des station</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <%@ include file="inc/navbar.jsp" %>

</header>
<div class="container">
    <h1 class="mt-4">
        <h1 class="mt-4">${station != null && station.id != null ? "Modifier la station" : "Ajouter une station"}</h1>
    </h1>
    <div class="row mt-4 d-flex justify-content-center">
        <div class="col-md-6">
            <form action="${station != null && station.id != null ? 'updateStation' : 'saveStation'}" method="post" class="form-horizontal">
                <input type="hidden" name="id" value="${station != null ? station.id : ''}"/>
                <div class="mb-3 col-md-12">
                    <label for="nom" class="form-label">Nom Station</label>
                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom de la station" value="${station != null ? station.nom : ''}" required>
                </div>
                <div class="mt-2 float-end">
                    <a href="${pageContext.request.contextPath}/stations" type="button" class="btn btn-default">Annuler</a>
                    <button type="submit" class="btn btn-primary">${station != null && station.id != null ? "Modifier" : "Ajouter"}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
