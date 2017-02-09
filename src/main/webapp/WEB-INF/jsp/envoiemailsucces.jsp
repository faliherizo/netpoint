<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="label.netpoint_logo"/></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/conn.css" media="all" />
<body onload='document.loginForm.j_username.focus();'>
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
							
									<h2><spring:message code="label.envoiemailsuccess"/></h2>
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
