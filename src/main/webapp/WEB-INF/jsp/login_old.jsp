<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>NetPoint - Portail générique</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css" media="all" />
<style type="text/css">
body {
	background: #192939 url('../images/img/body.gif') repeat-x left top;
	color: #5A5A50;
	font: normal 0.8em sans-serif;
	line-height: 1.5em;	
}

</style>
</head>
<body onload='document.loginForm.j_username.focus();'>
<div id="global-connection">
<div id="header-connection"><img style="border:solid 1px gray;" src="${pageContext.request.contextPath}/img/logo_netpoint.jpg" width="180" height="60" alt="LOGO"/>
<h1 style="margin-left:120px;">Portail générique</h1>
</div>
<div id="messages-connection">
	<font class="error">
		
		<c:if test="${not empty param.login_error}">
		   <span class="actionError">
		   	<c:if test="${param.login_error==6}">
					<spring:message code="mondsi.error.logininvalid"/>
			</c:if>
			<c:if test="${param.login_error==2}">
					<spring:message code="mondsi.error.user_locked"/>
			</c:if>
			<c:if test="${param.login_error==3}">
					<spring:message code="mondsi.error.user_disabled"/>
			</c:if>
			<c:if test="${param.login_error==4}">
					<spring:message code="mondsi.error.connection"/>
			</c:if>
			<c:if test="${param.login_error==5}">
					<spring:message code="mondsi.error.AccountExpired"/>
			</c:if>
			<c:if test="${param.login_error==7}">
					<spring:message code="mondsi.error.mot_de_pass"/>
			</c:if>
		  </span>
		</c:if>
</font>
</div>

<div id="page-connection">
<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
	<table class="table-connection" cellpadding="6" cellspacing="0">
		<tr>
			<td id="label-login" class="col1"><span style="height:6px;color:#CC3333;">*</span>Login :</td>
			<td><input autocomplete="on" id="usernameField" type="text" name="j_username" /></td><td>
					<c:if test="${param.login_error==1}">
					<spring:message code="mondsi.error.existemail"/>
			</c:if></td>
		</tr>
		<tr>
			<td id="label-password" class="col1"><span style="height:6px;color:#CC3333;">*</span>Mot de passe :</td>
			<td><input autocomplete="off" id="passwordField" type="password" name="j_password" /></td>
		</tr>
		<tr>
			<td><!-- --></td>
			<td align="right" class="txt11">
			<span onclick="" title="Mot de passe oublié ?" class="link"> <a href="maildrop">Mot de passe oublié?</a></span>
		</td>
		</tr>
	</table>
  <input id="button-connection" type="submit" value="" onclick="doSubmit();"/>
 </form>
</div>
</div>
</body>

</html>

<script type="text/javascript">
        //window.parent.document.body.rows = '0, 0, *';
		document.getElementById("usernameField").focus();
		function doSubmit(){
		if(document.getElementById("usernameField").value==0){
		   window.alert('<spring:message code="common.login.login.required" />');
		 }else {
		    if(document.getElementById("passwordField").value==""){
		       window.alert('<spring:message code="common.login.password.required" />');
		     }else{
					document.loginForm.submit();
				  }
		 }
	  }
</script>