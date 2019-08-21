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
    <title>Administrare categorie</title>

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
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong>Categorie </strong>
                                    <small>Administrare</small>
                                </div>
                                <div class="card-body card-block">
                                <form:form method="post" action="${contextPath}/salvareCategorie/${meniu_id}" modelAttribute="categorieForm" class="form-signin">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <spring:bind path="id">
                                        <div class="form-group">
                                        <form:input type="hidden" id="nume" path="id" name="id"  class="form-control"></form:input>
                                        </div>
                                     </spring:bind>
                                      <spring:bind path="denumire">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <label for="denumire" class=" form-control-label">Denumire</label>
                                                <form:input type="text" id="denumire" path="denumire" name="denumire" placeholder="nume" class="form-control"></form:input>
                                          <form:errors cssStyle="color: red" path="denumire"></form:errors>
                                            </div>
                                      </spring:bind>
                                                    <spring:bind path="vizibilitate">
                                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <label for="vizibilitate" class=" form-control-label">Vizibilitate</label>
                                                            <form:select id="vizibilitate" path="vizibilitate" name="vizibilitate" class="form-control">
                                                                <option value="0" >Alegeti vizibilitatea</option>
                                                                <c:if test="${ categorieForm.vizibilitate=='0'}">
                                                                    <option value="vizibil" >Vizibil</option>
                                                                    <option value="invizibil">Invizibil</option>
                                                                </c:if>
                                                                <c:if test="${categorieForm.vizibilitate=='vizibil'}">
                                                                    <option value="vizibil" selected >Vizibil</option>
                                                                    <option value="invizibil">Invizibil</option>
                                                                </c:if>
                                                                <c:if test="${categorieForm.vizibilitate=='invizibil'}">
                                                                    <option value="vizibil" >Vizibil</option>
                                                                    <option value="invizibil" selected>Invizibil</option>
                                                                </c:if>
                                                            </form:select>
                                                    </div>
                                                        <form:errors cssStyle="color: red" path="vizibilitate"></form:errors>

                                                    </spring:bind>

                                        <spring:bind path="imagine">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                    <label for="imagine" class=" form-control-label">Incarca o imagine</label>
                                                    <input type="file" id="imagine" class="form-control-file"/>
                                                    <form:input type="hidden" name="imagine" path="imagine" id="idS" />
                                                <c:if test="${add==false}">
                                                    <label for="imagePreview" class=" form-control-label">Imagine veche</label>
                                                </c:if>
                                                <img style="max-width: 150px; max-height: 150px; margin: 0 auto;object-fit: cover" src="${imageSrc}" id="imagePreview">

                                                    <label id="label-preview"  for="preview" class=" form-control-label"></label>
                                                <img style="max-width: 150px; max-height: 150px; margin: 0 auto; object-fit: cover" src="" id="preview">

                                            </div>
                                            <form:errors cssStyle="color: red" path="imagine"></form:errors>

                                        </spring:bind>
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
    function getBase64(file) {
        var preview = document.getElementById("preview");

        var label= document.getElementById("label-preview");
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (e) {
            console.log(reader.result);
            document.getElementById("idS").value = reader.result;

            console.log('a ajuns aici');
            preview.setAttribute('src', e.target.result);
            label.innerHTML='Imaginea noua' ;
        };
        reader.onerror = function (error) {
            console.log('Error: ', error);
        };
    }


    $('#imagine').bind('change', function (e) {
        var files = document.getElementById('imagine').files;
        if (files.length > 0) {
            getBase64(files[0]);
        }});



</script>

</body>

</html>
<!-- end document-->
