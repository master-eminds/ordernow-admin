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
    <title>Adaugare cont admin</title>
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


    <form:form method="POST" action="${contextPath}/salvareCont"  modelAttribute="adminForm" class="splash-container">
    <div class="card">
        <div class="card-header">
            <c:if test="${add!=false}">
                <h3 class="mb-1">Creare cont</h3>
            </c:if>
            <c:if test="${add==false}">
                <h3 class="mb-1">Setari cont</h3>
            </c:if>
            <p>Introduceti informatiile despre dumneavoastra.</p>
        </div>
        <div class="card-body">
            <spring:bind path="id">
            <div class="form-group">
                <form:input type="hidden" id="id" path="id" name="id"  class="form-control"></form:input>
                </spring:bind>
                <spring:bind path="rol">
                <div class="form-group">
                    <form:input type="hidden" id="rol" path="rol" name="rol"  class="form-control"></form:input>
                    </spring:bind>
            <spring:bind path="nume">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="nume" class="form-control form-control-lg" placeholder="Nume"
                                autofocus="true"></form:input>
                    <form:errors cssStyle="color: red" path="nume"></form:errors>

                </div>
            </spring:bind>
            <spring:bind path="prenume">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="text" path="prenume" class="form-control form-control-lg" placeholder="Prenume"
                                autofocus="true"></form:input>
                    <form:errors cssStyle="color: red" path="prenume"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="text" path="username" class="form-control form-control-lg" placeholder="Email"
                                autofocus="true"></form:input>
                    <form:errors cssStyle="color: red" path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="password" path="password" id="pass1" class="form-control form-control-lg" placeholder="Parola"
                                autofocus="true"></form:input>
                    <form:errors cssStyle="color: red" path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input type="password" path="passwordConfirm" class="form-control form-control-lg" placeholder="Confirmare parola"
                                autofocus="true"></form:input>
                    <form:errors cssStyle="color: red" path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

                <div class="form-group pt-2">
                    <button class="btn btn-block btn-primary" type="submit">Salveaza modificarile</button>
                </div>
                <div class="card-footer bg-white">
                    <p>Vrei sa renunti?<a href="${contextPath}/welcome" class="text-secondary">Revino la pagina principala.</a></p>
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
