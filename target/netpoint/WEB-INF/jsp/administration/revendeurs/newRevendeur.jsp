<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />
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
							$('#form_revendeur').submit();
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

<div id="page">
	<div class="scrollauto">
<c:if test="${droitcreation==true}">

<!-- ui-dialog -->
		<div id="dialog" title="">
			<p><spring:message code="label.save_confirmation_rev"/></p>
		</div>
<h2><spring:message code="label.titre_rev"/></h2>
<form:form commandName="revendeur" id="form_revendeur" method="POST">
	<table>
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
						<div class="form_property"><strong>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/> :
							</strong></div>
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
						<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.siret"/> :</strong></div>
						</td>
						<td>
							<form:input path="societe.siret" />							
						</td>
						<td>
							<form:errors path="societe.nom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.adresse1"/> :
							</strong></div>
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
							<div class="form_property"><strong><spring:message code="label.adresse2"/> :</strong></div>
						</td>
						<td>
							<form:input path="societe.adresse2" />
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.codepostal"/> : 
							</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/> :
							</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.pays"/> :
							</strong></div>
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
						<td>
							<div class="form_property"><strong><spring:message code="label.tva"/> :</strong></div>
						</td>
						<td>
							<form:input path="societe.tvaIntracommunautaire" />
						</td>
						<td>
						</td>
					</tr>
				</table>			
			</td>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<div class="form_property"><strong><spring:message code="label.contact"/></strong></div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.civilite"/> :
							</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/> :
							</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/> :
							</strong></div>
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
							<div class="form_property"><strong><spring:message code="label.fonction"/> :
							</strong></div>
						</td>
						<td>
							<form:input path="user.fonction" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.telephone"/> :
							</strong></div>
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
							<div class="form_property"><strong><spring:message code="label.portable"/> :</strong></div>
						</td>
						<td>
							<form:input path="user.portable" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.fax"/></strong></div>
						</td>
						<td>
							<form:input path="user.fax" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.email"/> :
							</strong></div>
						</td>
						<td>
							<form:input path="user.login" />
						</td>
						<td>
							<form:errors path="user.login" cssClass="error" />
						</td>
					</tr>
					<%-- 
					<tr>
					<td class="col1">
					<div class="form_property"><strong>
					<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.droit"/> :
					</strong></div>
					</td>
						<td class="col1">
							 <div id="profilerr" class="erreur" style="width: 160px;">
								<form:select path="user.profil.id">
									<form:option value="" label="Choisir" />
									<form:options items="${profilListRev}" itemLabel="libelle" itemValue="id" />
								</form:select>
							</div>
						</td>
				</tr>--%>
					<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/> :</strong>
			</div>
			</td>
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
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password"/> :
			</strong></div>
			</td>
			<td class="col1">
			    <form:password path="user.pwd" />
			</td>
			<td>
			 <form:errors path="user.pwd" cssClass="error" />
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
							<spring:message code="label.infosbancaire"/>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom_banque"/> :</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.code_banque"/> :</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.guichet_banque"/> :</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.compte_banque"/> :</strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.clerib_banque"/> :</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.cleRIB"/>
						</td>
						<td>
							<form:errors path="compteBancaire.cleRIB" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.mode_reglement"/></strong></div>
						</td>
						<td>
							<div id="reglementerr" class="erreur" style="width: 160px;">
								<form:select path="compteBancaire.modeReglement.id">
									<form:option value="" label="Choisir" />
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.devise_banque"/> :
							</strong></div>
						</td>
						<td>
							<div id="deviserr" class="erreur" style="width: 160px;">
								<form:select path="compteBancaire.devise.id">
									<form:option value="" label="Choisir" />
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
			</td>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							<spring:message code="label.console_mondsi"/>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.ratache_a"/> :
							</strong></div>
						</td>
						<td>
							<div id="revendeur" class="erreur" style="width: 160px;">
							   <form:select path="revendeur.id">
									<form:option value="" label="Choisir" />
									<form:options items="${revendeurList}" itemLabel="user.nom" itemValue="id" />
							    </form:select>
						    </div>
						</td>
						<td>
							
						</td>
					</tr>
			<!-- 	<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/>Zone :
						</td>
						
						<td>
							B
						</td>
					</tr> -->	
					<tr>
						<td>
							<div class="form_property"><strong>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etatcompte"/> :
							</strong></div>
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
							<div class="form_property"><strong>
							<spring:message code="label.autorisation_creation_rev"/> :
							</strong></div>
						</td>
						<td>
							<form:checkbox path="droitCreationRevendeur" value="1"/> 
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.validation_rev"/> :</strong></div>
						
						</td>
						<td>
							<form:input name="date" path="user.dateFinChar" id="date"/>
						</td>
						<td>
							<form:errors path="user.dateFinChar" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.partener_pwd"/> :
							</strong></div>
						</td>
						<td>
							<form:input path="partnerCode"/>
						</td>
						<td>
							<form:errors path="partnerCode" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.partener_code"/> :
							</strong></div>
						</td>
						<td>
							<form:input path=""/>
						</td>
						<td>
							<form:errors path="" cssClass="error" />
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
			</td>
		</tr>
		<tr>
 
<td valign="top">
	<table>
		<tr>
			<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center "><spring:message code="label.produit_list"/></td>
		</tr>
		<c:forEach var="produit" items="${listproduit}" varStatus="loopA">
		
		<tr>
		   	<td valign="top"><form:checkbox 
		       path="produitRevendeurs[${loopA.index}].produit.id" 
		       value="${produit.id}"></form:checkbox>${produit.nomProduit}</td>
		       <td valign="top">Remise : <form:input path="produitRevendeurs[${loopA.index}].tauxRemise"/>%</td>
		 </tr>
		  </c:forEach>
		
					
	</table>
</td>

		</tr>
		<tr>
			<td>
				<table border="0">
					<tr>
					<td>&nbsp;</td>
					<td>
						<div style="margin-left: 0px;"><input type="submit" id="valider" value="<spring:message code="label.valider"/>" /> 
							<input type="reset"  name="retour"  value="Annuler" />
						</div>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>	
</c:if>

<c:if test="${droitcreation==false}">
<h1><spring:message code="droitcreationrev"/></h1>
</c:if>
</div>
</div>