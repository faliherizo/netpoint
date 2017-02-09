<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />


<div id="page">
<div class="scrollauto">
<table style="width:100%;">
	<tr>
		<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
			<h2><spring:message code="label.detail_command"/></h2>
		</td>
	</tr>
	<tr>
	<td>
	<table style="border: 1px solid gray;width:100%;">
						<tr>
							<td>
								<spring:message code="label.numerocmd"/>
							</td>
							<td>
								${command.numerocommande}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.numeroCommandeRev"/>
							</td>
							<td>
								${command.numeroCommande}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.produit"/>
							</td>
							<td>
								${command.produit.nomProduit}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.produit"/>
							</td>
							<td>
								${command.produit.nomProduit}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.raison_sociale"/>
							</td>
							<td>
								${command.produit.nomProduit}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.raison_sociale"/>
							</td>
							<td>
								${command.client.societe.nom}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.nombreposte"/>
							</td>
							<td>
								${command.nombreposte}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.duree"/>
							</td>
							<td>
								${command.dureeCommande.duree}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.datedebut"/>
							</td>
							<td>
							<fmt:formatDate value="${command.dateDebut}" pattern="dd/MM/yyyy"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.datefin"/>
							</td>
							<td>
							<fmt:formatDate value="${command.dateFin}" pattern="dd/MM/yyyy"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.montant"/>
							</td>
							<td>
							${command.montant}
							</td>
						</tr>
						<%-- <tr>
							<td>
								<spring:message code="label.devise_banque"/>
							</td>
							<td>
								${command.client.compteBancaire.devise.libelle}
							</td>
						</tr>
						--%>
						<tr>
							<td>
								<spring:message code="label.parent"/>
							</td>
							<td>
								-
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.revendeur"/>
							</td>
							<td>
								${command.revendeur.user.nom}
							</td>
						</tr>
	</table>
	</td>
		<td></td>
	</tr>
	<tr>
	<td><input type=button value="Retour" onClick="history.back()"></td>
	</tr>
</table>
</div>
</div>