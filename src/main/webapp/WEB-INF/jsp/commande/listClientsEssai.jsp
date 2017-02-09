<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<c:set scope="request" var="pageName" value="commande"/>
<c:url var="urlChercher" value="/listClientsEssai"/>
<div id="page">
<h2>Clients en essai</h2>
<div class="scrollauto">
	<form:form commandName="searchCriteria" method="Get" action="${urlChercher}">
	 <table style="width:100%;" >
					 <tr>
					    <td style="width:50%;">
					    <table style="border: 1px solid gray;width:100%;">
					    
					   <tr>
						    <td class="col1">Société</td>
							<td class="col1">
							  <div id="profilerr" class="erreur" style="width: 160px;">
								<form:select path="societe">
									<form:option value="" label="Choisir" />
									<form:options items="${societeList}" itemLabel="nom" itemValue="id" />
								</form:select>
							</div>
							</td>
							
							
					    </tr>
					    
					  
					     <tr>
						    <td class="col1">Email</td>
							 <td class="col1"> <form:input path="email" type="text"/>
							</td>
					    </tr>
					    
					    
					 	 <tr>
									<td class="col1">Telephone</td>
									<td class="col1">
									<form:input path="phone" type="text"/>
									</td>
								</tr>
					  
					    </table>
					    </td>
						<td style="width:50%;">
							 <table style="border: 1px solid gray;width:100%;">
							 
							  <tr>
								    <td class="col1">N° Client(revendeur)</td>
									<td class="col1">
									 <input id="client_n" 	name="num_client_revendeur" type="text">
									</td>
					    		</tr>
					    		
					    		
					    		 <tr>
								    <td class="col1">N° Client</td>
									<td class="col1">
										 <form:input path="num_client" type="text"/>
									</td>
					   			 </tr>
					    		
					    		 <tr>
								    <td class="col1">Contact(Nom/Prénom)</td>
									<td class="col1">
									  <form:input path="nom" id="c_essai_contact" 	name="c_essai_contact" type="text"></form:input>
									</td>
					    		</tr>
							    
						    </table>
						</td>
					 </tr>
					 
	        </table>
	        <input type="submit" value="Rechercher"/>
	</form:form>
	
	<br></br>
	<div class="resultat" >
	<c:if test="${!empty clientEnEssaiList}">
		 <div style="margin-top:20px; margin-bottom:20px; weight:200px;; width: 95%;text-align:center; font-size:13px;font-weight:bold;border:2px solid #e3e3e3;">
	<display:table name="${clientEnEssaiList}" requestURI="listClientsEssai"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="client.societe.nom" title="Societe" sortable="true" />
				    <display:column property="client.codeClient"  title="N° Client" sortable="true"/>
				    <display:column property="client.user.etatCompte.libelle"  title="Etat" sortable="true"/>
				    <display:column property="client.user.nom"  title="Contact" sortable="true"/>
				    <display:column property="revendeur.user.nom"  title="Revendeur" sortable="true"/>
				   <display:column title="Action" style="width: 74px;">
					<!-- <a href="<c:url value='/UpdateClient?id=${currentObject.id}'/>"> 
							<img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/edit.gif"/></a>
					        &nbsp;&nbsp;
				            <a href="<c:url value='/deleteUser/${currentObject.id}'/>" onclick="return confirmSubmit()"> 
				            <img border="0" alt="Delete" src="<%=request.getContextPath()%>/img/delete.gif"/>
						</a> -->
					</display:column>
		</display:table>
	 </div>
	</c:if>
	</div>
</div>
</div>