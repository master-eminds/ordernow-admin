<!DOCTYPE html>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
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
    <title>Vizualizare sugestii</title>

    <!-- Fontfaces CSS-->
    <link href="${contextPath}/resources/tema/css/font-face.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="${contextPath}/resources/tema/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
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

    <link rel="stylesheet" href="${contextPath}/resources/tema/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${contextPath}/resources/tema/assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/tema/assets/libs/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/assets/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">

</head>

<body class="animsition">
<div class="page-wrapper">
    <!-- HEADER MOBILE-->
    <header class="header-mobile d-block d-lg-none">
        <div class="header-mobile__bar">
            <div class="container-fluid">
                <div class="header-mobile-inner">
                    <a href="/welcome">
                        <img src="${contextPath}/resources/tema/images/icon/logo_administrare2C.png" alt="Logo" style="max-width: 20%;max-height: 20%" />
                    </a>
                    <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                    </button>
                </div>
            </div>
        </div>
        <nav class="navbar-mobile">
            <div class="container-fluid">
                <ul class="navbar-mobile__list list-unstyled">
                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-tachometer-alt"></i>Gestionare meniuri</a>
                        <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                            <li>
                                <a href="${contextPath}/vizualizareMeniuri">Vizualizare meniuri</a>
                            </li>
                            <li>
                                <a href="${contextPath}/administrareMeniu/0">Adaugare meniu</a>
                            </li>

                        </ul>
                    </li>
                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-chart-bar"></i>Statistici</a>
                        <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                            <li>
                                <a href="${contextPath}/statisticiComenziUltimaSaptamana">Comenzi ultima saptamana</a>
                            </li>
                            <li>
                                <a href="${contextPath}/statisticiComenziUltimeleLuni">Comenzi lunare</a>
                            </li>
                            <li>
                                <a href="${contextPath}/statisticiComenziTotal">Total comenzi</a>
                            </li>
                            <li>
                                <a href="${contextPath}/statisticiReviewProduse">Evaluari produse</a>
                            </li>
                            <li>
                                <a href="${contextPath}/statisticiReviewOspatari">Evaluari ospatari</a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
    <!-- END HEADER MOBILE-->

    <!-- MENU SIDEBAR-->

    <aside class="menu-sidebar d-none d-lg-block">
        <div class="logo">
                <a href="/welcome">
                    <img src="${contextPath}/resources/tema/images/icon/logo_administrare2C.png" alt="Logo" />
                </a>
        </div>
        <div class="menu-sidebar__content js-scrollbar1">
            <nav class="navbar-sidebar">
                <ul class="list-unstyled navbar__list">
                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-tachometer-alt"></i>Gestionare meniuri</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li >
                                <a href="/vizualizareMeniuri" >Vizualizare meniuri</a>
                            </li>
                            <li >
                                <a  href="/administrareMeniu/0">Adaugare meniu</a>
                            </li>
                        </ul>
                    </li>


                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-copy"></i>Statistici</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li >
                                <a  href="/statisticiComenziUltimaSaptamana">Ultima saptamana</a>
                            </li>
                            <li >
                                <a  href="${contextPath}/statisticiComenziUltimeleLuni">Comenzi  lunare</a>
                            </li>
                            <li >
                                <a href="${contextPath}/statisticiComenziTotal">Total comenzi</a>
                            </li>
                            <li >
                                <a  href="${contextPath}/statisticiReviewProduse">Evaluari produse</a>
                            </li>
                            <li >
                                <a href="${contextPath}/statisticiReviewOspatari">Evaluari ospatari</a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </nav>
        </div>
    </aside>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">

                    <div class="main-content container-fluid p-0">
                        <div class="email-inbox-header">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="email-title">
                        <span class="icon"><i class="fas fa-inbox"></i>
                        </span> Sugestii
                                        <c:if test="${not empty listaSugestiiNoi}">
                                            <span class="new-messages">(${counterSugestiiNoi} sugestii necitite)</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="email-filters">
                            <div class="email-filters-left">
                                <label class="custom-control custom-checkbox be-select-all">
                                    <input class="custom-control-input chk_all" type="checkbox" name="chk_all"><span class="custom-control-label"></span>
                                </label>
                                <div class="btn-group">
                                    <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">
                                        Sugestii selectate <span class="caret"></span></button>
                                    <div class="dropdown-menu" role="menu">
                                        <a id="mark-citite" class="dropdown-item" href="#" onclick="marcheazaCaCitite()">Marcheaza ca citit</a>
                                        <a id="mark-necitite" class="dropdown-item" href="#" onclick="marcheazaCaNecitite()">Marcheaza ca necitit</a>
                                    </div>
                                </div>
                                <div class="btn-group">
                                    <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">
                                        Stare <span class="caret"></span></button>
                                    <div class="dropdown-menu" role="menu">
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii">Toate</a>
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii/stare/citit">Citite</a>
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii/stare/necitit">Necitite</a>
                                    </div>
                                </div>

                                <div class="btn-group">
                                    <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">Categorii <span class="caret"></span></button>
                                    <div class="dropdown-menu dropdown-menu-right" role="menu">
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii">Toate</a>
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii/produse">Produse</a>
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii/aplicatie">Aplicatie</a>
                                        <a class="dropdown-item" href="${contextPath}/vizualizareSugestii/altceva">Altceva</a>
                                    </div>
                                </div>
                            </div>
                            <div class="email-filters-right"><span class="email-pagination-indicator">1-${numarSugestii} din 50</span>
                                <div class="btn-group email-pagination-nav">
                                    <button class="btn btn-light" type="button"><i class="fas fa-angle-left"></i></button>
                                    <button class="btn btn-light" type="button"><i class="fas fa-angle-right"></i></button>
                                </div>
                            </div>

                        </div>
                        <div class="email-list">
                            <c:forEach var="sugestie" items="${listaSugestiiNoi}">

                                <div class="email-list-item email-list-item--unread">
                                    <div class="email-list-actions">
                                        <label class="custom-control custom-checkbox">
                                            <input class="custom-control-input checkboxes" type="checkbox" value="${sugestie.id}" id="${sugestie.id}">
                                            <span class="custom-control-label"></span>
                                        </label>
                                    </div>
                                    <div class="email-list-detail"><span class="date float-right"><span class="icon">
                </span>${sugestie.data}</span><span class="from">${sugestie.categorie.toUpperCase()}</span>
                                        <p class="msg">${sugestie.continut}</p>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:forEach var="sugestie" items="${listaSugestiiVechi}">

                                <div class="email-list-item">
                                    <div class="email-list-actions">
                                        <label class="custom-control custom-checkbox">
                                            <input class="custom-control-input checkboxes" type="checkbox" value="${sugestie.id}" id="${sugestie.id}">
                                            <span class="custom-control-label"></span>
                                        </label>
                                    </div>
                                    <div class="email-list-detail">
                                        <span class="date float-right">${sugestie.data}</span>
                                        <span class="from">${sugestie.categorie.toUpperCase()}</span>
                                        <p class="msg">${sugestie.continut}</p>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>


                </div>
            </div>
            </div>
        </div>
    </div>
