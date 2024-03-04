<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Busway | Les voyages</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>

</header>
<div class="container mt-5">
    <h2>Les voyages</h2>
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

    <table class="table table-striped table-bordered my-4">
        <thead>
        <th>Station départ</th>
        <th>Heure départ</th>
        <th>Station arrivee</th>
        <th>Heure arrivee</th>
        <th>Tarif</th>
        </thead>
        <tbody>
        <c:forEach items="${voyages}" var="voyage">
            <tr>
                <td>${voyage.depart.nom}</td>
                <td>${voyage.heureDepart}</td>
                <td>${voyage.arrivee.nom}</td>
                <td>${voyage.heureArrivee}</td>
                <td>${voyage.tarif} DH</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>