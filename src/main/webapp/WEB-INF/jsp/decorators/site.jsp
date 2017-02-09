<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  --%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css"/>              

<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/menutree.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css//Ressources/Netpoint/Netpoint.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery/devbundle/demo.css	"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery/devbundle/themes/base/jquery.ui.all.css" />

<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/js/jquery-ui-1.8rc3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.datepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.dialog.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts/script.js"></script>

<%-- <meta http-equiv="Window-TARGET" content="main1" />--%><title>
	Netpoint-group
</title>
   
<script type="text/javascript">
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/hoverIntent.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/superfish.js"></script>

<script type="text/javascript">
		// initialise plugins
		jQuery(function(){
			jQuery('ul.sf-menu').superfish();
		});
</script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<decorator:head/> 
</head>
<body>
	   <%@ include file="../layout/header.jsp"%>
	   <div id="divCenter">
			<div id="divCorps">
				<div id="headerdivCorps">
		<decorator:body/>
				</div>
		    </div>
	     </div>
	   <%@ include file="../layout/footer.jsp"%>
	
</body>
</html>