<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>  
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<%@ page isELIgnored ="false" %>
<script type= "text/javascript" >
$(document).ready(function () 
{
	$('#projectid').change(function(){
		$('#result').fadeOut();
		$.post('ajaxlistuser', {
		nombre: $('#nombre').val()
		}, function(response){
			//setTimeout("finishAjax('result', '"+escape(response)+"')", 400);
			$('#resultat').refresh();
		});
		return false;
	});
});


function finishAjax(id, response){
	 $('#'+id).html(unescape(response));
	 $('#'+id).fadeIn();
}
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
<h2><spring:message code="label.list_user"/></h2>
   <form:form action="listUser" commandName="searchCriteria" name="searchForm" method="GET">
	<table>
		<tr>
			<td class="col1"><spring:message code="label.email"/> :</td>
			<td class="col1"> <form:input path="mail"/>
			</td>
			<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.droit"/> :</td>
			<td class="col1">
			 	<div id="profilerr" class="erreur" style="width: 160px;">
					<form:select path="idprofil">
					<form:option value="" label="Choisir" />
					<form:options items="${profilListUser}" itemLabel="libelle" itemValue="id" />
					</form:select>
				</div>
			</td>
			<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etatcompte"/> :</td>
			<td class="col1">
				 <div id="clienterr" class="erreur" style="width: 160px;">
				   <form:select path="idetat">
						<form:option value="" label="Choisir" />
						<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
				    </form:select>
			    </div>
			</td>
			<td>
				<input type="submit" value="<spring:message code="label.rechercher"/>" />
			</td>
		</tr>
	</table>
</form:form>
<%String i= request.getParameter("nombre");
int g=2;
if(i!=null)
	g=java.lang.Integer.parseInt(i);
%>
<div id="separation" style="height: 30px"></div>
    <div class="resultat" >
    <c:if test="${!empty resultsearchtUser}">
		<display:table name="${resultsearchtUser}" requestURI="listUser"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true"
				decorator="com.netPoint.applications.site.displaytag.decorators.UserDecorator">	
						
				    <display:column property="mail" titleKey="label.email" sortable="true"  />
				    
				    <display:column property="profil.libelle"  titleKey="label.droit" sortable="true"/>
				    <display:column property="dateCreation"  titleKey="label.date_creation" sortable="true" format="{0,date,dd/MM/yy}"/>
				    <display:column property="dateFin"  titleKey="label.date_fin" sortable="true"  format="{0,date,dd/MM/yy}" />
				    <display:column property="etatCompte.libelle"  titleKey="label.etatcompte" sortable="true"/>
				  <%--   <display:column title="Action" style="width: 74px;">
						
							<img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/edit.gif"/></a>
					       &nbsp;&nbsp;
				            <a href="<c:url value='/deleteUser/${currentObject.id}'/>" onclick="return confirmSubmit()"> 
				            <img border="0" alt="Delete" src="<%=request.getContextPath()%>/img/delete.gif"/>
						</a>
					</display:column>--%>
					<display:setProperty name="export.csv.filename" value="users.csv"/>
           			<display:setProperty name="export.excel.filename" value="users.xls"/>
		</display:table>

	</c:if>	
	</div>	
</div>


<div id="result" ></div>
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

