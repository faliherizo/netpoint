<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />

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
<h2><spring:message code="label.detail_rev"/></h2>
<form:form commandName="editRevendeur" id="revendeur" method="POST" action="/neption/newrevendeur">
	<table>
		<tr>
			<td valign="top">
				<table border="0" width="300">
					<tr>
						<td colspan="3" bgcolor="#CCCCCC">
							<spring:message code="label.coordonnee"/>
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.societe"/></strong></div>
						</td>
						<td>
							<form:input path="societe.nom" />
						</td>
						<td>
			 				<form:errors path="societe.nom" cssClass="error" />
			 			</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.siret"/></strong></div>
						</td>
						<td>
							<form:input path="societe.siret" />							
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.adresse1"/></strong></div>
						</td>
						<td>
							<form:input path="societe.adresse1" />
						</td>
						<td>
							<form:errors path="societe.adresse1" cssClass="error" />					
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><spring:message code="label.adresse2"/></strong></div>
						</td>
						<td>
							<form:input path="societe.adresse2" />
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.codepostal"/></strong></div>
						</td>
						<td>
							<form:input path="societe.codePostal" />
						</td>
						<td>
							<form:errors path="societe.codePostal" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/></strong></div>
						</td>
						<td>
							<form:input path="societe.ville" />
						</td>
						<td>
							<form:errors path="societe.ville" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.pays"/></strong></div>
						
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
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.tva"/></strong></div>
						
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
						<td colspan="3" bgcolor="#CCCCCC">
							<spring:message code="label.contact"/>
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.civilite"/></strong></div>
						
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
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/></strong></div>
						
						</td>
						
						<td>
							<form:input path="user.nom" />
						</td>
						<td>
							<form:errors path="user.nom" cssClass="error" />
						</td>
					</tr>
					<tr>
					
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/></strong></div>
						
						</td>
						<td>
							<form:input path="user.prenom" />
						</td>
						<td>
							<form:errors path="user.prenom" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.fonction"/></strong></div>
						
						</td>
						<td>
							<form:input path="user.fonction" />
						</td>
						<td></td>
					</tr>
					<tr>
						
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.telephone"/></strong></div>
						
						</td>
						<td>
							<form:input path="user.telephone" />
						</td>
						<td>
							<form:errors path="user.telephone" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><spring:message code="label.portable"/></strong></div>
						</td>
						<td>
							<form:input path="user.portable" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.fax"/></strong></div>
						</td>
						<td>
							<form:input path="user.fax" />
						</td>
						<td>							
						</td>
					</tr>
					<tr>
						<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.email"/></strong></div></td>			
						<td>
							<form:input path="user.login" />
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
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/></strong></div></td>
			<td class="col1">
			 <div id="langueerr" class="erreur" style="width: 160px;">
				<form:select path="user.langue.id">
					<form:option value="-1" label="Choisir" />
					 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
				 </form:select>
			</div>
			</td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password"/></strong></div></td>
			<td class="col1">
			    <form:password path="user.pwd" showPassword="true"/>
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
						<td colspan="3" bgcolor="#CCCCCC">
							<spring:message code="label.infosbancaire"/>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.nom_banque"/>
							</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.banque"/>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.code_banque"/>
							</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.codeBanque"/>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.guichet_banque"/>
							</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.codeGuichet"/>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.compte_banque"/>
							</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.numeroCompte"/>
						</td>
						<td>
							
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="form_property"><strong><spring:message code="label.clerib_banque"/>
							</strong></div>
						</td>
						<td>
							<form:input path="compteBancaire.cleRIB"/>
						</td>
						<td>
							
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
						<td colspan="3" bgcolor="#CCCCCC">
							<div class="form_property"><strong><spring:message code="label.console_mondsi"/>
							</strong></div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ratache_a"/>
							</strong></div>
						</td>
						<td>
							<div id="revendeur" class="erreur" style="width: 160px;">
							   <form:select path="revendeur.id">
									<form:option value="-1" label="Choisir" />
									<form:options items="${revendeurList}" itemLabel="user.nom" itemValue="id" />
							    </form:select>
						    </div>
						</td>
						<td>
							
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
					<tr>
						<td>
							<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.datefin"/>
							</strong></div>
						</td>
						<td>
							<div class="demo">
			   					<form:input path="user.dateFin" id="date"/>
			 				</div>
						</td>
						<td>
							<form:errors path="dateFin" cssClass="error" />
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
	<c:if test="${not empty listprodRev}">
		<tr>
			<td colspan="2" bgcolor="#CCCCCC"><spring:message code="label.produit_list"/></td>
		</tr>
		
			<c:forEach var="produitRev" items="${listprodRev}" varStatus="loopA">
		
				<tr>
				   	<td valign="top">${produitRev.produit.nomProduit}</td>
				       <td valign="top">Remise : <input type="text" value="${produitRev.tauxRemise}"/>%</td>
				 </tr>
		  </c:forEach>
</c:if>
		
		
					
	</table>
</td>
<%--
			<td valign="top">
				<table >
					<tr>
						<td colspan="2" bgcolor="#CCCCCC">Produit</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops"/>Tops</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Promotionnel"/>Tops Promotionnel</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Gouvernement"/>Tops Gouvernement</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Gouvernement Promotionnel"/>Tops Gouvernement Promotionnel</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Education"/>Tops Education</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Education Promotionnel"/>Tops Education Promotionnel</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Promo Sequence"/>Tops Promo Sequence</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Special Amadeus Emea"/>Tops Special Amadeus Emea</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Special Amadeus Apac"/>Tops Special Amadeus Apac</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Extended"/>Tops Extended</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Eduction Extended"/>Tops Eduction Extended</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					<tr>
						<td valign="top"><form:checkbox path="" value="Tops Governement Extended"/>Tops Governement Extended</td>
						<td valign="top">Remise : <form:input path=""/>%</td>
					</tr>
					
				</table>
			</td>
	--%>
		</tr>
		<tr>
			<td>
				<table border="0">
					<tr>
					<td>&nbsp;</td>
					<td>
						<div style="margin-left: 0px;"><a href="<c:url value='/UpdateRevendeur?id=${editRevendeur.id}'/>"><input type="button" name="valider" value="<spring:message code="label.modifier"/>" /></a> 
							<a href="/netpoint/listRevendeurs"><input type="button"  name="retour"  value="Annuler" /></a>
						</div>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>	
</div>
</div>