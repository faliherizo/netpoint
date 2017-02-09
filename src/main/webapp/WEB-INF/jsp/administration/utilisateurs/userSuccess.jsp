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
<div class="scrollauto">
<h1>
   <spring:message code="label.usersuccess"/>
</h1>

</div>
</div>