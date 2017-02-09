<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><spring:message code="label.netpoint_logo"/></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css"/>              
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.all.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css" />
<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8rc3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8rc3.custom.css" />
<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/menutree.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/Netpoint.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.datepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.dialog.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$('#password').keyup(function(){
		$('#result').html(checkStrength($('#password').val()))
	})	
	$('#con_password').keyup(function(){
		if($('#password').val()!=$('#con_password').val()){
			$('#result2').html('password incorect')
		}
		else{
			$('#result2').html('<img src="<%=request.getContextPath()%>/img/green.png"/>')
		}
	})
	function checkStrength(password){
    
	//initial strength
    var strength = 0
	
    //if the password length is less than 6, return message.
    if (password.length < 6) { 
		$('#result').removeClass()
		$('#result').addClass('short')
		return 'Too short' 
	}
    
    //length is ok, lets continue.
	
	//if length is 8 characters or more, increase strength value
	if (password.length > 7) strength += 1
	
	//if password contains both lower and uppercase characters, increase strength value
	if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1
	
	//if it has numbers and characters, increase strength value
	if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1 
	
	//if it has one special character, increase strength value
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1
	
	//if it has two special characters, increase strength value
    if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
	
	//now we have calculated strength value, we can return messages
	
	//if value is less than 2
	if (strength < 2 ) {
		$('#result').removeClass()
		$('#result').addClass('weak')
		return 'Weak'			
	} else if (strength == 2 ) {
		$('#result').removeClass()
		$('#result').addClass('good')
		return 'Good'		
	} else {
		$('#result').removeClass()
		$('#result').addClass('strong')
		return 'Strong'
	}
}
});
</script>
<script type="text/javascript">
			$(function(){

				// Accordion
				$("#accordion").accordion({ header: "h3" });
				$("#accordions").accordion({ header: "h3" });
				// Tabs
				$('#tabs').tabs();
	

				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 300,
					buttons: {
						"Oui": function() { 
							$('#form_user').submit();
							$(this).dialog("close"); 
						}, 
						"Non": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#valider').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				$('#datepicker').datepicker({
					inline: true
				});
				
				// Slider
				$('#slider').slider({
					range: true,
					values: [17, 67]
				});
				
				// Progressbar
				$("#progressbar").progressbar({
					value: 20 
				});
				
				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				
			});
		</script>
<body onload='document.loginForm.j_username.focus();'>

<div id="dialog" title="">
			<p><%-- <spring:message code="label.newpwdchange"/>--%></p>
</div>
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
								<%-- <a href="?lang=fr"><img src="${pageContext.request.contextPath}/img/fr.gif"></a>
								<a href="?lang=en"><img src="${pageContext.request.contextPath}/img/en.gif"></a>--%>
							</span>
							</h2>
						</div>
						<div class="post_date"></div>
								
						<div style="width:425px; margin-left:220px" align="center">
							<form:form id="frmpwd" name="frmpwd" commandName="mailpwdfrm" action="changepassword"  method="post">
								<fieldset>
									<div class="legend">
										<h2><spring:message code="label.recupe_pwd"/></h2>
									</div>
									<div class="form_row">
										<div class="form_property"><strong><spring:message code="label.email"/> :</strong></div>
										<div class="form_value"><form:input autocomplete="off" id="mail" name="mail" path="mail" />
										<form:errors path="mail" cssClass="error" />
										</div>
										<div class="clearer">&nbsp;</div>
									</div>
			
									<div class="form_row">
										<div class="form_property"><strong><spring:message code="label.password"/> :</strong></div>
										<div class="form_value"><form:password autocomplete="off" id="password" name="password" path="password"/><span id="result"></span></div>
										
										<div class="clearer">&nbsp;</div>
									</div>
									<div class="form_row">
										<div class="form_property"><strong><spring:message code="label.password2"/> :</strong></div>
										<div class="form_value"><form:password autocomplete="off" id="con_password" name="con_password" path="password2"/><span  id="result2" style="float: rigth;padding-top:50px;"></span></div>
										<div class="clearer">&nbsp;</div>
									</div>
									<div class="form_row form_row_submit">
										<div class="form_value"><input type="submit" name="valider" value="<spring:message code="label.valider"/>" /></div>
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
