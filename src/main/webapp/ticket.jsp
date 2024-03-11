<%--
  Created by IntelliJ IDEA.
  User: ZAHIR
  Date: 03/11/2024
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Busway - Votre Ticket</title>
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
    <h2 class="text-center mb-4">Votre Ticket QR Code</h2>

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
        <div class="card" style="width: 18rem;">
            <img id="qrCodeImage" src="data:image/png;base64,${sessionScope.ticket.qrCodeData}" class="card-img-top" alt="Ticket QR Code">
            <div class="card-body">
                <p class="card-text">Scannez ce QR Code à l'entrée du bus. Conservez-le pour toute la durée de votre voyage.</p>
                <a href="#" class="btn btn-primary" onclick="downloadQRCode()">Télécharger le Ticket</a>
            </div>
        </div>
    </div>

</div>

<script>
    function downloadQRCode() {
        var imageData = document.getElementById('qrCodeImage').src;
        var byteString = atob(imageData.split(',')[1]);
        var mimeType = "image/png"; // Ou "image/jpeg"
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        var blob = new Blob([ab], {type: mimeType});

        var link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = "Ticket-QRCode.png";
        link.click();
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
