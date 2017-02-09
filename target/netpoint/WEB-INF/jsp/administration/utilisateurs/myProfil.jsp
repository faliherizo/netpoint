<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<c:set scope="request" var="pageName" value="profil"/>
<div id="page">
<h2><spring:message code="label.myprofil"/></h2>
<div class="scrollauto">
<form:form commandName="user" method="POST" action="myProfil">
	<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.civilite"/></strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
			 <div id="civiliteerr" class="error" style="width: 160px;">			
				  <form:select path="civilite.id">
							<form:option value="" label="Choisir" />
							<form:options items="${civiliteList}" itemLabel="libelle" itemValue="id" />
				  </form:select>
			  </div>
			</td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.email"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			  <form:input path="login" style="border:0px" readonly="true"/>
			 </td>
			 <td>
			 <form:errors path="login" cssClass="error" />
			 </td>
			  <td class="col1">
			  <form:input path="id" type="hidden" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.langue"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			 <div id="langueerr" class="erreur" style="width: 160px;">
				<form:select path="langue.id">
					<form:option value="-1" label="Choisir" />
					 <form:options items="${langueList}" itemLabel="libelle" itemValue="id" />
				 </form:select>
			</div>
			</td>
		</tr>
		
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.recevoir_nwsletre"/>
			</strong></div> :</td>
			<td class="col1"><form:checkbox path="newslettre"/></td>
		</tr>
		
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
				<spring:message code="label.modif_pwd"/>
			</strong></div> :
			</td>
			<td class="col1">
			    	<form:checkbox path="changepwd"/>
			</td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong>
			
				<spring:message code="label.password"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			    <form:password path="pwd" showPassword="true" />
			</td>
			<td>
			 <form:errors path="pwd" cssClass="error" showPassword="true" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.password2"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			  <form:password path="pwd2" showPassword="true"/>
			</td>
			<td>
			 <form:errors path="pwd2" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.nom"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			  <form:input path="nom" />
			 </td>
			 <td>
			 <form:errors path="nom" cssClass="error" />
			 </td>
		</tr>
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.prenom"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			   <form:input path="prenom" />
			 </td>
			 <td>
			 <form:errors path="prenom" cssClass="error" />
			 </td>
		</tr>
				
		<tr>
			<td class="col1">
			<div class="form_property"><strong>
			<spring:message code="label.droit"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/>
			</td>
			<td class="col1">
			<!--<form:select path="profil.id" readonly="true">
				<form:option value="" label="Choisir" />
				<form:options items="${profilList}" itemLabel="libelle" itemValue="id" />
			</form:select>-->
			<form:input path="profil.id" type="hidden"/>
			<form:input path="profil.libelle" readOnly="true" border="none"/>
			</td>
		</tr>

		
		
		<tr>
			<td class="col1">
			<div class="form_property"><strong><spring:message code="label.etatcompte"/>
			</strong></div> :<fmt:message key="monDSI.required.data.symbol"/></td>
			<td class="col1">
				 <div id="clienterr" class="erreur" style="width: 160px;">
				   <form:select path="etatCompte.id" readonly="true">
						<form:option value="-1" label="Choisir" />
						<form:options items="${etatCompteList}" itemLabel="libelle" itemValue="id" />
				    </form:select>
			    </div>
			</td>
		</tr>
		
		<tr>
			<td class="col1"><div class="form_property"><strong><spring:message code="label.revendeurassocie"/>
			</strong></div> :</td>
			<td class="col1">
			 <div id="revendeurerr" class="erreur" style="width: 160px;">
				<form:select path="revendeur.id">
					<form:option value="" label="Choisir" />
					<form:options items="${revendeurList}" itemLabel="user.nom" itemValue="id" />
				</form:select>
			</div>
			</td>
		</tr>
		<tr>
			<td class="col1"><form:input path="fonction" type="hidden"/></td>
		</tr>
		<tr>
			
			<td class="col1"><form:input path="telephone" type="hidden"/></td>
		</tr>

		<tr>
			
			<td class="col1"><form:input path="portable" type="hidden" /></td>
			
		</tr>


		<tr>
		
			<td class="col1"><form:input path="fax" type="hidden"/></td>
		 	<form:input type="hidden" name="date" path="dateFin" id="date"/>
		</tr>
		<tr>
			
			<td class="col1">

			   <form:input type="hidden" name="datepicker" path="dateFin" id="date"/>

			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="<spring:message code="label.valider"/>" value="Valider" /> 
				<a href="listUser"><input type="reset"  name="retour"  value="<spring:message code="label.retour"/>" /></a></div>
			</td>
		</tr>
		
		
	</table>
	
</form:form>
</div>
</div>