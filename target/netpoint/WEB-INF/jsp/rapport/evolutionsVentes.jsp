<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
					width: 400,
					buttons: {
					"Cancel": function() { 
							$(this).dialog("close"); 
						} ,
						"export doc": function() { 
							downloadReport('doc','evolutionvente');
							$(this).dialog("close"); 
						} ,
						"export pdf": function() { 
							downloadReport('pdf','evolutionvente');
							$(this).dialog("close"); 
						} ,
						"export xls": function() { 
							downloadReport('xls','evolutionvente');
							$(this).dialog("close"); 
						} 
						
					}
				});
				
				// Dialog Link
				$('#Export').click(function(){
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
<script type="text/javascript" charset="utf-8">
$(document).ready(function() { 
	var firstYear = 2000; var lastYear = 2100; for(var i =firstYear; i<=lastYear; i++) { $('#evol_annee').append('<option value="'+i+'">'+i+'</option>') }
	
	
	function verifetat(product){
		if(product=='')
			return "<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>"
		else{
			return "";
		}
	}
	//$('#product').change(function(){
	//	$('#error_prod').html(verifetat($('#product').val()));
	//})
	//$('#revendeur').change(function(){
	//	$('#error_rev').html(verifetat($('#revendeur').val()));
	//})
	$('#evol_mois').change(function(){
		$('#error_evol_mois').html(verifetat($('#evol_mois').val()));
	})
	$('#evol_annee').change(function(){
		$('#error_evol_annee').html(verifetat($('#evol_annee').val()));
	})
	//$('#fournisseur').change(function(){
		//$('#error_frns').html(verifetat($('#fournisseur').val()));
	//})
	$('#revendeur').change(function() {
		
				$.getJSON("<c:url value="get_produit_list_rev" />", {
					revendeur : $(this).val(), ajax : 'true'}, function(data) {
					var html = '<option value="">Select</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].nomProduit + '</option>';
					}
					html += '</option>';

					$('#product').html(html);
				});
			});
});
	</script>
<script>
currentReportName="";
function chargerRapport() {
	var a=$('#revendeur').val();
	var b= $('#product').val();
	var c= $('#evol_mois').val();
	var d= $('#evol_annee').val();
	var e= $('#fournisseur').val();
	
    if(c=='' || d==''){
		if(c==''){
			$('#error_evol_mois').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
		}
		if(d=='')
		{
			$('#error_evol_annee').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
		}
    	//if(b==)
    	//{
			//$('#error_prod').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
    	//}
    	//if(a==)
		//{
    		//$('#error_rev').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
    	//}
		//if(e==)
		//{
			//$('#error_frns').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
		//}
    }
	else{
    $('#rapport').html("<img src='images/loading.gif'></img>");         
    var revendeur = $('#revendeur').val();
    var produit = $('#product').val();
    var mois= $('#evol_mois').val();
    var annee= $('#evol_annee').val();
    $('#rapport').load('ajax/rapport_evolution_vente?rapport=evolutionvente&revendeur='+revendeur+'&produit='+produit+'&month='+mois+'&year='+annee, function(response, status, xhr) {
    	  if (status == "error") {
    		    var msg = "Sorry but there was an error: ";
    		    $("#rapport").html(msg + xhr.status + " " + xhr.statusText);
    		  }
    		});  
	} 
    //$('#rapport').load('rapportBirt?rapport=vente&revendeur='+revendeur+'&produit='+produit);    
}
function genererLienRapport() {     
    var map = $('iframe[name=birtViewer5]').contents().find("map");
    
    // on cache le tableau de correspondance
    var table = map.parent().next();
    
    // si pas de table de correspondance, on ne fait rien
    if(!table || table == null) 
        return;
    
    
    table.css("display","none");
    
    
    var areas = map.find("area");
    var valeurs = table.find("td"); 
    
    
    for(var i=0;i<areas.length;i++) {
        var href = $(areas[i]).attr("href");
        var index = valeurs.size()-i;
        var valeur =$(valeurs[index]).text();
        var res = href.replace("AREMPLACER",valeur);        
        $(areas[i]).attr("href",res);
    }
        
}

setInterval(genererLienRapport, 500);
function downloadReport(format, currentReportName){
	
	if(currentReportName==""){
		alert("Please Select the report.");
		return;
	}
	 var revendeur = $('#revendeur').val();
	    var produit = $('#product').val();
	    var mois= $('#evol_mois').val();
	    var annee= $('#evol_annee').val();
	//here relative url is given if relative url is not working try giving full url
	var reporturl ='/netpoint/loadReport?ReportName='+currentReportName+'&ReportFormat='+format+'&revendeur='+revendeur+'&produit='+produit+'&month='+mois+'&year='+annee;
	window.location.href = reporturl;
	$(this).dialog("close"); 
}
</script>

<c:set scope="request" var="pageName" value="rapport"/>
<div id="page">
 <h2><spring:message code="label.evolventemois"/></h2>
 <div id="dialog" title="">
			<%--TODO dialog select export format --%>
			<div id="downloadOptions" align="right">
				<spring:message code="label.downloadOptions"/>
					
			</div>
		</div>
<div class="scrollauto">
	<table style="border: 1px solid gray;width:100%;" >
		<tr>
			<td class="col1"><spring:message code="label.periode"/></td>
			<td class="col1">
				<select id="evol_mois"
						name="evol_mois">
						<option value="">Select</option>
						<option value="1">Janvier</option>
						<option value="2">Fevrier</option>
						<option value="3">Mars</option>
						<option value="4">Avril</option>
						<option value="5">Mai</option>
						<option value="6">Juin</option>
						<option value="7">Juillet</option>
						<option value="8">Aout</option>
						<option value="9">Septembre</option>
						<option value="10">Octobre</option>
						<option value="11">Novembre</option>
						<option value="12">Decembre</option>
				</select>
			</td>
			<td id="error_evol_mois"></td>
			<td>
				<select id="evol_annee"
						name="evol_annee">
						<option value="">Select</option>
					</select>
			</td>
			<td id="error_evol_annee"></td>
			<td class="col1"></td>
			<td class="col1"></td>
			<td class="col1"></td>
		</tr>
	 	<tr>
			<td class="col1"><spring:message code="label.revendeur"/></td>
			<td class="col1">
				<select id="revendeur" name="revendeur">
					<option value="">Select</option>      
					<c:forEach var="revend" items="${revendeurList}">        
						<option value="${revend.id}">${revend.user.nom}</option>                    
					</c:forEach>
				</select>    			   
			</td><td id="error_rev"></td>
			<td class="col1" style="width:100px;"><spring:message code="label.produit"/></td>
			<td class="col1" >
			 <select id="product" name="product">        
						<option value="">Select</option>                    		        
			 </select> 
			</td><td id="error_prod"></td>
			<td class="col1" style="width:100px;"><spring:message code="label.fournisseur"/></td>
			<td class="col1" >
				 <c:if test="${not empty get_frns_list}">
					<select id="fournisseur" name="fournisseur">
						<option value="">Select</option>   
						<c:forEach var="frns" items="${get_frns_list}"> 
							      
							<option value="${frns.id}">${frns.nom}</option>                    
						</c:forEach>
					 </select>
				</c:if>
				</td><td id="error_frns"></td>
	 	</tr>		 
			
		<tr>
			
			<td class="col1"></td><td class="col1"></td><td class="col1"></td><td class="col1"></td>
			<td class="col1"></td>
			<td class="col1"></td>
			<td class="col1"></td>
			<td class="col1" style="float: right;margin-right:4px;"><input type="button" name="Charger" value="Rechercher" onclick="chargerRapport();"/></td>
			<td class="col1" style="float:right; margin-right: 22px;"><input type="button" name="Export" id="Export" value="Export" /></td>
	 	</tr>
	 </table>
	 <br />
	       <div id="rapport">
	          

	       
	       </div>

	
 </div>
</div>