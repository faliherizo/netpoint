<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<c:set scope="request" var="pageName" value="client"/>
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
							$('#form_client1').submit();
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
function emptyfield() {

	if (document.getElementById('cleRIB').value.length > 2){
		alert("Clés rib doit conténir seulement deux chiffres");
		document.getElementById('cleRIB').focus();
	}
	
	
}
</script>

<div class="scrollauto">
<div id="page">
		<div id="dialog" title="">
			<p><spring:message code="label.save_client"/></p>
		</div>
<h2><spring:message code="label.create_client"/></h2>
  <div id="web_flow"  style="height: 80px;margin-bottom:-5px;width:100%" >
		<div style="margin-left:40px">
			<ul id="flow">
				<li class="depart" style="width:30%;" ><h1 class="etape-active" >1</h1></li>
				<li class="intermediaire" style="width:30%;" ><h1 class="etape">2</h1></li>
				<li class="fin" style="width:30%;" ><h1 class="etape">3</h1></li>
			</ul>
			<div id="flow-titles">
				<div class="actif" style="width:30%;"><spring:message code="label.infos_client"/></div>
				<div class="inactif" style="width: 30%;"><spring:message code="label.choix_solution"/></div>
				<div class="inactif" style="width: 30%;"><spring:message code="label.racapitulation"/></div>
			</div>
		</div>
	</div>
	<form:form commandName="client" method="POST" action="newClient?_page=2" id="form_client1">
	<table style="width: 100%">
		<tr>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<spring:message code="label.coordonnee"/>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/>
						</td>
						<td>
							<form:input path="societe.nom" />
						</td>
						<td>
			 				<form:errors path="societe.nom" cssClass="error" />
			 			</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.siret"/>
						</td>
						<td>
							<form:input path="societe.siret" />							
						</td>
						<td>
							<form:errors path="societe.siret" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.rcs"/>
						</td>
						<td>
							<form:input path="rcs" />							
						</td>
						<td>
							<form:errors path="rcs" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.adresse1"/>
						</td>
						<td>
							<form:input path="societe.adresse1" />
						</td>
						<td>
							<form:errors path="societe.adresse1" cssClass="error" />					
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.adresse2"/>
						</td>
						<td>
							<form:input path="societe.adresse2" />
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.codepostal"/>
						</td>
						<td>
							<form:input path="societe.codePostal" />
						</td>
						<td>
							<form:errors path="societe.codePostal" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/>
						</td>
						<td>
							<form:input path="societe.ville" />
						</td>
						<td>
							<form:errors path="societe.ville" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.pays"/>
						</td>
						<td>
							<div id="payserr" class="erreur" style="width: 160px;">
								<form:select path="societe.pays.id">
									<form:option value="" label="Choisir" />
									<form:options items="${paysList}" itemLabel="libelle" itemValue="id" />
								</form:select>
							</div>
						</td>
						<td>
							<form:errors path="societe.pays.id" cssClass="error" />
						</td>
					</tr>			
					<tr>
						<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/></td>
						<td class="col1">
						 <div id="langueerr" class="erreur" style="width: 160px;">
							<form:select path="user.langue.id">
								<form:option value="" label="Choisir" />
								 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
							 </form:select>
						</div>
						</td>
						<td>
							<form:errors path="user.langue.id" cssClass="error" />
						</td>
					</tr>
				</table>			
			</td>
			<td valign="top">
			
			<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<spring:message code="label.infosuppl"/>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.email"/>
						</td>
						<td>
							<form:input path="user.login" />
						</td>
						<td>
							<form:errors path="user.login" cssClass="error" />
						</td>
					</tr>
				<tr>
					<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password"/></td>
					<td class="col1">
					    <form:password path="user.pwd" />
					</td>
					<td>
					 <form:errors path="user.pwd" cssClass="error" />
					 </td>
				</tr>
					
				<tr>
				<td class="col1">
					<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password2"/></td>
					<td class="col1">
						    <form:password path="user.pwd2" />
						</td>
						<td>
						 <form:errors path="user.pwd2" cssClass="error" />
					 </td>
				</tr>
				<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etat"/>
						</td>
						<td>
							<div id="clienterr" class="erreur" style="width: 160px;">
							   <form:select path="user.etatCompte.id">
									<form:option value="" label="Choisir" />
									<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
							    </form:select>
						    </div>
						</td>
						
						<td>
					 		<form:errors path="user.etatCompte.id" cssClass="error" />
					 </td>
					</tr>
					
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.numero_revendeur"/>
						</td>
						<td>
							<div id="clienterr" class="erreur" style="width: 160px;">
							   <form:select path="numeroRevendeur">
									<form:option value="" label="Choisir" />
									<form:options items="${revendeurList}" itemLabel="codeRevendeur" itemValue="codeRevendeur" />
							    </form:select>
						    </div>
						</td>
						<td>
							<form:errors path="numeroRevendeur" cssClass="error" />
						</td>

					</tr>
				</table>				
			
					
			</td>
		</tr>
			
		<tr>
			<td valign="top">
				
				
			
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<spring:message code="label.contact"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.civilite"/>
						</td>
						<td>
							<div id="civiliterr" class="erreur" style="width: 160px;">
								<form:select path="user.civilite.id">
									<form:option value="" label="Choisir" />
									<form:options items="${civiliteList}" itemLabel="libelle" itemValue="id" />									 
								</form:select>
							</div>
							
						</td>
						<td>
							<form:errors path="user.civilite.id" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/>
						</td>
						<td>
							<form:input path="user.nom" />
						</td>
						<td>
							<form:errors path="user.nom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/>
						</td>
						<td>
							<form:input path="user.prenom" />
						</td>
						<td>
							<form:errors path="user.prenom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.fonction"/>
						</td>
						<td>
							<form:input path="user.fonction" />
						</td>
						
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.telephone"/>
						</td>
						<td>
							<form:input path="user.telephone" />
						</td>
						<td>
							<form:errors path="user.telephone" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.portable"/>
						</td>
						<td>
							<form:input path="user.portable" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.fax"/>
						</td>
						<td>
							<form:input path="user.fax" />
						</td>
						<td>							
						</td>
					</tr>
				
				</table>		
				
				
				
				
				
			</td>
			<td valign="top">
			<%--- 
					<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<spring:message code="label.infosbancaire"/>
						</td>
					</tr>
					<tr>
						<td>
						<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom_banque"/>	
						</td>
						<td>
							<form:input path="compteBancaire.banque"/>
						</td>
						<td>
							<form:errors path="compteBancaire.banque" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.code_banque"/>
						</td>
						<td>
							<form:input path="compteBancaire.codeBanque"/>
						</td>
						<td>
							<form:errors path="compteBancaire.codeBanque" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.guichet_banque"/>
						</td>
						<td>
							<form:input path="compteBancaire.codeGuichet"/>
						</td>
						<td>
							<form:errors path="compteBancaire.codeGuichet" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.compte_banque"/>
						</td>
						<td>
							<form:input path="compteBancaire.numeroCompte"/>
						</td>
						<td>
							<form:errors path="compteBancaire.numeroCompte" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.clerib_banque"/>
						</td>
						<td>
							<form:input path="compteBancaire.cleRIB" id="cleRIB"/>
						</td>
						<td>
							<form:errors path="compteBancaire.cleRIB" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.reglement_banque"/>
						</td>
						<td>
							<div id="reglementerr" class="erreur" style="width: 160px;">
								<form:select path="compteBancaire.modeReglement.id">
									<form:option value="-1" label="Choisir" />
									<form:options items="${modeReglementList}" itemLabel="libelle" itemValue="id" />
								</form:select>
							</div>
						</td>
						<td>
							<form:errors path="compteBancaire.modeReglement.id" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.devise_banque"/>
						</td>
						<td>
							<div id="deviserr" class="erreur" style="width: 160px;">
								<form:select path="compteBancaire.devise.id">
									<form:option value="-1" label="Choisir" />
									<form:options items="${deviseList}" itemLabel="libelle" itemValue="id" />
								</form:select>
							</div>
						</td>
						<td>
							<form:errors path="compteBancaire.devise.id" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</table>			
			--%>
			</td>
		</tr>
		
		
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.next"/>" id="valider" /> 
				<a href="listUser"><input type="button"  name="retour"  value="Annuler" /></a></div>
			</td>
		</tr>
		</table>			
	
	
	
	</form:form>
</div>
</div>