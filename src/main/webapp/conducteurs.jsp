<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Busway | Gestion des conducteurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <%@ include file="inc/navbar.jsp" %>

</header>
<div class="container mt-5">
    <h2>Les conducteurs</h2>
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
    <a href="${pageContext.request.contextPath}/newConducteur" class="btn btn-primary my-4 float-end">Ajouter un conducteur</a>
    <table class="table table-striped table-bordered my-4">
        <thead>
        <th>ID</th>
        <th>NOM</th>
        <th>Prénom</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${conducteurs}" var="conducteur">
            <tr>
                <td>${conducteur.id}</td>
                <td>${conducteur.nom}</td>
                <td>${conducteur.prenom}</td>
                <td class="col-2 text-end">
                    <a href="${pageContext.request.contextPath}/editConducteur?id=${conducteur.id}" class="btn btn-sm btn-warning">Modifier</a>
                    <form action="${pageContext.request.contextPath}/deleteConducteur" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${conducteur.id}" />
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce conducteur ?');">Supprimer</button>
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