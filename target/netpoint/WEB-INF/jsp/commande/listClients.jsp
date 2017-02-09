<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/displaytag.css" />
<%@ page isELIgnored ="false" %>

<script type="text/javascript">
	$(function(){
		//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				// Dialog			
				$('#dialog-form-clt').dialog({
					autoOpen: false,
					 height: 400,
					width: 850,
					 buttons: {
						"Valider": function() { 
							//Valider form
								var client_id = $("#dialog-form-clt").data('client_id');
								
							var a=$('#typecommande').val();
							var b= $('#fournisseur').val();
							var c= $('#dureeCommande').val();
							var d= $('#numeroCommande').val();
							var e= $('#nombreposte').val();
							var f= $('#product').val();
							
							if(c=="" || d=="" || a=="" || b=="" || e=="" || f==""){
								if(c==""){
								
									$('#error_typecommande').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
								}
								if(d=="")
								{
									$('#error_fournisseur').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
								}
						    	if(b=="")
						    	{
									$('#error_dureeCommande').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
						    	}
						    	if(a=="")
								{
						    		$('#error_numeroCommande').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
						    	}
								if(e=="")
								{
									$('#error_nombreposte').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
								}
								if(f=="")
								{
									$('#error_product').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
								}
								
							}
							else
							{

								$('#form_command').submit();
								
								
								$(this).dialog("close"); 
							}
						}, 
						Cancel: function() {
						$( this ).dialog( "close" );
						}
						},
						close: function() {
						allFields.val( "" ).removeClass( "ui-state-error" );
						}  
							});
							
	});
	
function ShowForm(client_id, type){
	//Set id_client in link to get in form 
	document.getElementById('id_client').value =client_id;
	document.getElementById('typecommande').value= type;
	$( "#dialog-form-clt" ).data("client_id", client_id).dialog( "open" );
}
</script>
<c:set scope="request" var="pageName" value="commande"/>
<div id="page">
<div class="scrollauto">
<div id="dialog-form-clt" title="">
		<form:form commandName="command" method="POST" action="newCommand" id="form_command">
		<table style="width: 100%">
				
		<tr>
			<td>
				<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.type_commande"/>
			</td>
			<td>
			
					<form:select id="typecommande" path="typeCommande.id">
									<form:option value="" label="Choisir" />
									<form:options items="${get_list_typecmd}" itemLabel="libelle" itemValue="id" />
					</form:select>
				
			</td>
			<td id="error_typecommande">
				
			 </td>
		</tr>
		
		<tr>
			<td>
				<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.frns_produit_duree"/>
			</td>
			<td>
			
				<form:select id="fournisseur" path="produit.fournisseur.id">
					<form:option value="" label="Choisir" />
					<form:options items="${get_frns_list}" itemLabel="nom" itemValue="id" />	
				</form:select> 
				
			</td>
			<td id="error_fournisseur">
				
			 </td>
			<td>
			
				<form:select id="product" path="produit.id">
					<form:option value="" label="Choisir" />
					<form:options items="${listproduit}" itemLabel="nomProduit" itemValue="id" />	
				</form:select>
				
			</td>
			<td id="error_product">
				
			 </td>
			<td>
				<form:select path="dureeCommande.id" id="dureeCommande">
					<form:option value="" label="Choisir" />
					<form:options items="${get_duree_list}" itemLabel="duree" itemValue="id" />		
				</form:select>
			</td>
			<td id="error_dureeCommande">
				
			 </td>
		</tr>
			<!--
			
			
		
		
		</tr>
		 -->
		<tr>
		
			<td>
					<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.numerocmd"/>
			</td>
			<td>
					<form:input path="numeroCommande"/>
			</td>
			<td id="error_numeroCommande">
				 
			 </td>
		</tr>
		
		<tr>
			<td>
					<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nombreposte"/>
			</td>
			<td>
					<form:input path="nombreposte"/>
			</td>
			<td id="error_nombreposte">
				 
			 </td>
		</tr>
		
		<tr>
			<td>
				<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.datedebut"/>
			</td>
			<td>
				<form:input path="dateDebut" id="date" />
			</td>
			<td id="error_dateDebut">
				 
			 </td>
		</tr>
		<tr>
		<td id="id_clients">
			 <form:input path="client.id" id="id_client" type="hidden" />
			 </td>
		</tr>
		</table>
		
	</form:form>
