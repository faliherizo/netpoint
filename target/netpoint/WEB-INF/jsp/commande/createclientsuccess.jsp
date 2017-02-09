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
							$('#form_client_success').submit();
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
<script language="javascript">
function goback(){
history.back();
}
</script>

<div id="page">
<div class="scrollauto">
		<div id="dialog" title="">
			<p><spring:message code="label.save_client_recap"/></p>
		</div>
	<div id="web_flow"  style="height: 80px;margin-bottom:-5px;width:100%" >
		<div style="margin-left:40px">
			<ul id="flow">
				<li class="depart" style="width:30%;"><h1 class="etape">1</h1></li>
				<li class="intermediaire" style="width:30%;"><h1 class="etape">2</h1></li>
				<li class="fin" style="width:30%;"><h1 class="etape-active">3</h1></li>
			</ul>
			<div id="flow-titles">
				<div class="actif"  style="width:30%;"><spring:message code="label.infos_client"/></div>
				<div class="inactif"  style="width:30%;"><spring:message code="label.choix_solution"/></div>
				<div class="inactif"  style="width:30%;"><spring:message code="label.racapitulation"/></div>
			</div>
		</div>
	</div>
	<div class="recap_client">
	<form:form commandName="command" method="POST" action="newClient?_submit" id="form_client_success">
	
		<table class="table1 table-groupe" cellpadding="3" cellspacing="0" style="width: 50%">
			<tr>
						<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
							Coordonnées
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/>
						</td>
						<td>
							<form:input path="client.societe.nom" readonly="true"/>
						</td>
						<td>
			 				<form:errors path="client.societe.nom" cssClass="error" />
			 			</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.siret"/>
						</td>
						<td>
							<form:input path="client.societe.siret" readonly="true"/>							
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.adresse1"/>
						</td>
						<td>
							<form:input path="client.societe.adresse1" readonly="true"/>
						</td>
						<td>
							<form:errors path="client.societe.adresse1" cssClass="error" />					
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.adresse2" />
						</td>
						<td>
							<form:input path="client.societe.adresse2" readonly="true"/>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.codepostal"/>
						</td>
						<td>
							<form:input path="client.societe.codePostal" readonly="true"/>
						</td>
						<td>
							<form:errors path="client.societe.codePostal" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/>
						</td>
						<td>
							<form:input path="client.societe.ville" readonly="true"/>
						</td>
						<td>
							<form:errors path="client.societe.ville" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.pays"/>
						</td>
						<td>
							<div id="payserr" class="erreur" style="width: 160px;">
								<form:select path="client.societe.pays.id" readonly="true">
									<form:option value="-1" label="Choisir" />
									<form:options items="${paysList}" itemLabel="libelle" itemValue="id"/>
								</form:select>
							</div>
						</td>
						<td>
						</td>
					</tr>			
				<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.civilite"/>
						</td>
						<td>
							<div id="civiliterr" class="erreur" style="width: 160px;">
								<form:select path="client.user.civilite.id" readonly="true">
									<form:option value="-1" label="Choisir" />
									<form:options items="${civiliteList}" itemLabel="libelle" itemValue="id" />									 
								</form:select>
							</div>
							
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/>
						</td>
						<td>
							<form:input path="client.user.nom" readonly="true" />
						</td>
						<td>
							<form:errors path="client.user.nom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/>
						</td>
						<td>
							<form:input path="client.user.prenom" readonly="true" />
						</td>
						<td>
							<form:errors path="client.user.prenom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.fonction"/>
						</td>
						<td>
							<form:input path="client.user.fonction" readonly="true" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.telephone"/>
						</td>
						<td>
							<form:input path="client.user.telephone" readonly="true" />
						</td>
						<td>
							<form:errors path="client.user.telephone" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.portable"/>
						</td>
						<td>
							<form:input path="client.user.portable" readonly="true" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.fax"/>
						</td>
						<td>
							<form:input path="client.user.fax" readonly="true" />
						</td>
						<td>							
						</td>
					</tr>
				<tr>
						<td class="col1"><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/></td>
						<td class="col1">
						 <div id="langueerr" class="erreur" style="width: 160px;">
							<form:select path="client.user.langue.id" readonly="true">
								<form:option value="-1" label="Choisir" />
								 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
							 </form:select>
						</div>
						</td>
					</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.valider"/>" id="valider" /> 
				<input type=button value="Retour" onClick="history.go(-1)"></div>
			</td>
		</tr>
		</table>			
	
		
	</form:form>
	</div>
</div>
</div>