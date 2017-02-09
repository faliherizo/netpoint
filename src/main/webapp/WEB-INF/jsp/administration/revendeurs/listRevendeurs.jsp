<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<%@ page isELIgnored ="false" %>
<%
int nombre = 2;
%>
<script type="text/javascript" charset="utf-8">
		
	</script>
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
<h2><spring:message code="titre.listerevendeur"/></h2>
   <form:form action="listRevendeurs" commandName="searchCriteriaRevendeur" name="searchForm" method="GET">
	<table style="width:100%;">
		<tr>
			<td class="col1"><spring:message code="label.societe"/></td>
			<td class="col1">
				<div id="profilerr" class="erreur" style="width: 160px;">
					<form:select path="idSociete">
						<form:option value="" label="Choisir" />
						<form:options items="${societeList}" itemLabel="nom" itemValue="id" />
					</form:select>
				</div>
			</td>
			
			<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.droit"/></td>
			<td class="col1">
				 <div id="clienterr" class="erreur" style="width: 160px;">
				   <form:select path="idetat">
						<form:option value="" label="Choisir" />
						<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
				    </form:select>
			    </div>
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
     <c:if test="${!empty resultsearchRevendeur}">
		<display:table name="${resultsearchRevendeur}" requestURI="listRevendeurs"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true">			
				   <display:column property="societe.nom" titleKey="label.societe" sortable="true"></display:column>
				   <display:column titleKey="label.ratachea" sortable="true" >	
				    <a href="<c:url value='detailRevendeur/${currentObject.id}'/>" >${currentObject.user.nom}</a>	
				   </display:column>			    
				   <display:column property="user.etatCompte.libelle" titleKey="label.etatcompte" sortable="true" />
				   <display:column property="user.dateCreation" titleKey="label.date_creation" sortable="true" format="{0,date,dd/MM/yy}"  />
				   <display:column property="user.dateFin" titleKey="label.date_fin" sortable="true"  format="{0,date,dd/MM/yy}" />
				   <%--<display:column title="Action" style="width: 74px;" >
				   <a href="<c:url value='detailRevendeur/${currentObject.id}'/>" ><img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/detail.png"/></a>	
						 <a href="<c:url value='/editRevendeur/${currentObject.id}'/>">
					
							<img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/edit.gif"/></a>
					        &nbsp;&nbsp;
				            <a href="<c:url value='/deleteRevendeur/${currentObject.id}'/>" onclick="return confirmSubmit()"> 
				            <img border="0" alt="Delete" src="<%=request.getContextPath()%>/img/delete.gif"/>
						</a>
					</display:column>--%>
					<display:setProperty name="export.csv.filename" value="revendeurs.csv"/>
           			<display:setProperty name="export.excel.filename" value="revendeurs.xls"/>
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