<!-- Jquery JS-->
<script src="${contextPath}/resources/tema/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="${contextPath}/resources/tema/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<script src="${contextPath}/resources/tema/assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${contextPath}/resources/tema/assets/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="${contextPath}/resources/tema/assets/libs/js/main-js.js"></script>

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
<script>
    function goToTrimiteRaspuns(id) {
        var link = document.getElementById("aTrimiteRaspuns");
        link.setAttribute('href','${contextPath}/raspunsMesaj/'+id)
      link.click();
    }

    $(document).ready(function() {

        // binding the check all box to onClick event
        $(".chk_all").click(function() {

            var checkAll = $(".chk_all").prop('checked');
            if (checkAll) {
                $(".checkboxes").prop("checked", true);
            } else {
                $(".checkboxes").prop("checked", false);
            }

        });

        // if all checkboxes are selected, then checked the main checkbox class and vise versa
        $(".checkboxes").click(function() {

            if ($(".checkboxes").length == $(".subscheked:checked").length) {
                $(".chk_all").attr("checked", "checked");
            } else {
                $(".chk_all").removeAttr("checked");
            }

        });

    });


    function get_selected_checkboxes_array(){
        var array = []
        var checkboxes = document.querySelectorAll('input[type=checkbox].checkboxes:checked')

        for (var i = 0; i < checkboxes.length; i++) {
            array.push(checkboxes[i].value)
        }
        return array; }

    function marcheazaCaCitite() {

        var array = [];
        var selected = get_selected_checkboxes_array();
        var link=document.getElementById('mark-citite');
        console.log('a ajuns aici');

        var idSel='';
        for (var i = 0; i < selected.length-1; i++) {
            idSel+=selected[i] +',';
        }
        idSel+= selected[selected.length-1];
        if(selected.length !=0){
            link.setAttribute('href', '/marcheazaCaCitite/'+ idSel);
        }
    }
    function marcheazaCaNecitite() {
        var selected = get_selected_checkboxes_array();

        var link=document.getElementById('mark-necitite');

        var idSel='';
        for (var i = 0; i < selected.length-1; i++) {
            idSel+=selected[i] +',';
        }
        idSel+= selected[selected.length-1];
        if(selected.length !=0){
            link.setAttribute('href', '/marcheazaCaNecitite/'+ idSel);
        }
    }
</script>

</body>

</html>
<!-- end document-->
