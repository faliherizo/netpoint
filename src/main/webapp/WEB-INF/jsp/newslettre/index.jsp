<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.ui.all.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.8rc3.custom.css" />
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8rc3.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.js"></script>
<div id="newslettre">
	<div id="titre_mail"><spring:message code="label.newslettre"/></div>
	<div id="table_newslettre">
		<!--<c:forEach >-->
	
	</div>
</div>