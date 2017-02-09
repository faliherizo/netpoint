<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="label.netpoint_logo"/></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/conn.css" media="all" />
<body>
<div id="layout_wrapper_outer">
<div id="layout_wrapper">
	<div id="layout_top">

	</div>

	<div id="layout_body_outer">
	<div id="layout_body">
    <div id="header_login" style="background: url(${pageContext.request.contextPath}/img/main.gif) repeat-y;
	border-bottom: 1px solid #C5C5C5;">
	<img style="border:solid 1px gray;" src="${pageContext.request.contextPath}/img/logo_netpoint.jpg" width="180" height="60" alt="LOGO"/>
    </div>

	<div>
		<div>
		  <div class="clearer">&nbsp;</div>
			<div>	</div>
		</div>
	</div>
		<div id="main">
			<div class="left" id="content_outer">
				<div id="content">
					<div class="post">
						<div class="post_title">
							<h2>
							<spring:message code="label.portail"/>
							<span style="float:right;">
								<a href="?lang=fr"><img src="${pageContext.request.contextPath}/img/fr.gif"></a>
								<a href="?lang=en"><img src="${pageContext.request.contextPath}/img/en.gif"></a>
							</span>
							</h2>
						</div>
						<div class="post_date"></div>
								
						<div style="width:400px; margin-left:220px" align="center">
							<form:form id="loginForm" name="loginForm" commandName="mailform"  action="maildrop" method="post">
									<div class="form_row">
										<div class="form_property"><strong><spring:message code="label.email"/></strong></div>
										<div class="form_value"><form:input path="mail" autocomplete="off"/> <form:errors path="mail" cssClass="error" /></div>
										<div class="clearer">&nbsp;</div>
									</div>
									<div class="form_row form_row_submit">
										<div class="form_value"><input id="button-connection" type="submit" value="<spring:message code="label.valider"/>" onClick="doSubmit();"/></div>
										<div class="clearer">&nbsp;</div>
									</div>
								</fieldset>
							</form:form>
						</div>
                    </div>
						<p class="navigator">
							
						</p>
				</div>
			</div>
			<div class="clearer">&nbsp;</div>
		</div>

		<div id="dashboard">
			<div id="dashboard_inner">
				<div class="copy"><h3><spring:message code="label.coprigth"/></h3></div>
				<div class="clearer">&nbsp;</div>
			</div>
		</div>	
		</div>
	</div>
	<div id="footer">
		<div class="left">
			
		</div>
		<div class="right">
			
		</div>
		<div class="clearer">&nbsp;</div>
	</div>
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
