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
						"Ok": function() { 
							$('#form_user').submit();
							$(this).dialog("close"); 
						}, 
						"Cancel": function() { 
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
	if (document.getElementById('nomUser').value.trim() == "")
		emptyFields.push('nomusererr');
	
	if (document.getElementById('login').value.trim() == "")
		emptyFields.push('loginerr');
	
	if (document.getElementById('pwd').value.trim() == "")
		emptyFields.push('pwderr');

	if (document.getElementById('pwd2').value.trim() == "")
		emptyFields.push('pwd2err');

	if (document.getElementById('revendeur.id').value == "")
		emptyFields.push('revendeurerr');

	if (document.getElementById('droit.id').value == "")
		emptyFields.push('droiterr');

	if (document.getElementById('etatCompte.id').value == "")
		emptyFields.push('etaterr');

	if (document.getElementById('langue.id').value == "")
		emptyFields.push('langueerr');

	if (document.getElementById('civilite.id').value == "")
		emptyFields.push('civiliteerr');
	
	if (document.getElementById('prenom').value.trim() == "")
		emptyFields.push('prenomerr');
	
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
		if(document.getElementById('pwd').value!=document.getElementById('pwd2').value)
	});
	jQuery.validator.setDefaults({
		debug: true,
		success: "valid"
	});;
	</script>

	  <script>
	  $(document).ready(function(){
	    $("#user").validate({
	  rules: {
		datefin: {
	      required: true,
	      date: true
	    }
	  }
	});
	  });
	  </script>
<div id="page">
<h2>Modification d'un utilisateur</h2>
<div class="scrollauto">
<form:form commandName="user" id="form_user" method="POST">
<!-- ui-dialog -->
		<div id="dialog" title="">
			<p>Vous voullez enregistrer cet utilisateur?.</p>
		</div>

	<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.email"/></strong></div></td>
			<td class="col1">
			  <form:input path="login" type="email" readonly="true"/>
			 </td>
			 <td>
			 <form:errors path="login" cssClass="error" />
			  <form:input path="id" type="hidden" />
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.modif_password"/></strong></div></td>
			<td class="col1">
				<form:checkbox path="changepwd"/>
			    
			</td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password"/></strong></div></td>
			<td class="col1">
			    <form:password path="pwd" showPassword="true" />
			</td>
			<td>
			 <form:errors path="pwd" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.password2"/></strong></div></td>
			<td class="col1">
			  <form:password path="pwd2" showPassword="true"/>
			</td>
			<td>
			 <form:errors path="pwd2" cssClass="error" />
			 </td>
		</tr>
		
			<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.nom"/>
			</strong></div></td>
			<td class="col1">
			  <form:input path="nom" />
			 </td>
			 <td>
			 <form:errors path="nom" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.prenom"/>
			</strong></div></td>
			<td class="col1">
			   <form:input path="prenom" />
			 </td>
			 <td>
			 <form:errors path="prenom" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.fonction"/></strong></div></td>
			<td class="col1"><form:input path="fonction" /></td>
			 <td><form:errors path="fonction" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.telephone"/></strong></div></td>
			<td class="col1"><form:input path="telephone" /></td>
			<td><form:errors path="telephone" cssClass="error" /></td>
		</tr>

		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.portable"/></strong></div></td>
			<td class="col1"><form:input path="portable" /></td>
			<td><form:errors path="portable" cssClass="error" /></td>
		</tr>

		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.fax"/></strong></div></td>
			<td class="col1"><form:input path="fax" /></td>
			<td><form:errors path="fax" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.revendeurassocie"/></strong></div></td>
			<td class="col1">
			 <div id="revendeurerr" class="error" style="width: 160px;">
				<form:select path="revendeur.id" id="revendeur">
					<form:option value="" label="Choisir" />
					<form:options items="${revendeurList}" itemLabel="user.nom" itemValue="id" />
				</form:select>
			</div>
			</td>
			<td><form:errors path="revendeur.id" cssClass="error" /></td>
			
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/>
			<spring:message code="label.datefin"/></strong></div></td>
			<td class="col1">
			   <form:input name="date" path="dateFin" id="date"/>
			</td>
			<td><form:errors path="dateFinChar" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/>
			<spring:message code="label.droit"/></strong></div></td>
			<td class="col1">
			 <div id="profilerr" class="erreur" style="width: 160px;">
			<form:select path="profil.id">
				<form:option value="" label="Choisir" />
				<form:options items="${profilListUser}" itemLabel="libelle" itemValue="id" />
			</form:select>
			</div>
			</td>
			<td><form:errors path="profil.id" cssClass="error" /></td>
		</tr>

		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.langue"/></strong></div></td>
			<td class="col1">
			 <div id="langueerr" class="error" style="width: 160px;">
				<form:select path="langue.id">
					<form:option value="" label="Choisir" />
					 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
				 </form:select>
			</div>
			</td>
			<td><form:errors path="langue.id" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong><fmt:message key="monDSI.required.data.symbol"/>
			<spring:message code="label.civilite"/></strong></div></td>
			<td class="col1">
			 <div id="civiliteerr" class="error" style="width: 160px;">			
				  <form:select path="civilite.id">
							<form:option value="" label="Choisir" />
							<form:options items="${civiliteList}" itemLabel="libelle" itemValue="id" />
				  </form:select>
			  </div>
			</td>
			<td><form:errors path="civilite.id" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etatcompte"/></div></</td>
			<td class="col1">
				 <div id="clienterr" class="erreur" style="width: 160px;">
				   <form:select path="etatCompte.id">
						<form:option value="" label="Choisir" />
						<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
				    </form:select>
			    </div>
			</td>
			<td><form:errors path="etatCompte.id" cssClass="error" /></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="Ajouter" id="valider" /> 
				<a href="listUser"><input type="button"  name="retour"  value="Retour" onClick="history.back()" /></a></div>
			</td>
		</tr>

	</table>
</form:form>
</div>
</div>