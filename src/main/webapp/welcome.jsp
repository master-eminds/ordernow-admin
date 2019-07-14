<!DOCTYPE html>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Pagina principala</title>

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
                                <a href="${contextPath}/">Comenzi ultima saptamana</a>
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
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <aside class="menu-sidebar d-none d-lg-block">
        <c:if test="${not empty user}">
            <div style="    max-width: 46%;margin-left: 23%;">
                <a href="/welcome">
                    <img src="${contextPath}/resources/tema/images/icon/logo_administrare2C.png" alt="Logo" />
                </a>
            </div>
        </c:if>
        <div class="menu-sidebar__content js-scrollbar1">
            <nav class="navbar-sidebar">
                <ul class="list-unstyled navbar__list">
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
                            <li >
                                <a  href="${contextPath}/statisticiReviewOspatari">Evaluari ospatari</a>
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
        <!-- HEADER DESKTOP-->
        <header class="header-desktop">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="header-wrap">

                         <c:if test="${master eq 1}">
                             <div class="row">
                                 <div class="col-md-12">
                                     <div class="overview-wrap">
                                         <a class="btn btn-primary" href="${contextPath}/registration/${0}">Adauga admin</a>

                                     </div>
                                 </div>
                             </div>
                         </c:if>
                        <div class="header-button">
                            <div class="noti-wrap">

                                <div class="noti__item js-item-menu">
                                    <i class="zmdi zmdi-comment-more"></i>
                                   <c:if test="${counterMesajeNecitite ne 0}">
                                       <span class="quantity">${counterMesajeNecitite} </span>
                                   </c:if>
                                    <div class="mess-dropdown js-dropdown">
                                        <div class="mess__title">
                                            <c:if test="${counterMesajeNecitite eq 1}">
                                                <p>Ai un mesaj nou</p>
                                            </c:if>
                                            <c:if test="${counterMesajeNecitite gt 1}">
                                                <p>Ai ${counterMesajeNecitite} mesaje noi</p>
                                            </c:if>
                                        </div>
                                        <a id="aTrimiteRaspuns" type="hidden" href="#"></a>

                                        <c:forEach var="mesaj" items="${listaMesajeNecitite}">
                                            <div class="mess__item" onclick="goToTrimiteRaspuns(${mesaj.id})">

                                                <div class="content">
                                                    <h5>${mesaj.expeditor}</h5>
                                                    <h6>Subiect: ${mesaj.subiect}</h6>
                                                    <c:if test="${mesaj.continut.length() gt 21}">
                                                        <p>${mesaj.continut.substring(0,20)}...</p>
                                                    </c:if>
                                                    <c:if test="${mesaj.continut.length() le 21}">
                                                        <p>${mesaj.continut}</p>
                                                    </c:if>
                                                    <span class="time">${mesaj.data}</span>
                                                </div>
                                            </div>

                                        </c:forEach>


                                        <div class="mess__footer">
                                            <a href="/vizualizareMesaje">Vezi toate mesajele</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="noti__item js-item-menu">
                                    <i class="zmdi zmdi-notifications"></i>
                                    <c:if test="${counterSugestiiNecitite ne 0}">
                                        <span class="quantity">${counterSugestiiNecitite}</span>

                                    </c:if>
                                    <div class="notifi-dropdown js-dropdown">

                                        <div class="notifi__title">
                                            <p>Ai ${counterSugestiiNecitite} sugestii necitite de la clienti</p>
                                        </div>
                                        <c:forEach var="sugestie" items="${listaSugestiiNoi}">
                                            <div class="mess__item" >

                                                <div class="content">
                                                    <h5>${sugestie.categorie.toUpperCase()}</h5>
                                                    <c:if test="${sugestie.continut.length() gt 21}">
                                                        <p>${sugestie.continut.substring(0,20)}...</p>
                                                    </c:if>
                                                    <c:if test="${sugestie.continut.length() le 21}">
                                                        <p>${sugestie.continut}</p>
                                                    </c:if>
                                                    <span class="time">${sugestie.data}</span>
                                                </div>
                                            </div>

                                        </c:forEach>
                                        <div class="notifi__footer">
                                            <a href="vizualizareSugestii">Toate sugestiile</a>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                         <c:if test="${not empty user}">
                            <div class="account-wrap">
                                <div class="account-item clearfix js-item-menu">

                                    <div class="content">
                                        <a class="js-acc-btn" href="#">${user.nume} ${user.prenume}</a>
                                    </div>
                                    <div class="account-dropdown js-dropdown">
                                        <div class="info clearfix">

                                            <div class="content">
                                                <h5 class="name">
                                                    <a href="#">${user.nume} ${user.prenume}</a>
                                                </h5>
                                                <span class="email">${pageContext.request.userPrincipal.name}</span>
                                            </div>
                                        </div>
                                        <div class="account-dropdown__body">

                                            <div class="account-dropdown__item">
                                                <a href="${contextPath}/registration/${user.id}">
                                                    <i class="zmdi zmdi-settings"></i>Setari</a>
                                            </div>
                                        </div>
                                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </form>
                                        <div class="account-dropdown__footer">
                                            <a id="logout" onclick="document.forms['logoutForm'].submit()">
                                                <i class="zmdi zmdi-power"></i>Logout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                         </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- HEADER DESKTOP-->

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">


                    <div class="row m-t-25">
                        <div class="col-sm-6 col-lg-3">
                            <div class="overview-item overview-item--c4" onclick="goToStatisticiOspatari('statisticiReviewOspatari')">
                                <a type="hidden" href="#" id="aStatisticiO"></a>
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="zmdi zmdi-account-o"></i>
                                        </div>
                                        <div class="text">
                                            <h2>${membriOnline}</h2>
                                            <span>Ospatari online</span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-lg-3">
                            <div class="overview-item overview-item--c2" onclick="goToStatisticiLunare('statisticiComenziUltimeleLuni/4')">
                                <a type="hidden" href="#" id="aStatisticiL"></a>
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="zmdi zmdi-shopping-cart"></i>
                                        </div>
                                        <div class="text">
                                            <h2>${comenziUltimeleLuni}</h2>
                                        </div>
                                        <div class="text">
                                            <span>Numar comenzi ultimele 4 luni</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c3" onclick="goToStatisticiSaptamanale('statisticiComenziUltimaSaptamana')">
                                    <a type="hidden" href="#" id="aStatisticiS"></a>

                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-calendar-note"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${counterThisWeek}</h2>
                                            </div>
                                            <div class="text">
                                                <span>Numar comenzi din ultima saptamana</span>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c1" onclick="goToStatisticiTotale('statisticiComenziTotal')">
                                    <a type="hidden" href="#" id="aStatisticiT"></a>

                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-money"></i>
                                            </div>
                                            <div class="text">
                                                <h2>${incasari} lei</h2>
                                            </div>
                                            <div class="text">
                                                <span>Valoare totala comenzi</span>
                                            </div>
                                        </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                         <c:if test="${not empty listaMese}">
                            <div class="col-lg-12">
                            <h2 class="title-1 m-b-25">Lista Mese Restaurant</h2>
                            <div class="au-card au-card--bg-blue au-card-top-countries m-b-40">
                                <div class="au-card-inner">
                                    <div class="table-responsive">
                                        <table class="table table-top-countries" style="background-color: #3873ff">
                                            <thead>
                                            <tr>
                                                <th style="color:white;">Numar Masa</th>
                                                <th style="color:white;">Numar Comenzi</th>
                                                <th style="color:white;">Vizualizare comenzi</th>
                                            </tr>
                                            <tbody>
                                            <c:forEach var="masa" items="${listaMese}">
                                                <c:if test="${not empty masa.comenzi}">
                                                <tr>
                                                    <td>${masa.id}</td>
                                                    <td>${masa.comenzi.size()}</td>
                                                    <td>
                                                        <a href="/vizualizareComenzi/${masa.id}"><i class="fas fa-eye" style="color: whitesmoke"></i></a>
                                                    </td>
                                                </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                         </c:if>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                    <div class="bg-overlay bg-overlay--blue"></div>
                                    <h3>
                                        <i class="zmdi zmdi-account-calendar"></i>${data}</h3>
                                    <button class="au-btn-plus">
                                        <a href="/administrareOspatari/${0}"><i class="zmdi zmdi-plus"></i></a>
                                    </button>
                                </div>

                             <c:if test="${not empty listaOspatari}">
                                <div class="au-task js-list-load">
                                    <div class="au-task__title">
                                        <p>Lista Ospatari</p>
                                    </div>

                                    <div class="au-task-list js-scrollbar3">
                                        <div class="au-task__item au-task__item--danger">
                                            <c:forEach var="ospatar" items="${listaOspatari}">
                                            <div class="au-task__item-inner">
                                                <table>
                                                <tr>
                                                    <h5 class="task">
                                                        <a href="#">${ospatar.nume}</a>
                                                    </h5>
                                                </tr>
                                               <td>
                                                   <td width="200">
                                                       <span class="time">${ospatar.email}</span>
                                                   </td>
                                                   <td width="120">
                                                       <c:if test="${ospatar.status == 'online' }">
                                                           <span class="role member">Conectat</span>
                                                       </c:if>
                                                       <c:if test="${ospatar.status != 'online' }">
                                                           <span class="role admin">Deconectat</span>
                                                       </c:if>
                                                   </td>
                                                   <td width="120">
                                                       <a href="/administrareOspatari/${ospatar.id}"><span class="role user">EDITARE</span></a>
                                                   </td>

                                                  <td width="120">

                                                      <a href="/stergeOspatar/${ospatar.id}"  type="button" class="btn btn-danger btn-sm">
                                                          <i class="fa fa-eraser"></i>&nbsp;Sterge</a>
