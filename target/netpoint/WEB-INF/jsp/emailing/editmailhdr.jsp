<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<title>Personnalisation des E-mails</title>
<%@ page isELIgnored ="false" %>
<div id="page">
	<h2>Personnalisation des E-mails</</h2>
	<div id="separation" style="height: 30px"></div>
    <div id="resultat" >
		<c:if test="${!empty listTypemail }">
	<h3>
	<form:form commandName="mailhdr" id="mail_hdr" method="POST">
	<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
		<thead>
			<tr>
				<th></th>
				<th>Personnaliser</th>
				
			</tr>
		</thead>
		
		
		<tbody>
			<c:forEach items="${listTypemail}" var="mail">
				<tr>
					<td>${mail.subject}</td>
					<td><input type="checkbox" value="${mail.active}"></td>
				</tr>
			</c:forEach>
		</tbody>
		
		</table>
	</form:form>
		<div class="button">
			<a href="/">Retour</a>
		</div>
	</c:if>

		
	</div>	
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#liste').dataTable( {
        	"aaSorting": [[ 3, "desc" ]],
            "oLanguage": {
                "sUrl": "<%=request.getContextPath()%>/js/fr_FR.txt"
            }
        });
    });
</script>