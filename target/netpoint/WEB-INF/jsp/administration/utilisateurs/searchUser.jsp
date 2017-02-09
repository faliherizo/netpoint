<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
    <div id="recherche">
	<div id="page">
		<h2>Liste des utilisateurs</h2>
	
		<div id="central" class="mains">
			<div>
			
				<h2>Liste des documents index&eacute;s</h2>
				<div class="list-container">
					<div class="list-container-header">	

					<form:form id="search_user"  modelAttribute="critereRecherche" method="post" action="${searchUser}" name="form">						
							<p>		
								<span>
									<label>Typologie :</label>	
									<form:input  path="typologie" class="search-ipt infield"  id="libelleTypologie" onkeypress="submitForm(event)"/>
								</span>
								<span>
									<label>Indexation :</label>	
									<form:input  path="identifiant" class="search-ipt infield" id="identifiant" onkeypress="submitForm(event)"/>
								</span>								
								<form:hidden path="idTypologie" id="idTypologie" />
								<form:hidden path="topologieList" id="typologieList" />
								<form:hidden path="firstLoad" />
								
								<span><input type="button" id="btnRecherche" value="Rechercher"></input></span>
							</p>
						<!--</fieldset>-->
					</form:form>	
				
						<c:choose>
							<c:when test="${empty responseError}">
							<div id="listFichierIndexe">
								<display:table requestURI="${urlLoadList}" name="listFichierIndexe" sort="external" defaultsort="1" 
									pagesize="${result.objectsPerPage}"  partialList="true" size="result.objectsPerPage" class="list" id="fichierIndexe">									
									<c:url value="/remoting/all/indexation/saisie_fichier/form.htm/${fichierIndexe.idFichierSource}" var="saisieFichierUrl"/>
									<display:column property="identifiant" title="Identifiant" headerClass="identifiant"/>	
									<display:column property="typologie.libelleTypologie" title="Typologie" headerClass="typologie"/>	
									<display:column property="fichierSource.createur.utilisateurNom" title="Import&eacute; par" headerClass="createur"/>
									<display:column property="fichierSource.dateCreationString" title="Le" headerClass="dateCreation"/>									
									<display:column property="fichierSource.titre" title="Titre" headerClass="titre"/>																										
									<display:column  title="Double-valid&eacute;" headerClass="doubleValidation" >
										<c:choose>
											<c:when test="${fichierIndexe.indexValide}">Oui</c:when>
											<c:otherwise>Non</c:otherwise>
										</c:choose>
									</display:column>
									<display:column title="Action">
										<c:choose>
											<c:when test="${not fichierIndexe.indexValide}">
												<a href="<c:out value="${saisieFichierUrl}" />" class="edit" title="Modifier">Double-valider</a>
											</c:when>											
										</c:choose>
																			
									</display:column>							
								</display:table>	
							</div>			
							</c:when>			
							<c:otherwise>
								<div class="errorblock">
									<span>${responseError}</span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	