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
    <title>Vizualizare categorii</title>
</head>

<body>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <!-- ============================================================== -->
    <!-- navbar -->
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
                            <h2 class="pageheader-title">Vizualizare categorii</h2>
                            <p class="pageheader-text">Nulla euismod urna eros, sit amet scelerisque torton lectus vel mauris facilisis faucibus at enim quis massa lobortis rutrum.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${contextPath}/vizualizareMeniuri" class="breadcrumb-link">Vizualizare meniuri</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Vizualizare categorii</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader  -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
                        <div class="row">

                            <c:if test="${not empty categorii}">
                                <c:forEach var="categorie" items="${categorii}">
                                <div class="col-xl-4 col-lg-6 col-md-12 col-sm-12 col-12">
                                    <div class="product-thumbnail">
                                        <div class="product-img-head" style="margin: 0 auto;" >
                                            <div class="product-img" style="margin: 0 auto; height: 220px">
                                               <img src="${String(categorie.imagine)}" style="margin: 0 auto; max-height:80%; max-width: 80%; object-fit: cover" alt="" class="img-fluid"></div>
                                            <div class=""><a href="${contextPath}/detaliiCategorie/${categorie.id}/${meniu_id}" class="product-wishlist-btn"><i class="fas fa-eye"></i></a></div>
                                        </div>
                                        <div class="product-content" style="margin: 0 auto;height: 260px">
                                            <div class="product-content-head" style="margin: 0 auto; height: 100px">
                                                <h3 class="product-title">${categorie.denumire}</h3>
                                                <div class="product-price">${categorie.vizibilitate.toUpperCase()}</div>
                                            </div>
                                            <div class="product-btn" >
                                                <a href="${contextPath}/detaliiCategorie/${categorie.id}/${meniu_id}" class="btn btn-primary">Vezi detalii</a>
                                                <a href="${contextPath}/administrareCategorie/${categorie.id}/${meniu_id}" class="btn btn-outline-light">Modifica</a>
<%--
                                               <c:if test="${empty categorie.produse}">
--%>
                                                    <a href="${contextPath}/stergeCategorie/${categorie.id}/${meniu_id}" class="btn btn-danger btn-sm">Sterge</a>

<%--
                                                </c:if>
--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                            </c:if>



                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                    </ul>
                                </nav>
                            </div>

                        </div>
                        </div>
                    <div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        <div class="product-sidebar">
                            <div class="product-sidebar-widget">
                                <a href="${contextPath}/administrareCategorie/0/${meniu_id}"class="btn btn-danger btn-sm">Adauga o categorie</a>
                            </div>
                            <div class="product-sidebar-widget">
                                <h4 class="mb-0">Filtrare</h4>
                            </div>
                            <div class="product-sidebar-widget">
                                <h4 class="product-sidebar-widget-title">Vizibilitate</h4>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filtruActiv">
                                    <label class="custom-control-label" for="filtruActiv">Vizibil</label>
                                </div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filtruInactiv">
                                    <label class="custom-control-label" for="filtruInactiv">Invizibil</label>
                                </div>
                                <div class="product-sidebar-widget">
                                    <a href="#" id="aplicaFiltru" onclick="aplicaFiltru(${meniu_id})" class="btn btn-outline-light">Aplica</a>
                                </div>
                            </div>

                            <div class="product-sidebar-widget">
                                <a href="${contextPath}/vizualizareCategorii/${meniu_id}" class="btn btn-outline-light">Sterge filtrul</a>
                            </div>
                        </div>
                    </div>
                    </div>

                </div>
            </div>
            <!-- ============================================================== -->

            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- end wrapper  -->
        <!-- ============================================================== -->
    </div>

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
<script src="${contextPath}/resources/tema/libs/js/main-js.js"></script>

<script>
    function aplicaFiltru(meniu_id) {
        var btnAplica = document.getElementById("aplicaFiltru");
        var checkBoxA= document.getElementById("filtruActiv");
        var checkBoxI= document.getElementById("filtruInactiv");
        if( checkBoxA.checked === true){
            btnAplica.setAttribute('href','${contextPath}/vizualizareCategorii/'+meniu_id+'/vizibil')
            btnAplica.click();
            checkBoxA.checked=true;
        }
        else if (checkBoxI.checked===true){
            btnAplica.setAttribute('href','${contextPath}/vizualizareCategorii/'+meniu_id+'/invizibil')
            btnAplica.click();
            checkBoxI.checked=true;
        }

    }

</script>
</body>

</html>