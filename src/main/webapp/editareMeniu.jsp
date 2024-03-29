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

    <title>Editare meniu</title>

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
    <h2 class="form-signin-heading">Lista produse</h2>

    <c:if test="${not empty listaProduse}">

            <table>
            <c:forEach var="produs" items="${listaProduse}">
                <tr>
                    <td>
                        <a href="${contextPath}/gestionareProdus?meniu_id=${produs.meniu.id}"></a>
                    </td>
                    <td>
                        ${produs.denumire}
                    </td>
                    <td>
                            ${produs.pret} lei
                    </td>
                    <td>
                        <img src="${produs.urlImagine}" alt="" height="80" width="150"/>
                    </td>
                </tr>
            </c:forEach>
            </table>

    </c:if>
    <a href="${contextPath}/gestionareProdus?meniu_id=${meniu_id_param}">Adauga un produs nou</a>



</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
