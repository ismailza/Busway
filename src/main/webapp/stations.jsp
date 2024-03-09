<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Busway | Gestion des stations</title>
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
    <a href="${pageContext.request.contextPath}/newStation" class="btn btn-primary my-4 float-end">Ajouter une station</a>
    <table class="table table-striped table-bordered my-4">
        <thead>
        <th>ID</th>
        <th>Nom station</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach items="${stations}" var="station">
            <tr>
                <td>${station.id}</td>
                <td>${station.nom}</td>
                <td class="col-2 text-end">
                    <a href="${pageContext.request.contextPath}/editStation?id=${station.id}" class="btn btn-sm btn-warning">Modifier</a>
                    <form action="${pageContext.request.contextPath}/deleteStation" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${station.id}" />
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette station ?');">Supprimer</button>
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