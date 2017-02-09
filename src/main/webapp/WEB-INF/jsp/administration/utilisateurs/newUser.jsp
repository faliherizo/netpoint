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
							$('#form_user').submit();
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
<h2><spring:message code="label.create_user"/></h2>
<div class="scrollauto">
<form:form commandName="user" id="form_user" method="POST" >
<!-- ui-dialog -->
		<div id="dialog" title="">
			<p><spring:message code="label.save_user"/></p>
		</div>

	<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.email"/></strong></div> : <fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			 <form:input path="login" />
			 </td>
			 <td>
			 <form:errors path="login" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.password"/> </strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			    <form:password path="pwd" />
			</td>
			<td>
			 <form:errors path="pwd" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.password2"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>

			<td class="col1">
			 <form:password path="pwd2" />
			</td>
			<td>
			 <form:errors path="pwd2" cssClass="error" />
			 </td>
		</tr>
		
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.nom"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			  <form:input path="nom" />
			 </td>
			 <td>
			 <form:errors path="nom" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
				<div class="form_property"><strong><spring:message code="label.prenom"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			   <form:input path="prenom" />
			 </td>
			 <td>
			 <form:errors path="prenom" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.fonction"/></strong></div> :</td>
			<td class="col1"><form:input path="fonction" /></td>
			 <td><form:errors path="fonction" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.telephone"/></strong></div> :</td>
			<td class="col1"><form:input path="telephone" /></td>
			<td><form:errors path="telephone" cssClass="error" /></td>
		</tr>

		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.portable"/></strong></div> :</td>
			<td class="col1"><form:input path="portable" /></td>
			<td><form:errors path="portable" cssClass="error" /></td>
		</tr>

		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.fax"/></strong></div> :</td>
			<td class="col1"><form:input path="fax" /></td>
			<td><form:errors path="fax" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.revendeurassocie"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
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
			<td class="col1"><div class="form_property"><strong>
			<spring:message code="label.datefin"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			   <form:input name="date" path="dateFinChar" id="date"/>
			</td>
			<td><form:errors path="dateFinChar" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong>
			<spring:message code="label.droit"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
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
			<spring:message code="label.langue"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
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
			<div class="form_property"><strong>
			<spring:message code="label.civilite"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
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
			<spring:message code="label.etatcompte"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
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
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.ajouter"/>" id="valider" /> 
				<a href="listUser"><input type="button"  name="retour"  value="<spring:message code="label.annuler"/>" /></a></div>
			</td>
		</tr>

	</table>
</form:form>
</div>
</div>