</div>
<h2><spring:message code="label.listcustomer"/></h2>
<div id="page_search">
<!--
  <div style=" margin-bottom:10px;font-size:16px;font-weight:bold;" >Liste des client</div> -->
	      <form:form action="listClients" commandName="searchCriteria" name="searchForm" method="GET" id="form_searchCriteria">
			 <table style="width:100%;" >
					 <tr>
					    <td style="width:50%;">
					    <table style="border: 1px solid gray;width:100%;">
					    <tr>
						    <td class="col1"><spring:message code="label.societe"/></td>
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
						    <td class="col1"><spring:message code="label.etatcompte"/></td>
								<td class="col1">
									 <div id="clienterr" class="erreur" style="width: 160px;">
									   <form:select path="etat">
											<form:option value="" label="Choisir" />
											<form:options items="${etatCompte}" itemLabel="libelle" itemValue="id" />
									    </form:select>
								    </div>
								</td>
					    </tr>
					    
					     <tr>
						    <td class="col1"><spring:message code="label.email"/></td>
							 <td class="col1"> <form:input path="email" type="text"/>
							</td>
					    </tr>
					    
					    <tr>
						    <td class="col1"><spring:message code="label.siret"/></td>
							 <td class="col1"> <form:input path="siret" type="text"/>
							</td>
					    </tr>
					    
					     <tr>
						    <td class="col1"><spring:message code="label.rcs"/></td>
							 <td class="col1"> <form:input path="rcs" type="text"/>
							</td>
					    </tr>
					    
					    <tr>
						    <td class="col1"><spring:message code="label.num_client"/></td>
							<td class="col1">
								 <form:input path="num_client" type="text"/>
							</td>
					    </tr>
					    
					    </table>
					    </td>
						<td style="width:50%;">
							 <table style="border: 1px solid gray;width:100%;">
							    <tr>
								    <td class="col1"><spring:message code="label.revendeur"/></td>
									<td class="col1">
								 <form:select path="id_revendeur">
									<form:option value="" label="Choisir" />
										<form:options items="${listRevendeur}" itemLabel="user.nom" itemValue="id" />
									</form:select>
									</td>
							    </tr>
							    
							     <tr>
								    <td class="col1"><spring:message code="label.numcustomerrev"/> </td>
									<td class="col1">
									 <input id="client_n" 	name="num_client_revendeur" type="text">
									</td>
					    		</tr>
					    		
					    		<tr>
								    <td class="col1"><spring:message code="label.cutomerkey"/> </td>
									<td class="col1">
									  <form:input path="customer_key" type="text"/>
									</td>
					    		</tr>
					    		
					    		<tr>
									<td class="col1"><spring:message code="label.nom"/></td>
									<td class="col1">
									<form:input path="nom" type="text"/>
									</td>
								</tr>
								<tr>
									<td class="col1"><spring:message code="label.prenom"/></td>
									<td class="col1">
									<form:input path="prenom" type="text"/>
									</td>
								</tr>
								<tr>
									<td class="col1"><spring:message code="label.telephone"/></td>
									<td class="col1">
									<form:input path="phone" type="text"/>
									</td>
								</tr>
					    
						    </table>
						</td>
					 </tr>
	        </table>
	        <input type="submit" name="valider" value="<spring:message code="label.rechercher"/>" />
		 </form:form>
      </div>
      
        <div class="resultat" >
        <c:if test="${!empty clients}">
		<display:table name="${clients}" requestURI="listClients"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="client.societe.nom" title="Societe" sortable="true" />
				    <display:column titleKey="label.num_client" sortable="true">
				    <a href="detailClient?id=${currentObject.client.id}">${currentObject.client.codeClient}</a> 
				    </display:column>
				    <display:column property="client.user.etatCompte.libelle"  titleKey="label.etat" sortable="true"/>
				    <display:column property="client.user.nom"  titleKey="label.contact" sortable="true"/>
				    <display:column property="revendeur.user.nom"  titleKey="label.revendeur" sortable="true"/>
				   <display:column titleKey="label.order" style="width: 74px;">
					<a href="#" onclick="ShowForm(${currentObject.client.id}, 1)">O</a> 
					</display:column>
					<display:column titleKey="label.essaie" style="width: 74px;">
					<a href="#" onclick="ShowForm(${currentObject.client.id}, 2)">O</a> 
					</display:column>
		</display:table>

		</c:if>
	</div>	
</div>
</div>