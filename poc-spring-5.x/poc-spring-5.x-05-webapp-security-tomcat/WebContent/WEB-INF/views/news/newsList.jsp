<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de Noticias</title>
    <spring:url value="/resources" var="publicResourcesUrl" />
	<spring:url value="/news/create" var="newsFormUrl" />
	<spring:url value="/news/edit" var="newsEditUrl" />
	<spring:url value="/news/delete" var="newsDeleteUrl" />
    <link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${publicResourcesUrl}/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3>Listado de Noticias</h3>
      
      <c:if test="${successMessage != null}">      	
      	<div class='alert alert-success' role="alert">${successMessage}</div>      	
      </c:if>
      
      <a href="${newsFormUrl}" class="btn btn-success" role="button" title="Nueva Pelicula" >Nueva</a><br><br>

      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Fecha</th>              
                <th>Estatus</th>              
                <th>Opciones</th>              
            </tr>
            <c:forEach var="news" items="${newsList}">
            <tr>
                <td>${news.id}</td>
                <td>${news.title}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${news.date}"/></td>              
                <td>
                <c:choose>
                <c:when test="${news.status eq 'ACTIVE'}">
                	<span class="label label-success">${news.status}</span>
                </c:when>
                <c:otherwise>
                	<span class="label label-danger">${news.status}</span>
                </c:otherwise>
                </c:choose>
                </td>
                <td>
                    <a href="${newsEditUrl}/${news.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${newsDeleteUrl}/${news.id}" onclick='return confirm("�Desea eliminar la noticia \"${news.title}\"?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            </c:forEach>
        </table>
      </div>  
      <hr class="featurette-divider">

	<jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js"></script>     
  </body>
</html>