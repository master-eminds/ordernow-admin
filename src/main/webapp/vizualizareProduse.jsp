<!DOCTYPE html>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Vizualizare comanda</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${contextPath}/resources/tema/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/tema/libs/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/fonts/fontawesome/css/fontawesome-all.css">
</head>
<body>

<!-- striped table -->
<!-- ============================================================== -->
<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
<c:if test="${not empty listaProduse}">
    <div class="card">
        <h5 class="card-header">Vizualizare comanda</h5>
        <div class="card-body">
            <table class="table table-striped">

                <thead>
                <tr>
                    <th scope="col">Denumire</th>
                    <th scope="col">Pret</th>
                    <th scope="col">Cantitate</th>
                    <th scope="col" style="color: black">Valoare</th>


                </tr>
                <tbody>
                <c:forEach var="item" items="${listaProduse}">
                    <tr>
                        <td>${item.produs.denumire}</td>
                        <td>${item.produs.pret}</td>
                        <td>${item.cantitate}</td>
                        <td>${item.produs.pret * item.cantitate}</td>

                    </tr>
                </c:forEach>
                <tr>
                    <td>Valoare totala: ${valoareTotala} lei</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

</div>
<!-- ============================================================== -->
<!-- end striped table -->
<!-- ============================================================== -->


<!-- Jquery JS-->
<script src="${contextPath}/resources/tema/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="${contextPath}/resources/tema/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="${contextPath}/resources/tema/vendor/slick/slick.min.js">
</script>
<script src="${contextPath}/resources/tema/vendor/wow/wow.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/animsition/animsition.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
</script>
<script src="${contextPath}/resources/tema/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/counter-up/jquery.counterup.min.js">
</script>
<script src="${contextPath}/resources/tema/vendor/circle-progress/circle-progress.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="${contextPath}/resources/tema/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/select2/select2.min.js">
</script>

<!-- Main JS-->
<script src="${contextPath}/resources/tema/js/main.js"></script>

<!-- Optional JavaScript -->
<script src="${contextPath}/resources/tema/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src=${contextPath}/resources/tema/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${contextPath}/resources/tema/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="${contextPath}/resources/tema/vendor/custom-js/jquery.multi-select.html"></script>
<script src="${contextPath}/resources/tema/libs/js/main-js.js"></script>

</body>

</html>
<!-- end document-->
