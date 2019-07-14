<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${contextPath}/resources/tema/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/tema/libs/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <!-- Fontfaces CSS-->
    <link href="${contextPath}/resources/tema/css/font-face.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="${contextPath}/resources/tema/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="${contextPath}/resources/tema/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="${contextPath}/resources/tema/css/theme.css" rel="stylesheet" media="all">

    <title>Statistici ospatari</title>
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
                                            <a class="nav-link" href="${contextPath}/statisticiReviewProduse">Raiting produse</a>
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
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title">Statistici ospatari</h2>
                                <p class="pageheader-text">Nulla euismod urna eros, sit amet scelerisque torton lectus vel mauris facilisis faucibus at enim quis massa lobortis rutrum.</p>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="${contextPath}/welcome" class="breadcrumb-link">Pagina principala</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Evaluari ospatari</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->

                </div>
                <div class="row">
                    <div class="col-lg-12" style="margin: 0 auto">
                       <div class="au-card chart-percent-card">
                            <div class="au-card-inner">
                                <h3 class="title-2 tm-b-5">Raiting ospatari</h3>
                                <div class="row no-gutters" style="margin: 0 auto">
                                    <div class="col-xl-6" style="margin: 0 auto">
                                        <div class="au-card-inner">
                                            <p style="font-size: large">Rezultate evaluari</p>
                                            <canvas id="singelBarChart"></canvas>
                                            <input type="hidden" value="${dateChartReview}" id="dateChartReviewOsp"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <!-- hoverable table -->
                    <!-- ============================================================== -->
                    <div style="max-width: 85%; max-height: 85%;margin: 0 auto"
                         class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">

                        <c:if test="${not empty listaOspatari}">
                            <div class="card">
                                <h5 class="card-header">Vizualizare ospatari</h5>
                                <div class="card-body">
                                    <table class="table table-hover">

                                        <thead>
                                        <tr>
                                            <th scope="col">Nume</th>
                                            <th scope="col">Vizualizare review-uri</th>
                                        </tr>
                                        <tbody>

                                        <c:forEach var="ospatar" items="${listaOspatari}">
                                            <c:if test="${noteOspatar.get(ospatar.id)!=null}">

                                                <td>${ospatar.nume}</td>
                                                <td>
                                                    <a href="/vizualizareReviewOspatar/${ospatar.id}"><i class="fas fa-eye"></i></a>
                                                </td>
                                        </c:if>
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
        <!-- footer -->

        <!-- ============================================================== -->
        <!-- end footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- end wrapper  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- end main wrapper  -->
</div>

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

</body>

</html>