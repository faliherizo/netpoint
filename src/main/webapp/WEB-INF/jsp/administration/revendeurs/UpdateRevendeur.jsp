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
<script type="text/javascript">

// load brand list

<!--
var gBox= false;
function closeBox()
{
   if( gBox )
   {
      gBox= false;
      $.colorbox.close();
   }
}

function resetFields() {
	var divs = document.getElementsByTagName('div');
	for (var i=0; i<divs.length; i++) {
		if (divs[i].className == "fielderror")
			divs[i].className = "";
	}
}


function showInfo(aTitle, aMessage, aEnableClose)
{
	if( aEnableClose )
  		$.modaldialog.success(aMessage, {title: aTitle, width: '300px', onClosed: onCloseForm});
	else
   	$.modaldialog.success(aMessage, {title: aTitle, width: '300px'});
}

function showError(aTitle, aMessage, aEnableClose)
{
	if( aEnableClose )
  		$.modaldialog.error(aMessage, {title: aTitle, width: '300px', onClosed: onCloseForm});
	else
   	$.modaldialog.error(aMessage, {title: aTitle, width: '300px'});
}




function emptyfield() {
	var emptyFields = new Array();
	var divs = document.getElementsByTagName('div');
	
	for (var i=0; i<divs.length; i++) {
		if (divs[i].className == "fielderror")
			divs[i].className = "";
	}
	
	// Formulaire principal
	if (document.getElementById('societe.nom').value.trim() == "")
		emptyFields.push('societenomerr');
	
	if (document.getElementById('societe.adresse1').value.trim() == "")
		emptyFields.push('societeadr1err');
	
	if (document.getElementById('societe.codePostal').value.trim() == "")
		emptyFields.push('societecodeerr');

	if (document.getElementById('societe.ville').value.trim() == "")
		emptyFields.push('societevilleerr');

	if (document.getElementById('societe.pays.id').value == -1)
		emptyFields.push('payserr');

	if (document.getElementById('societe.langue.id').value == -1)
		emptyFields.push('languerr');
		
	if (document.getElementById('user.civilite.id').value == -1)
		emptyFields.push('civiliterr');
	
	if (document.getElementById('user.nom').value.trim() == "")
		emptyFields.push('nomerr');
	
	if (document.getElementById('user.prenom').value.trim() == "")
		emptyFields.push('prenomerr');
	if (document.getElementById('user.telephone').value.trim() == "")
		emptyFields.push('telephonerr');
	if (document.getElementById('user.email').value.trim() == "")
		emptyFields.push('emailerr');
	
	if (document.getElementById('compteBancaire.modeReglement.id').value== -1)
		emptyFields.push('reglementerr');
	
	if (document.getElementById('compteBancaire.devise.id').value== -1)
		emptyFields.push('deviserr');
		
	if (document.getElementById('dateFin').value.trim() == "")
		emptyFields.push('dateFinerr');
	
	if (document.getElementById('partnerCode').value.trim() == "")
		emptyFields.push('partnerCodeerr');
		
	if (document.getElementById('partnerPwd').value.trim() == "")
		emptyFields.push('partnerPwderr');
	
	
	for (var i=0; i<emptyFields.length; i++) {
		document.getElementById(emptyFields[i]).className = "fielderror";
	}
	
	return emptyFields;
}


function save() {
    var result = false;
    
    if (emptyfield().length > 0) {
	
    }
    else {

    }
}

function doSubmit()
{
   try
   {
      if(emptyfield().length > 0)
      {
    	  showError("Champs obligatoires! ","Veuillez renseigner tous les champs obligatoires",closeBox());
      }
      else  {
    	  $.modaldialog.prompt('Souhaitez-vous réellement enregistrer cet utilisateur ?', {title: 'Création d\'un utilisateur', width: '400px', height: '50px', callback:submit_user});
      }
   }
   catch(e)
   {
	   alert(e);
   }
}

submit_user = function(response)
{
  if(response){	
	  $('#user').submit();
 }
}


$(function() {
	$( "#datepicker" ).datepicker();
});

</script>
<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			altField: "#alternate",
			altFormat: "DD, d MM, yy"
		});
	});
	jQuery.validator.setDefaults({
		debug: true,
		success: "valid"
	});;
	</script>

	  <script>
	  $(document).ready(function(){
	    $("#revendeur").validate({
	  rules: {
	    field: {
	      required: true,
	      date: true
	    }
	  }
	});
	  });
	  </script>
