<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />

<div id="page">
	<h2><spring:message code="label.creation_product"/></h2>
	
		<div class="scrollauto">
			<form:form commandName="produit" id="form_produit" method="POST">
			<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
				<tr>
					<td class="col1"><label for="nomProduit"><spring:message code="label.produit"/> :</label></td>
					<td class="col1"><form:input path="nomProduit" /></td>
					<td><form:errors path="nomProduit" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="col1"><spring:message code="label.url"/> :</td>
					<td class="col1"><form:input path="url"/> </td>
					<td><form:errors path="url" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="col1"><spring:message code="label.sku"/> :</td>
					<td class="col1"><form:input path="sku" /> </td>
					<td><form:errors path="sku" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="col1"><spring:message code="label.codeProduit"/> :</td>
					<td class="col1"><form:input path="codeproduit" /> </td>
					<td><form:errors path="codeproduit" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="col1"><spring:message code="label.etatproduit"/> :</td>
					<td class="col1"><form:checkbox path="etatProduit" value="1"/> </td>
					<td><form:errors path="etatProduit" cssClass="error" /></td>
				</tr>
				<tr>
					<td class="col1"><spring:message code="label.prix"/> :</td>
					<td class="col1"><form:input path="prix" /> </td>
					<td><form:errors path="prix" cssClass="error" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.valider"/>" id="valider" /> 
						<input type="button"  name="retour"  value="<spring:message code="label.retour"/>" /></div>
					</td>
				</tr>
				</table>
			</form:form>
		</div>
</div>