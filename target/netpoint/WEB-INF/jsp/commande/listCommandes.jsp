<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />

<%@ page isELIgnored ="false" %>
<c:set scope="request" var="pageName" value="commande"/>

<div id="page">
<div class="scrollauto">
<div id="page_search">
<h2><spring:message code="label.liste_commande"/></h2>
	<form:form action="listCommandes" commandName="searchCriteria" name="searchForm" method="GET">
	   <table style="width:100%;" >
					 <tr>
					    <td style="width:50%;">
					    <table style="border: 1px solid gray;width:100%;">
					    
					     <tr>
						    <td class="col1"><spring:message code="label.numerocmd"/> :</td>
							<td class="col1">
							 <form:input path="numeroCommande" type="text"/>
							</td>
					    </tr>
					    
					      <tr>
						    <td class="col1"><spring:message code="label.numeroCommandeRev"/> :</td>
							<td class="col1">
							 <form:input path="num_commande_rev" type="text"/>
							</td>
					    </tr>
					    
					  
					    </table>
						<br/>
						<table style="border: 1px solid gray;width:100%;padding-top:1px;">
						    
						     <tr>
							    <td class="col1"><spring:message code="label.date_cmd_entre"/> :</td>
								<td class="col1">
								 <form:input path="dateInf" type="date" id="date" name="date"/>
								</td>
						    </tr>
						    
						      <tr>
							    <td class="col1"><spring:message code="label.et_le"/> : </td>
								<td class="col1">
								  <form:input path="dateSupp" type="date" id="date_fin" name="date_fin"/>
								</td>
						    </tr>
						    
						     
						</table>
					    
					    </td>
						<td style="width:50%;">
							 <table style="border: 1px solid gray;width:100%;">
							 
							  <tr>
								    <td class="col1"><spring:message code="label.email"/> :</td>
									<td class="col1">
									<form:input path="email" type="text"/>
									</td>
					    		</tr>
					    		
					    		
					    		 <tr>
									<td>
										<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/>
									</td>
									<td>
										<form:input path="societe"  />
									</td>
									<td>
						 				<form:errors path="societe" cssClass="error" />
						 			</td>
								</tr>
					    		
					 
							     <tr>
								    <td class="col1"><spring:message code="label.num_client"/> :</td>
									<td class="col1">
									 <form:input path="num_client" />
									</td>
					    		</tr>
					    		
					    		 <tr>
						    <td class="col1"><spring:message code="label.etat"/> :</td>
								<td class="col1">
									 <div id="clienterr" class="erreur" style="width: 160px;">
									   <form:select path="etat">
											<form:option value="" label="Choisir" />
											<form:options items="${etatCommande}" itemLabel="libelle" itemValue="id" />
									    </form:select>
								    </div>
								</td>
					    </tr>
					   
							    
					    		
					    		<tr>
								    <td class="col1"><spring:message code="label.fournisseur"/> :</td>
									<td class="col1">
									   <form:select id="fournisseur" path="id_fournisseur">
											<form:option value="" label="Choisir" />
											<form:options items="${get_frns_list}" itemLabel="nom" itemValue="id" />	
										</form:select> 
									</td>
							    </tr>
							    
						    </table>
						</td>
					 </tr>
					 
					 
						  
					 
	        </table>
			<br/>
			<div style="padding-left:10px;">
	          <input type="submit" name="valider" value="<spring:message code="label.rechercher"/>" />
			</div>
	</form:form>
</div>

	<div class="resultat" style="padding-top:20px;" >
		<c:if test="${!empty commandeList}">
				<display:table name="${commandeList}" requestURI="listCommandes"
						pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
						    <display:column property="client.societe.nom" titleKey="label.societe" sortable="true" />
						    <display:column property="client.codeClient"  titleKey="label.num_client" sortable="true"/>
						    <display:column property="client.user.etatCompte.libelle"  titleKey="label.etat" sortable="true"/>
						    <display:column property="client.user.nom"  titleKey="label.contact" sortable="true"/>
						    <display:column property="revendeur.user.nom"  titleKey="label.revendeur" sortable="true"/>
						   <display:column title="Action" style="width: 74px;">
						   <a href="<c:url value='/detailCommand?id=${currentObject.id}'/>"> 
									<img border="0" alt="Edit" src="<%=request.getContextPath()%>/img/edit.gif"/></a>
							</display:column>
				</display:table>
		</c:if>
	</div>
</div>
</div>