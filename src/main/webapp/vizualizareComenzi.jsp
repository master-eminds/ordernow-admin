<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${contextPath}/resources/tema/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/tema/libs/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <title>Vizualizare comenzi masa</title>
</head>

<body>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <!-- ============================================================== -->
    <!-- navbar -->
    <!-- ============================================================== -->
    <div class="dashboard-header">
        <nav class="navbar navbar-expand-lg bg-white fixed-top">
            <a class="navbar-brand" href="/welcome">Pagina principala</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </nav>
    </div>
    <!-- ============================================================== -->
    <!-- end navbar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- left sidebar -->
    <!-- ============================================================== -->
    <div class="nav-left-sidebar sidebar-dark">
        <div class="menu-list">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="d-xl-none d-lg-none" href="#">Meniu</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav flex-column">
                        <li class="nav-divider">
                            Optiuni
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link active" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-1" aria-controls="submenu-1"><i class="fa fa-fw fa-user-circle"></i>Gestionare meniuri <span class="badge badge-success">6</span></a>
                            <div id="submenu-1" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/vizualizareMeniuri" >Vizualizare meniuri</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/administrareMeniu/0">Adaugare meniu</a>
                                    </li>


                                </ul>
                            </div>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link active" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-2" aria-controls="submenu-2"><i class="fa fa-fw fa-user-circle"></i>Statistici<span class="badge badge-success">6</span></a>
                            <div id="submenu-2" class="collapse submenu" style="">
                                <ul class="nav flex-column">


                                            <ul class="nav flex-column">
                                                <li class="nav-item">
                                                    <a class="nav-link" href="/statisticiComenziUltimaSaptamana">Ultima saptamana</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="${contextPath}/statisticiComenziUltimeleLuni">Comenzi lunare</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="${contextPath}/statisticiComenziTotal">Total comenzi</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="${contextPath}/statisticiReviewProduse">Evaluari produse</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="${contextPath}/statisticiReviewOspatari">Evaluari ospatari</a>
                                                </li>

                                            </ul>
                                    </li>
                                </ul>
                            </div>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- end left sidebar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- wrapper  -->
    <!-- ============================================================== -->
    <div class="dashboard-wrapper">
        <div class="dashboard-ecommerce">
            <div class="container-fluid dashboard-content ">
                <!-- ============================================================== -->
                <!-- pageheader  -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title">Vizualizare comenzi masa</h2>
                            <p class="pageheader-text">Nulla euismod urna eros, sit amet scelerisque torton lectus vel mauris facilisis faucibus at enim quis massa lobortis rutrum.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${contextPath}/welcome" class="breadcrumb-link">Pagina principala</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Vizualizare comenzi masa</li>  </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader  -->
                <!-- ============================================================== -->



                <!-- hoverable table -->
                <!-- ============================================================== -->
                <div style="max-width: 85%; max-height: 85%"
                        class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">

                    <c:if test="${not empty listaComenzi}">
                        <div class="card">
                            <h5 class="card-header">Vizualizare comenzi</h5>
                            <div class="card-body">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th scope="col">Numar comanda</th>
                                        <th scope="col">Data</th>
                                        <th scope="col">Ospatar</th>
                                        <th scope="col">Valoare</th>
                                        <th scope="col">Vizualizare produse</th>

                                    </tr>
                                    <tbody>
                                    <c:forEach var="comanda" items="${listaComenzi}">
                                        <tr>
                                            <td>${comanda.id}</td>
                                            <td>${comanda.data}</td>
                                            <td>${comanda.ospatar.nume}</td>
                                            <td>${comanda.valoare} lei</td>
                                            <td>
                                                <a href="/vizualizareProduse/${comanda.id}"><i class="fas fa-eye"></i></a>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </c:if>
                </div>
                <!-- ============================================================== -->
                <!-- end hoverable table -->
                <!-- ============================================================== -->




            </div>

        </div>
    </div>
    <!-- ============================================================== -->
   <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- end wrapper  -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- end main wrapper  -->
<!-- ============================================================== -->
<!-- Optional JavaScript -->
<!-- jquery 3.3.1 -->
<script src="${contextPath}/resources/tema/vendor/jquery/jquery-3.3.1.min.js"></script>
<!-- bootstap bundle js -->
<script src="${contextPath}/resources/tema/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<!-- slimscroll js -->
<script src="${contextPath}/resources/tema/vendor/slimscroll/jquery.slimscroll.js"></script>
<!-- main js -->
<script src="${contextPath}/resources/tema/libs/js/main-js.js"></script>

</body>

</html>