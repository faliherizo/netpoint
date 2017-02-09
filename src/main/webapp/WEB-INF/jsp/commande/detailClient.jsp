<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />


<div id="page">
<h2><spring:message code="label.detail_client"/></h2>
<div class="scrollauto">
		<table style="width:100%;">
			<tr>
				<td style="width:50%;">
					<table style="border: 1px solid gray;width:100%;">
						<tr>
							<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
								<spring:message code="label.coordonnee"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.societe"/>
							</td>
							<td>
								${client.societe.nom} 
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.siret"/>
							</td>
							<td>
								${client.societe.siret}							
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.adresse1"/>
							</td>
							<td>
								${client.societe.adresse1}
							</td>

						</tr>
						<tr>
							<td>
								<spring:message code="label.adresse2" />
							</td>
							<td>
								${client.societe.adresse2}
							</td>

						</tr>
						<tr>
							<td>
								<spring:message code="label.codepostal"/>
							</td>
							<td>
								${client.societe.codePostal}
							</td>
						</tr>
						<tr>
							<td>
								<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.ville"/>
							</td>
							<td>
								${client.societe.ville}
							</td>

						</tr>
						<tr>
							<td>
								<spring:message code="label.pays"/>
							</td>
							<td>
									${client.societe.pays.libelle}
							</td>
						</tr>	
						<tr>
							<td class="col1"><spring:message code="label.langue"/></td>
							<td class="col1">
								${client.user.langue.libelle}
							</td>
						</tr>	
					</table>			
				</td>
				<td style="width:50%;">
							
				<table style="border: 1px solid gray;width:100%;">
						<tr>
							<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
								<spring:message code="label.infosuppl"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.email"/>
							</td>
							<td>
								${client.user.login}
							</td>
						</tr>
					
					<tr>
							<td>
								<fmt:message key="monDSI.required.data.symbol"/><spring:message code="label.etat"/>
							</td>
							<td>
								<div id="clienterr" class="erreur" style="width: 160px;">
								   ${client.user.etatCompte.libelle}
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								<spring:message code="label.numero_revendeur"/>
							</td>
							<td>
								${clientRevendeur.numeroClientRevendeur}
							</td>
						</tr>
						<tr>
							<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
								<spring:message code="label.beneficiaire"/>
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.revendeur"/>
							</td>
							<td>
								${clientRevendeur.numeroClientRevendeur}
							</td>
						</tr>
						<tr>
							<td>
								<spring:message code="label.commercial"/>
							</td>
							<td>
								-
							</td>
						</tr>
					</table>
						
				</td>
			</tr>
			</table>		
			<table style="width:100%;">
						<tr>
							<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
								<spring:message code="label.contact"/>
							</td>
						</tr>
						<tr>
							<td>
							<table style="border: 1px solid gray;width:100%;">
							<tr>
								<td>
									<spring:message code="label.civilite"/>
								</td>
								<td>
										${client.user.civilite.libelle}
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.nom"/>
								</td>
								<td>
									${client.user.nom}
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.prenom"/>
								</td>
								<td>
									${client.user.prenom}
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.fonction"/>
								</td>
								<td>
									${client.user.fonction}
								</td>
								
							</tr>
							<tr>
								<td>
									<spring:message code="label.telephone"/>
								</td>
								<td>
									${client.user.telephone}
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.portable"/>
								</td>
								<td>
									${client.user.portable}
								</td>
							</tr>
							<tr>
								<td>
									<spring:message code="label.fax"/>
								</td>
								<td>
									${client.user.fax}
								</td>
								
							</tr>
					</table>
					</td>
					<td></td>
					</tr>
					</table>	
	<div id="resultat" style="padding-top:20px;" >
	<table style="width:100%;">
						<tr>
							<td colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center ">
								<spring:message code="label.historique_cmd"/>
							</td>
						</tr>
	</table>
<c:if test="${!empty client.listCommande}">
		<display:table name="${client.listCommande}" requestURI="detailClient"
				pagesize="2" defaultsort="2" defaultorder="descending"  export="false" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="numeroRevendeur" titleKey="label.numeroRevendeur"sortable="true" />
				    <display:column property="dateCommande"  titleKey="label.date_commande" sortable="true"/>
				    <display:column property="dateDebut"  titleKey="label.date_debut" sortable="true"/>
				    <display:column property="dateFin"  titleKey="label.date_fin" sortable="true"/>
				    <display:column property="produit.nomProduit"  titleKey="label.produit" sortable="true"/>
				    <display:column property="quantite"  titleKey="label.quantite" sortable="true"/>
				    <display:column property="quantite"  titleKey="label.montant" sortable="true"/>
				    <%-- <display:column property="client.compteBancaire.devise.libelle"  titleKey="label.devise_banque" sortable="true"/>--%>
				    <display:column property="etatCommande.libelle"  titleKey="label.etat" sortable="true"/>
		</display:table>
</c:if>
<table style="width:40%;float:left;">
		<tr>
			<td><div style="margin-left: 0px;"> 
				<a href="<c:url value='/listClient'/>"><input type="button"  name="retour"  value="Retour" /></a></div>
			</td></td>
			<td>
			<div style="margin-left: 0px;"><a href="<c:url value='/UpdateClient?id=${client.id}'/>"><input type="button" name="valider" value="Modifier" /> </a>
			</div>
			</td>
		</tr>

	</table>
</div>
	
</div>
</div>