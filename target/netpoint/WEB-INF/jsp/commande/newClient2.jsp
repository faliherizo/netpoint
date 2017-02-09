<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<c:set scope="request" var="pageName" value="commande"/>
<script type="text/javascript">
			$(function(){

				// Accordion
				$("#accordion").accordion({ header: "h3" });
				$("#accordions").accordion({ header: "h3" });
				// Tabs
				$('#tabs').tabs();
	

				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 300,
					buttons: {
						"Oui": function() { 
							$('#form_client2').submit();
							$(this).dialog("close"); 
						}, 
						"Non": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#valider').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				$('#datepicker').datepicker({
					inline: true
				});
				
				// Slider
				$('#slider').slider({
					range: true,
					values: [17, 67]
				});
				
				// Progressbar
				$("#progressbar").progressbar({
					value: 20 
				});
				
				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				
			});
		</script>

<script type="text/javascript">
$(document).ready(function() { 
	$('#fournisseur').change(function() {
		
				$.getJSON("<c:url value="get_list_produit" />", {
					fournisseur : $(this).val(),
					client : $('#client').val(), ajax : 'true'}, function(data) {
					 $('#product_error').html("<img src='images/loadinginput.gif'></img>");   
					var html = '<option value="">Select</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].nomProduit + '</option>';
					}
					html += '</option>';

					$('#product').html(html);
				});
			});
});

$(function(){
	
	// Datepicker
	$('#datepicker').datepicker({
		inline: true
	});
	
});
</script>




<div id="page">
<div class="scrollauto">
		<div id="dialog" title="">
			<p><spring:message code="label.save_client"/></p>
		</div>
<div id="web_flow"  style="height: 80px;margin-bottom:-5px;width:100%" >
		<div style="margin-left:40px">
			<ul id="flow">
				<li class="depart"  style="width:30%;"><h1 class="etape">1</h1></li>
				<li class="intermediaire"  style="width:30%;"><h1 class="etape-active">2</h1></li>
				<li class="fin"  style="width:30%;"><h1 class="etape">3</h1></li>
			</ul>
			<div id="flow-titles">
				<div class="actif"  style="width:30%;"><spring:message code="label.infos_client"/></div>
				<div class="inactif" style="width:30%;"><spring:message code="label.choix_solution"/></div>
				<div class="inactif"  style="width:30%;"><spring:message code="label.racapitulation"/></div>
			</div>
		</div>
	</div>
	<form:form commandName="command" method="POST" action="newClient?_boncommande" id="form_client2">
		<table style="width: 100%">
				
		<tr>
			<td>
				<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.type_commande"/>
			</td>
			<td>
			
					<form:select id="typeCommande" path="typeCommande.id">
									<form:option value="" label="Choisir" />
									<form:options items="${get_list_typecmd}" itemLabel="libelle" itemValue="id" />
					</form:select>
				
			</td>
			<td>
				 <form:errors path="typeCommande.id" cssClass="error" />
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
			<td>
				 <form:errors path="produit.fournisseur.id" cssClass="error" />
			 </td>
			<td>
			
				<form:select id="product" path="produit.id">
					<form:option value="" label="Select" />
					
				</form:select>
				
			</td>
			<td id="product_error">
				 <form:errors path="produit.id" cssClass="error" />
			 </td>
			<td>
				<form:select path="dureeCommande.id">
					<form:option value="" label="Choisir" />
					<form:options items="${get_duree_list}" itemLabel="duree" itemValue="id" />		
				</form:select>
			</td>
			<td>
				 <form:errors path="dureeCommande.id" cssClass="error" />
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
					<form:input path="numeroCommande"/><form:input path="client.id" type="hidden" id="client" />
			</td>
			<td>
				 <form:errors path="numeroCommande" cssClass="error" />
			 </td>
		</tr>
		
		<tr>
			<td>
					<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nombreposte"/>
			</td>
			<td>
					<form:input path="nombreposte"/>
			</td>
			<td>
				 <form:errors path="nombreposte" cssClass="error" />
			 </td>
		</tr>
		
		<tr>
			<td>
				<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.datedebut"/>
			</td>
			<td>
				<form:input path="dateDebut" id="date" readonly="true" pattern="dd-MM-yyyy" />
			</td>
			<td>
				 <form:errors path="dateDebut" cssClass="error" />
			 </td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.next"/>" id="valider" /> 
				<input type=button value="Retour" onClick="history.back()"></div>
			</td>
		</tr>
		
		</table>
		
	</form:form>
</div>
</div>