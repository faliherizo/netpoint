<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<c:set scope="request" var="pageName" value="accueil"/>
<c:url var="urlRecherche" value="/renouvellementEchus"/>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div id="page">
<h2><spring:message code="label.renouvechus"/></h2>
  <div class="scrollauto">
     <form:form  commandName="formRecherher" name="searchForm" method="GET" action="${urlRecherche}">
	 <table style="border: 1px solid gray;width:100%;" >
	 <tr>
	    <td class="col1" style="width:100px;"><spring:message code="label.product"/> : </td>
		<td class="col1" >
		  <form:select path="produit">
					<form:option value="" label="Choisir" />
					<form:options items="${listproduit}" itemLabel="nomProduit" itemValue="id" />
			</form:select>
		</td>
		<td class="col1"><spring:message code="label.revendeur"/> :</td>
		<td class="col1">
		   <form:select path="revendeur">
					<form:option value="" label="Choisir" />
					<form:options items="${listRevendeur}" itemLabel="codeRevendeur" itemValue="id" />
			</form:select>
		</td>
		<td class="col1"><input type="submit" name="rechercher" value="<spring:message code="label.rechercher"/>" /></td>
		
		</td>
	 </tr>
	 
	  
	   
	 </table>
	 </form:form>
	 <br></br>
	 <div class="resultat" >
	 <c:if test="${!empty listcommand}">
		 <div style="margin-top:20px; margin-bottom:20px; weight:200px;; width: 100%;text-align:center; font-size:13px;font-weight:bold;border:2px solid #e3e3e3;"> 
	 
		<display:table name="${listcommand}" requestURI="renouvellementEchus"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="revendeur.societe.nom" title="Soci&eacute;t&eacute;" sortable="true" />
				    <display:column property="montant"  title="Montant" sortable="true"/>
				    <display:column property="revendeur.compteBancaire.devise.libelle"  title="Device" sortable="true"/>
				    <display:column property="quantite"  title="Quantit&eacute;" sortable="true"/>
				    <display:column property="produit.nomProduit"  title="Produit" sortable="true"/>
				    <display:column property="revendeur.codeRevendeur"  title="Revendeur" sortable="true"/>
				    <display:column property="dateToStringEcheance"  title="Date d'&eacute;cheance" sortable="true"/>
				    <display:column property="nombreDuJour"  title="Jours Restantes" sortable="true"/>
		</display:table>
	 </div>
	 </c:if>
	 </div>
  </div>
</div>
