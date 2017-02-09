<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<%@ page isELIgnored ="false" %>
<div id="page">
	<h2>Liste des produits</h2>
	<div id="separation" style="height: 30px"></div>
    <div class="resultat" >
    <c:if test="${!empty listProduct}">
		<display:table name="${listProduct}" requestURI="listProduct"
				pagesize="10" defaultsort="2" defaultorder="descending"  export="true" sort="list" id="currentObject" keepStatus="true" >			
				    <display:column property="nomProduit" title="Nom du produit" sortable="true" />
				    <display:column property="etatProduit"  title="Etat" sortable="true"/>
				    <display:column property="fournisseur.nom"  title="Fournisseur" sortable="true"/>
				    <display:column property="codeproduit"  title="Code produit" sortable="true"/>
				    <display:column property="prix"  title="prix" sortable="true"/>
				    
		</display:table>
 </c:if>
		
	</div>	
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#liste').dataTable( {
        	"aaSorting": [[ 3, "desc" ]],
            "oLanguage": {
                "sUrl": "<%=request.getContextPath()%>/js/fr_FR.txt"
            }
        });
    });
</script>