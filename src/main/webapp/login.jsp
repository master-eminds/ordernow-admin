<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Autentificare</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${contextPath}/resources/tema/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/tema/libs/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/tema/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
        }
    </style>
</head>

<body>

<div class="splash-container">
    <div class="card ">
        <div class="card-header text-center"><a href="#">
            <img class="logo-img" src="${contextPath}/resources/tema/images/icon/logo_administrare2C.png" width="240" height="120" alt="logo"></a>
            <span class="splash-description">Introduceti informatiile contului dumneavoastra.</span></div>

        <div class="card-body">
            <form method="POST" action="${contextPath}/login" class="form-signin">
                <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <input class="form-control form-control-lg" id="username"  name="username" type="text" class="form-control" placeholder="Email admin"
                       autofocus="true"/>
                </div>
                <div class="form-group">
                <input name="password" type="password" id="password" class="form-control form-control-lg" placeholder="Parola"/>
                </div>
                    <span>${error}</span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-primary btn-lg btn-block" type="submit">Autentificare</button>
            </form>
        </div>
       <%-- <div class="card-footer bg-white p-0  ">
            <div class="card-footer-item card-footer-item-bordered">
                <a href="${contextPath}/registration" class="footer-link">Creeaza un cont</a></div>
        </div>--%>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Optional JavaScript -->
<script src="${contextPath}/resources/tema/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${contextPath}/resources/tema/vendor/bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
