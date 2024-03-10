<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Busway</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h2 class="text-center mb-4">Trouvez votre voyage ici</h2>
        <div class="row mt-4 d-flex justify-content-center">
            <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/search" style="max-width: 800px;">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="depart" class="form-label">Station Départ</label>
                        <input type="text" class="form-control" id="depart" name="depart" placeholder="Station de départ" required>
                        <div class="invalid-feedback">
                            Veuillez entrer une station de départ.
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="arrivee" class="form-label">Station Arrivée</label>
                        <input type="text" class="form-control" id="arrivee" name="arrivee" placeholder="Station d'arrivée" required>
                        <div class="invalid-feedback">
                            Veuillez entrer une station d'arrivée.
                        </div>
                    </div>
                    <div class="col-12 text-center">
                        <button class="btn btn-primary" type="submit">Recherche</button>
                    </div>
                </div>
            </form>
        </div>
        <c:if test="${not empty danger}">
            <div class="alert alert-danger" role="alert">
                    ${danger}
            </div>
        </c:if>
        <c:if test="${not empty results}">
            <div class="mt-5">
                <h3 class="text-center mb-4">Résultats de recherche</h3>
                <div class="card-container">
                    <c:forEach items="${results}" var="result">
                        <div class="row mb-3 border border-2 p-3 rounded shadow bg-light text-center">
                            <div class="col-md-3">
                                <h5>${result.depart.nom} - ${result.heureDepart}</h5>
                            </div>
                            <div class="col-md-6">
                                ---> <br>
                                <h6>Bus n°${result.bus.numBus}</h6>
                                <h6>${result.tarif} DH</h6>
                                <button type="button" class="btn btn-primary btn-sm">Reserver</button>
                            </div>
                            <div class="col-md-3">
                                <h5>${result.arrivee.nom} - ${result.heureArrivee}</h5>
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
