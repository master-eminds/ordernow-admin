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
    <title>Administrare ospatar</title>

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
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong>Angajati </strong>
                                    <small>Administrare</small>
                                </div>
                                <div class="card-body card-block">
                                <form:form method="post" action="${contextPath}/salvareOspatar" modelAttribute="ospatarForm" class="form-signin">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <spring:bind path="id">
                                        <div class="form-group">
                                        <form:input type="hidden" id="nume" path="id" name="id"  class="form-control"></form:input>
                                     </spring:bind>
                                      <spring:bind path="nume">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label for="nume" class=" form-control-label">Nume</label>
                                                <form:input type="text" id="nume" path="nume" name="nume" placeholder="Nume Prenume" class="form-control"></form:input>
                                          <form:errors cssStyle="color: red" path="nume"></form:errors>
                                      </spring:bind>
                                    </div>
                                        <spring:bind path="email">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label for="email" class=" form-control-label">Email</label>
                                                <form:input type="email" id="email" path="email" name="email" placeholder="Email"
                                                       class="form-control"></form:input>
                                                <form:errors cssStyle="color: red" path="email"></form:errors>
                                            </div>
                                        </spring:bind >
                                    <div class="row form-group">
                                        <div class="col-8">
                                        <spring:bind path="parola">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label for="parola" class=" form-control-label">Parola</label>
                                                <form:input type="password" path="parola" id="parola" name="parola" class="form-control"></form:input>
                                                <form:errors cssStyle="color: red" path="parola"></form:errors>
                                            </div>
                                         </spring:bind>
                                        </div>
                                        <div class="col-8">
                                            <spring:bind path="passwordConfirm">
                                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                                    <label for="passwordConfirm" class=" form-control-label">RepetaParola</label>
                                                    <form:input type="password" path="passwordConfirm" id="passwordConfirm" name="passwordConfirm"
                                                           class="form-control"></form:input>
                                                    <form:errors cssStyle="color: red" path="passwordConfirm"></form:errors>
                                                </div>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-success btn-sm">
                                            <i class="fa fa-dot-circle-o"></i> <c:if test="${add!=false}"> Submit </c:if> <c:if test="${add==false}"> Update </c:if>
                                        </button>
                                        <button type="reset" class="btn btn-danger btn-sm">
                                            <i class="fa fa-ban"></i> Reset
                                        </button>

                                    </div>
                                </div>
                                </form:form>
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
