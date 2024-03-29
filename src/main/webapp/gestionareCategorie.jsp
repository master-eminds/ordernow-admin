<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gestionare categorie</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <h3> <a href="/welcome">Home</a></h3>
   <h2>Gestionare categorie</h2>
    <c:if test="${not empty listaCategorii}">

        <table>
            <c:forEach var="categorie" items="${listaCategorii}">
                <tr>

                    <td>
                            ${categorie.denumire}
                    </td>
                    <td>
                          <img src="${categorie.urlImagine}" alt="" height="150" width="200"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>
    <form:form method="POST" modelAttribute="categorieForm" class="form-signin">

        <spring:bind path="denumire">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="denumire" class="form-control" placeholder="Denumire"
                            autofocus="true"></form:input>
                <form:errors path="denumire"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="urlImagine">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="urlImagine" class="form-control" placeholder="URL imagine"></form:input>
                <form:errors path="urlImagine"></form:errors>
            </div>
        </spring:bind>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
