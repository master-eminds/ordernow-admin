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
    <title>Creare cont</title>
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

<div class="container">


    <form:form method="POST" modelAttribute="adminForm" class="splash-container">
    <div class="card">
        <div class="card-header">
            <h3 class="mb-1">Creare cont</h3>
            <p>Introduceti informatiile despre dumneavoastra.</p>
        </div>
        <div class="card-body">
            <spring:bind path="nume">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="nume" class="form-control form-control-lg" placeholder="Nume"
                                autofocus="true"></form:input>
                    <form:errors path="nume"></form:errors>

                </div>
            </spring:bind>
            <spring:bind path="prenume">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="text" path="prenume" class="form-control form-control-lg" placeholder="Preume"
                                autofocus="true"></form:input>
                    <form:errors path="prenume"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="text" path="username" class="form-control form-control-lg" placeholder="Email"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="password" path="password" id="pass1" class="form-control form-control-lg" placeholder="Parola"
                                autofocus="true"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="password" path="passwordConfirm" class="form-control form-control-lg" placeholder="Confirmare parola"
                                autofocus="true"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <div class="form-group pt-2">
                <button class="btn btn-block btn-primary" type="submit">Inregistrare cont</button>
            </div>
            <div class="card-footer bg-white">
                <p>Ai deja un cont? <a href="${contextPath}/login" class="text-secondary">Autentifica-te aici.</a></p>
            </div>
        </div>
    </div>
        </form:form>

    </div>
    <!-- /container -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
