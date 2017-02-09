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
							downloadReport('doc','trivente');
							$(this).dialog("close"); 
						} ,
						"export pdf": function() { 
							downloadReport('pdf','trivente');
							$(this).dialog("close"); 
						} ,
						"export xls": function() { 
							downloadReport('xls','trivente');
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
		
		function verifetat(product){
			if(product=='')
				return "<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>"
			else{
				return "";
			}
		}
	//$('#product').change(function(){
		//$('#error_prod').html(verifetat($('#product').val()));
	//})
	//$('#revendeur').change(function(){
		//$('#error_rev').html(verifetat($('#revendeur').val()));
	//})
	$('#date_fin').change(function(){
		$('#error_date_fin').html(verifetat($('#date_fin').val()));
	})
	$('#date_debut').change(function(){
		$('#error_date_debut').html(verifetat($('#date_debut').val()));
	})
	
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
	function chargerRapport() 
	{
		
		var a=$('#revendeur').val();
		var b= $('#product').val();
		var c= $('#date_fin').val();
		var d= $('#date_debut').val();
		if(c=='' || d==''){
			if(c==''){
				$('#error_date_fin').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			}
			if(d=='')
			{
				$('#error_date_debut').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			}
			//if(b==)
			//{
				//$('#error_prod').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			//}
			//if(a==)
			//{
				//$('#error_rev').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			//}
		}
		else{
			$('#rapport').html("<img src='images/loading.gif'></img>");         
			var revendeur = $('#revendeur').val();
			var produit = $('#product').val();
			var dateDebut = $('#date_debut').val();
			var dateFin = $('#date_fin').val();
			$('#rapport').load('ajax/rapport_tri_vente?rapport=trivente&revendeur='+revendeur+'&produit='+produit+'&dateDebut='+dateDebut+'&dateFin='+dateFin, function(response, status, xhr) 
			{
				  if (status == "error") {
						var msg = "Sorry but there was an error: ";
						$("#rapport").html(msg + xhr.status + " " + xhr.statusText);
					  }
			}); 
		}  
	}
	function genererLienRapport() 
	{     
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
/**
 * Download report function
 * 
 * @param format
 */
function downloadReport(format, currentReportName){
	
	if(currentReportName==""){
		alert("Please Select the report.");
		return;
	}
	//here relative url is given if relative url is not working try giving full url
	//var reporturl ="/netpoint/loadReport?ReportName="+currentReportName+"&ReportFormat="+format;
	var revendeur = $('#revendeur').val();
			var produit = $('#product').val();
			var dateDebut = $('#date_debut').val();
			var dateFin = $('#date_fin').val();
	//here relative url is given if relative url is not working try giving full url
	var reporturl ="/netpoint/loadReport?ReportName="+currentReportName+"&ReportFormat="+format+"&revendeur="+revendeur+"&produit="+produit+"&dateDebut="+dateDebut+"&dateFin="+dateFin;
	
	window.location.href = reporturl;
	$(this).dialog("close"); 
}
</script>
    <c:set scope="request" var="pageName" value="rapport"/>
<div id="page">
<h2><spring:message code="label.tri_vente"/></h2>
<div id="dialog" title="">
			<%--TODO dialog select export format --%>
			<div id="downloadOptions" align="right">
				<spring:message code="label.downloadOptions"/>
					
			</div>
		</div>
<div class="scrollauto">
	<table style="border: 1px solid gray;width:100%;">	
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
		</tr>			    
		
		<tr>
			<td class="col1"><spring:message code="label.date_debut"/></td>
			<td class="col1">
				<input name="date_debut" type="date" id="date_debut" placeholder="jj/dd/mmmm" style="text-align: right;" />
			</td><td id="error_date_debut"></td>
			<td class="col1"><spring:message code="label.date_fin"/> </td>
			<td class="col1">
					<input type="date" name="date_fin" id="date_fin" placeholder="jj/dd/mmmm" style="text-align: right;" />
			</td><td id="error_date_fin"></td>
		</tr>
		<tr>
			<td class="col1"></td>
			<td class="col1"></td><td class="col1"></td><td class="col1"></td>
		    <td class="col1" style="float: right;">
				<input type="button" name="Charger" value="<spring:message code="label.rechercher"/>" onclick="chargerRapport();"/>
			</td>
			<td class="col1" style="float:right; margin-right: 22px;"><input type="button" name="Export" id="Export" value="Export" /></td>
	 </tr>
</table>	
 <br />
	       <div id="rapport">
	          

	       
	       </div>
 </div>
</div>