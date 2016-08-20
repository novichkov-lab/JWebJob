<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- \\ -->
<!-- JQuery -->
<%@ include file="/WEB-INF/templates/includeJquery.jsp" %>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- // -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Progress</title>
<!-- \\ -->
<!-- Javascript and Stylesheet -->
<script type="text/javascript" src="<c:url value="js/JobProgress.js" />" defer></script><!-- defer loads the script after page has loaded -->
<link rel="stylesheet" type="text/css" href="./css/propagator_base.css">
<!-- // -->
</head>
<body>
<div  class="ui-corner-all ui-widget ui-widget-content" style="background-color:white">
<div class="header">
		<img src="images/propagator_logo.png" class="logo">
        <h1>JavaWebJob Project Title</h1><span class="slogan">Lengthy Subtitle to Describe Program Function</span>
	<div class="clear"></div>        	
</div>
<div id="main_nav"><div class="version">-- version 1.0 --</div> </div>

<div class="main">


	<div id="content" class="content">
	  	<div class="grey_primebox regprecise_projects">
			<h2>Processing Job: <c:out value="${requestScope.jobName}"></c:out></h2>
			<table class="stattbl" style="width:200px">
			<c:forEach var="contentItem" items="${requestScope.iterableItemList}">
				<tr class="displayTableRow">
					<td>
						<div id="${contentItem.taskId}_Icon" class="taskStatusIcon">
							
						</div>
					</td>
					<td>
						<c:out value="${contentItem.taskName}"></c:out>
						<Input id="${contentItem.taskId}" type="hidden" disabled value="${contentItem.subTaskLevel}">
					</td>
				</tr>
			</c:forEach>
			</table>
			<div class="clear"></div>
		</div>
	</div>

	
</div>

<div class="footer_divider"></div>
<div class="footer">
	<div class="footerText">
    <div class="footer">© 2009-2016 RegPrecise</div>
</div>
</div>
</div>
</body>
</html>