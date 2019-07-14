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
    <title>Conversatie</title>

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
        <nav class="navbar-mobile">
            <div class="container-fluid">
                <ul class="navbar-mobile__list list-unstyled">
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
                        <div class="au-chat">
                            <div class="au-chat__title">
                                <div class="au-chat-info">
                                    <p style="font-size:20pt;font-weight: bold" >${mesaj.expeditor}</p>

                                </div>
                                <div class="au-chat-info">
                                    <p style="font-weight: bold" >Subiect: ${mesaj.subiect}</p>

                                </div>
                            </div>
                            <div class="au-chat__content">
                                <div class="recei-mess-wrap">
                                    <span class="mess-time">${mesaj.data}</span>
                                    <div class="recei-mess__inner">

                                        <div class="recei-mess-list">
                                            <div class="recei-mess">${mesaj.continut}</div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty mesaj.continutRaspuns}">
                                    <div class="send-mess-wrap">
                                        <div class="send-mess__inner">
                                            <div class="send-mess-list">
                                                <div class="send-mess">${mesaj.continutRaspuns}</div>
                                            </div>
                                        </div>
                                    </div>

                                </c:if>


                            </div>
                            <div class="au-chat-textfield">
                                <c:if test="${dejaRaspuns== false}">
                            <form:form method="post" action="${contextPath}/trimiteRaspuns/${mesaj.id}" modelAttribute="raspunsForm"  class="au-form-icon">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">

                                    <form:input type="text" id="continutRaspuns" path="continutRaspuns" name="continutRaspuns" placeholder="Scrie un mesaj..." class="au-input au-input--full au-input--h65"></form:input>
                                        <form:errors cssStyle="color: red" path="continutRaspuns"></form:errors>

                                    </div>

                                <button class="au-input-icon" type="submit">
                                    <i class="zmdi zmdi-mail-send"></i>
                                </button>
                            </form:form>
                                </c:if>
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


</body>

</html>
<!-- end document-->
