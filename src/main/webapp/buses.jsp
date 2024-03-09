<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Busway | Gestion des bus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <%@ include file="inc/navbar.jsp" %>

</header>
<div class="container mt-5">
    <h2>Les bus</h2>
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
    <a href="${pageContext.request.contextPath}/newBus" class="btn btn-primary my-4 float-end">Ajouter un bus</a>
    <table class="table table-striped table-bordered my-4">
        <thead>
        <th>ID</th>
        <th>Numero du bus</th>
        <th>Places limite</th>
        <th>Conducteur</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${buses}" var="bus">
            <tr>
                <td>${bus.id}</td>
                <td>${bus.numBus}</td>
                <td>${bus.placesLimite}</td>
                <td>${bus.conducteur.nom} ${bus.conducteur.prenom}</td>
                <td class="col-2 text-end">
                    <a href="${pageContext.request.contextPath}/editBus?id=${bus.id}" class="btn btn-sm btn-warning">Modifier</a>
                    <form action="${pageContext.request.contextPath}/deleteBus" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${bus.id}" />
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce bus ?');">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>