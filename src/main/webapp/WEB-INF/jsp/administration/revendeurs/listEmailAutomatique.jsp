<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<%@ page isELIgnored ="false" %>
<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			altField: "#alternate",
			altFormat: "DD, d MM, yy"
		});
	});
</script>
<div id="page">
<div class="scrollauto">
<h2><spring:message code="label.email_auto"/></h2>
<form:form action="listEmailAutomatique" commandName="searchCriteriaRevendeurmail" name="searchForm" method="GET">
	<table style="border: 1px solid gray;width:100%;">
		<tr>
			<td class="col1"><spring:message code="label.email"/></td>
			<td class="col1"> <form:input path="mail"/>
			</td>
			<td>
				<a href="#" onclick="document.searchForm.submit()">
				<input type="button" name="search" value="Rechercher"> </a>
			</td>
		</tr>
	</table>
</form:form>
<div id="separation" style="height: 30px"></div>
    <div class="resultat" >
    <c:if test="${!empty resultsearchRevendeuByMail}">
		<display:table name="${resultsearchRevendeuByMail}" requestURI="listEmailAutomatique"
				pagesize="10" defaultsort="4" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >						
				   <display:column property="societe.nom" title="Societe" sortable="true"></display:column>
				   <display:column property="revendeur.user.nom" title="Rattache a" sortable="true" />				    
				   <display:column property="user.etatCompte.libelle" title="Etat" sortable="true" />
				   <display:column property="dateCreation" title="Date de création" sortable="true" />
					   <display:column title="E-mails" style="width: 74px;" >
				   		<a href="<c:url value='cron-mail?id=${currentObject.mailhdr.id}'/>" >+</a>	
					</display:column>
					   <display:column title="Personnaliser" style="width: 74px;" >
					   		<a href="<c:url value='personnalisation-mail/${currentObject.mailhdr.id}'/>" >+</a>	
						</display:column>
						 <display:column title="Tester" style="width: 74px;">
							 <a href="<c:url value='test-mail?id=${currentObject.mailhdr.id}'/>" >+</a>	
						</display:column>
		</display:table>
</c:if>
		
	</div>	
</div>
</div>
<script language="JavaScript">
    document.forms[0].searchString.focus();
    document.forms[0].searchString.select();

	function confirmSubmit(name)
	{
	var agree=confirm("Are you sure you want to delete this item?");
	if (agree)
		return true ;
	else
		return false ;
	}

</script>

