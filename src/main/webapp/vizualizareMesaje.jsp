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
    <title>Vizualizare mesaje</title>

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
                                <a  href="${contextPath}/statisticiComenziUltimeleLuni">Comenzi lunare</a>
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
        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6" style="width:800px; margin: 0 auto">
                            <div class="au-card au-card--no-shadow au-card--no-pad m-b-40" style="width:800px; margin: 0 auto">
                                <div class="au-card-title" style="background-image:url('${contextPath}/resources/tema/images/bg-title-02.jpg');">
                                    <div class="bg-overlay bg-overlay--blue"></div>
                                    <h3><i class="zmdi zmdi-comment-text"></i>Mesaje</h3>
                                </div>
                                <div class="au-inbox-wrap js-inbox-wrap">
                                    <div class="au-message js-list-load">
                                        <div class="au-message__noti">
                                            <c:if test="${listaMesajeNecitite.size() eq 1}">
                                                <p>Ai un mesaj nou</p>
                                            </c:if>
                                            <c:if test="${listaMesajeNecitite.size() ne 1}">
                                                <p>Ai ${listaMesajeNecitite.size()} mesaje noi</p>
                                            </c:if>
                                        </div>
                                        <div class="au-message-list">
                                            <a id="aTrimiteRaspuns" type="hidden" href="#"></a>

                                            <c:forEach var="mesajNecitit" items="${listaMesajeNecitite}">

                                            <div class="au-message__item unread" onclick="goToTrimiteRaspuns(${mesajNecitit.id})">
                                                <div class="au-message__item-inner">
                                                    <table>
                                                        <tr>

                                                            <td width="300">
                                                                <div class="au-message__item-text">
                                                                    <div class="text">
                                                                        <h5 style="font-weight: bold" class="name">${mesajNecitit.expeditor}</h5>
                                                                        <p style="color: #0a3c93">Subiect: ${mesajNecitit.subiect}</p>

                                                                        <c:if test="${mesajNecitit.continut.length() gt 21}">
                                                                            <p>${mesajNecitit.continut.substring(0,20)}...</p>
                                                                        </c:if>
                                                                        <c:if test="${mesajNecitit.continut.length() le 21}">
                                                                            <p>${mesajNecitit.continut}</p>
                                                                        </c:if>
                                                                    </div>
                                                                </div>
                                                            </td>

                                                            <td width="150">
                                                                <div class="au-message__item-time">
                                                                    <span>${mesajNecitit.data}</span>
                                                                </div>
                                                            </td>

                                                        </tr>

                                                    </table>


                                                </div>
                                            </div>
                                            </c:forEach>
                                            <c:forEach var="mesajCitit" items="${listaMesajeCitite}">
                                            <div class="au-message__item" onclick="goToTrimiteRaspuns(${mesajCitit.id})">
                                                <div class="au-message__item-inner">
                                                    <table>

                                                        <tr>

                                                            <td width="300">
                                                                <div class="au-message__item-text">
                                                                    <div class="text">
                                                                        <h5 style="font-weight: bold" class="name">${mesajCitit.expeditor}</h5>
                                                                        <p style="color: #0a3c93">Subiect: ${mesajCitit.subiect}</p>
                                                                        <c:if test="${mesajCitit.continut.length() gt 21}">
                                                                            <p>${mesajCitit.continut.substring(0,20)}...</p>
                                                                        </c:if>
                                                                        <c:if test="${mesajCitit.continut.length() le 21}">
                                                                            <p>${mesajCitit.continut}</p>
                                                                        </c:if>
                                                                    </div>
                                                                </div>

                                                            </td>
                                                            <td width="150">
                                                                    <span>${mesajCitit.data}</span>
                                                            </td>
                                                            <td width="250">
                                                                <c:if test="${not empty mesajCitit.continutRaspuns}">
                                                                    <span style="color: red">Ai raspuns la acest mesaj!</span>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            </c:forEach>

                                        <div class="au-message__footer">
                                            <button class="au-btn au-btn-load js-load-btn" id="load-btn">Vezi mai multe</button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                </div>
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
    /*function citesteUrmatoarele() {
        var numar = document.getElementById("numar_urmatoarele").innerHTML;
        var aCitire=document.getElementById("citireUrmatoarele");
        var load_btn =document.getElementById('load-btn');
        load_btn.innerHTML("Incarca...").delay()(1500).queue(function (next) {

            console.log('asta ar trb sa fie nuamrul' +numar)
        var numarTrans= parseInt(numar);
        console.log (' nr transformat ok')
        numarTrans++;
        console.log (' nr transformat ok'+ numarTrans)

        numar.innerHTML=numarTrans;
        aCitire.setAttribute('href','${contextPath}/citesteUrmatoarele/'+numar)

        console.log('${contextPath}/citesteUrmatoarele/'+numar)
        aCitire.click();

        })
    }*/
</script>

</body>

</html>
<!-- end document-->
