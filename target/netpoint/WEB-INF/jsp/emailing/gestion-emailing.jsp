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
	<table class="table1 table-groupe" cellpadding="3" cellspacing="1">
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
					<td><a href="/netpoint/editmail?id=${mail.id}">  +</a></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
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