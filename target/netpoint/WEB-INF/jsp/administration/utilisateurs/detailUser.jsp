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
	if (document.getElementById('nomUser').value.trim() == "")
		emptyFields.push('nomusererr');
	
	if (document.getElementById('login').value.trim() == "")
		emptyFields.push('loginerr');
	
	if (document.getElementById('pwd').value.trim() == "")
		emptyFields.push('pwderr');

	if (document.getElementById('pwd2').value.trim() == "")
		emptyFields.push('pwd2err');

	if (document.getElementById('revendeur.id').value == -1)
		emptyFields.push('revendeurerr');

	if (document.getElementById('droit.id').value == -1)
		emptyFields.push('droiterr');

	if (document.getElementById('etatCompte.id').value == -1)
		emptyFields.push('etaterr');

	if (document.getElementById('langue.id').value == -1)
		emptyFields.push('langueerr');

	if (document.getElementById('civilite.id').value == -1)
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
</script>
<div id="page" style="margin-left:10px">
<h2>D&eacute;tails d'un utilisateur</h2>
<div class="scrollauto">
<h1>Utilisateur</h1>
	<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.email"/></strong></div></td>
			<td class="col1">
			${user.login}"
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
				<spring:message code="label.revendeur"/>
			</strong></div></td>
			<td class="col1">
			${user.revendeur.user.nom}
			</td>
		</tr>
			
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.droit"/>
			</strong></div>
			</td>
			<td class="col1">
				${user.profil.libelle}
			 </td>
		</tr>
		
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.etatcompte"/></strong></div></td>
			<td class="col1">
			${user.etatCompte.libelle}
				
			</td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.datefin"/>
			</strong></div>
			</td>
			<td class="col1">
				<fmt:formatDate value="${user.dateFin}" pattern="dd/MM/yyyy"/>
			</td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.langue"/>
			</strong></div>
			</td>
			<td>
			${user.langue.libelle}
			</td>
		</tr>	
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.civilite"/>
			</strong></div>
			</td>
			<td class="col1">
			 ${user.civilite.libelle}
			</td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.nom"/>
			</strong></div>
			</td>
			<td class="col1">
				${user.nom}
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.prenom"/>
			</strong></div>
			</td>
			<td class="col1">
			 	${user.prenom}
			 </td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.fonction"/></strong></div></td>
			<td class="col1">${user.fonction}</td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.telephone"/></strong></div></td>
			<td class="col1">${user.telephone}</td>
		</tr>

		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.portable"/></strong></div></td>
			<td class="col1">${user.portable}</td>
		</tr>
			
	
		

		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><a href="<c:url value='/UpdateUser?id=${user.id}'/>"><input type="button" name="valider" value="Modifier" /> </a>
				<a href="<c:url value='/listUser'/>"><input type="button"  name="retour"  value="Retour" /></a></div>
			</td>
		</tr>

	</table>
</div>
</div>