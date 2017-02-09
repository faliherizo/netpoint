<%@ page session="false" isErrorPage="true"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
	<head>
		<title><fmt:message key="monDSI.content.manual"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta name="robots" content="index, follow" />
	</head>
	<body>
		<div>
			<div>		
				<h2><fmt:message key="monDSI.error.pleaseSorry"/></h2>
				
				<p><strong><fmt:message key="monDSI.error.technicalTrouble"/></strong></p>
				<p><fmt:message key="monDSI.error.backToPreviousPage"><fmt:param><fmt:message key="monDSI.content.clickHere"/></fmt:param></fmt:message></p>
				<br/>
			</div>
		</div>
	</body>
</html>