<%----%>
                                                   </td>
                                               </tr>
                                                </table>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                             </c:if>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                    <div class="bg-overlay bg-overlay--blue"></div>
                                    <h3>
                                        <i class="zmdi zmdi-account-calendar"></i>${data}</h3>

                                    <form id="adaugaMasaForm" method="POST" action="${contextPath}/adaugaMasa">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <button class="au-btn-plus">

                                     <a id="adaugaMasa" onclick="document.forms['adaugaMasaForm'].submit()"><i class="zmdi zmdi-plus"></i></a>
                                    </button>
                                </div>

                                <c:if test="${not empty listaMese}">
                                    <div class="au-task js-list-load">
                                        <div class="au-task__title">
                                            <p>Lista Mese</p>
                                        </div>

                                        <div class="au-task-list js-scrollbar3">
                                            <div class="au-task__item au-task__item--danger">
                                                <c:forEach var="masa" items="${listaMese}">
                                                    <div class="au-task__item-inner">
                                                        <h5 class="task">
                                                            <a href="#">Masa numarul : ${masa.id}</a>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/stergeMasa/${masa.id}" type="button" style="color: whitesmoke; width:30px; height:30px" class="btn btn-danger btn-sm">
                                                                <i class="fa fa-eraser"></i></a>
                                                        </h5>
                                                        <span class="time">Numar comenzi : ${masa.comenzi.size()}</span>


                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        </c:if>
        <!-- END PAGE CONTAINER-->
    </div>

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
<script>
    function goToTrimiteRaspuns(id) {
        var link = document.getElementById("aTrimiteRaspuns");
        link.setAttribute('href','${contextPath}/raspunsMesaj/'+id)
        link.click();
    }
    function goToStatisticiLunare(adresa) {
        var link = document.getElementById("aStatisticiL");
        link.setAttribute('href','${contextPath}/'+adresa)
        link.click();
    }
    function goToStatisticiSaptamanale(adresa) {
        var link = document.getElementById("aStatisticiS");
        link.setAttribute('href','${contextPath}/'+adresa)
        link.click();
    }
    function goToStatisticiTotale(adresa) {
        var link = document.getElementById("aStatisticiT");
        link.setAttribute('href','${contextPath}/'+adresa)
        link.click();
    }
    function goToStatisticiOspatari(adresa) {
        var link = document.getElementById("aStatisticiO");
        link.setAttribute('href','${contextPath}/'+adresa)
        link.click();
    }
</script>
</body>

</html>
<!-- end document-->
