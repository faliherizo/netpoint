<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />

<div id="page">
	<h2></h2>
	
		<div class="scrollauto">
		<form:form commandName="mail" method="POST" action="editmail">
			<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
			<tr>
				<td>
					<spring:message code="label.subject"/>
				</td>
				<td>
					<form:input path="subject" />
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.website"/>
				</td>
				<td>
					<form:input path="website" />
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.societe"/>
				</td>
				<td>
					${mail.societe.nom}
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.mailFrom"/>
				</td>
				<td>
					<form:input path="mailFrom" />
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.telephone"/>
				</td>
				<td>
					<form:input path="phone" />
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="label.contenu"/>
				</td>
				<td>
					<form:textarea path="description" style=""/>
				</td>
			</tr>
			<tr>
				<td>
				  <form:input path="id" type="hidden" />
				 </td>
			</tr>
			<tr class="col1">
				<td>
				  <form:input path="codemail" type="hidden" />
				 </td>
				 <td>
				  <form:input path="contenu" type="hidden" />
				  <form:input path="logo" type="hidden" />
				  <form:input path="type" type="hidden" />
				  <form:input path="societe" type="hidden" />
				  <form:input path="active" type="hidden" />
				  <form:input path="mailhdr.id" type="hidden" />
				 </td>
			</tr>
			<tr>
				<td>
					<div style="margin-left: 0px;"><input type="submit" id="valider" value="Valider" /> 
								<input type="reset"  name="retour"  value="Annuler" />
					</div>
				</td>
			</tr>
		</table>
		</form:form>
	</div>

</div>