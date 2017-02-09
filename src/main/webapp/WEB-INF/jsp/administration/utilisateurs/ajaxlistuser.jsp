<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<% out.println(request.getParameter("nombre")); %>
<%String i= request.getParameter("nombre");
int g=java.lang.Integer.parseInt(i);
%>
<h1>test	</h1>
<c:if test="${!empty resultsearchtUser}">
		<display:table name="${resultsearchtUser}" requestURI="listUser"
				pagesize="<%=g%>" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="login" title="Email utilisateur" sortable="true" />
				    <display:column property="profil.libelle"  title="Droit" sortable="true"/>
				    <display:column property="dateCreation"  title="Date de cr&eacuteation" sortable="true"/>
				    <display:column property="dateFin"  title="Date de fin" sortable="true"/>
				    <display:column property="etatCompte.libelle"  title="Etat" sortable="true"/>
				   <display:column title="Action" style="width: 74px;">
						<a href="<c:url value='/detailUser/${currentObject.id}'/>"> 
							<img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/edit.gif"/></a>
					        &nbsp;&nbsp;
				            <a href="<c:url value='/deleteUser/${currentObject.id}'/>" onclick="return confirmSubmit()"> 
				            <img border="0" alt="Delete" src="<%=request.getContextPath()%>/img/delete.gif"/>
						</a>
					</display:column>
</display:table>
</c:if>