<div id="page">
<div class="scrollauto">
<c:if test="${droitcreation==true}">

<!-- ui-dialog -->
		<div id="dialog" title="">
			<p><spring:message code="label.confirm_modif_rev"/></p>
		</div>


<h2><spring:message code="label.modif_revendeur"/></h2>
<form:form commandName="revendeur" id="form_revendeur" method="POST">
	<table>
		<tr>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC">
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.coordonnee"/></strong></div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/></strong></div>
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
							<div class="form_property"><strong><spring:message code="label.siret"/></strong></div>
						</td>
						<td>
							<form:input path="societe.siret" />	
							<form:input path="societe.group" type="hidden"/>							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.adresse1"/></strong></div>
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
							<div class="form_property"><strong><spring:message code="label.adresse2"/></strong></div>
						</td>
						<td>
							<form:input path="societe.adresse2" />
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.codepostal"/></strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/></strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.pays"/></strong></div>
						</td>
						<td>
							<div id="payserr" class="erreur" style="width: 160px;">
								<form:select path="societe.pays.id">
									<form:option value="-1" label="Choisir" />
									<form:options items="${paysList}" itemLabel="libelle" itemValue="id" />
								</form:select>
							</div>
						</td>
						<td>
						</td>
					</tr>
						<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/></strong></div></td>
			<td class="col1">
			 <div id="langueerr" class="erreur" style="width: 160px;">
				<form:select path="user.langue.id">
					<form:option value="-1" label="Choisir" />
					 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
				 </form:select>
			</div>
			</td>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.tva"/></strong></div>
						</td>
						<td>
							<form:input path="societe.tvaIntracommunautaire" /><form:input path="societe.id" type="hidden"/>
						</td>
						<td>
						</td>
					</tr>
				</table>			
			</td>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC">
							Contact
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.civilite"/></strong></div>
						</td>
						<td>
							<div id="civiliterr" class="erreur" style="width: 160px;">
								<form:select path="user.civilite.id">
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/></strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/></strong></div>
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
							<div class="form_property"><strong><spring:message code="label.fonction"/></strong></div>
						</td>
						<td>
							<form:input path="user.fonction" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.telephone"/></strong></div>
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
							<div class="form_property"><strong><spring:message code="label.portable"/></strong></div>
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
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.email"/>
							</strong></div>
						</td>
						<td>
							<form:input path="user.login" /><form:input path="user.id" type="hidden"/>
						</td>
						<td>
							<form:errors path="user.login" cssClass="error" />
						</td>
					</tr>
					<tr>
					<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.droit"/></strong></div></td>
						<td class="col1">
						 <div id="profilerr" class="erreur" style="width: 160px;">
						<form:select path="user.profil.id">
							<form:option value="-1" label="Choisir" />
							<form:options items="${profilListRev}" itemLabel="libelle" itemValue="id" />
						</form:select>
						</div>
						</td>
				</tr>
				<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etatcompte"/>
							</strong></div>
						</td>
						<td>
							<div id="clienterr" class="erreur" style="width: 160px;">
							   <form:select path="user.etatCompte.id">
									<form:option value="-1" label="Choisir" />
									<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
							    </form:select>
						    </div>
						</td>
						<td>
							
						</td>
					</tr>
		<%-- <tr>
			<td class="col1"><fmt:message key="monDSI.required.data.symbol"/>Mot de passe</td>
			<td class="col1">
			    <form:password path="user.pwd" showPassword="true"/>
			</td>
			<td>
			 <form:errors path="user.pwd" cssClass="error" />
			 </td>
		</tr>--%>
				</table>				
			</td>
		</tr>



		<tr>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC">
							<spring:message code="label.infosbancaire"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.mode_reglement"/>
							</strong></div>
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
							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.devise_banque"/>
							</strong></div>
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
							
						</td>
					</tr>
					<tr>
						<td>
							<form:input path="compteBancaire.id" type="hidden"/>
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
			<td>
				<table border="0">
					<tr>
					<td>&nbsp;</td>
					<td>
						<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.valider"/>" /> 
							<a href="<c:url value='/listRevendeurs'/>"><input type="button"  name="retour"  value="Annuler" /></a>
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