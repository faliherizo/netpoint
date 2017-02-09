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
							downloadReport('doc','nombreEssaie');
							$(this).dialog("close"); 
						} ,
						"export pdf": function() { 
							downloadReport('pdf','nombreEssaie');
							$(this).dialog("close"); 
						} ,
						"export xls": function() { 
							downloadReport('xls','nombreEssaie');
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
	//$('#fournisseur').change(function(){
		//$('#error_frns').html(verifetat($('#fournisseur').val()));
	//})
	$('#date_fin').change(function(){
		$('#error_date_fin').html(verifetat($('#date_fin').val()));
	})
	$('#date_debut').change(function(){
		$('#error_date_debut').html(verifetat($('#date_debut').val()));
	})

	$('#fournisseur').change(function() {
		
				$.getJSON("<c:url value="get_produit_list" />", {
					fournisseur : $(this).val(), ajax : 'true'}, function(data) {
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
function chargerRapport() {        
    var fournisseur = $('#fournisseur').val();
    var produit = $('#product').val();
    var date_debut = $('#date_debut').val();
    var date_fin = $('#date_fin').val();
	if(date_debut=='' || date_fin==''){
			if(date_fin==''){
				$('#error_date_fin').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			}
			if(date_debut=='')
			{
				$('#error_date_debut').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			}
			//if(produit==)
			//{
				//$('#error_prod').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			//}
			//if(fournisseur==)
			//{
				//$('#error_frns').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
			//}
		}
	else{
			$('#rapport').html("<img src='images/loading.gif'></img>"); 
    $('#rapport').load('ajax/rapport_birt_essaie?rapport=essaie&produit='+produit+'&date_debut='+date_debut+'&date_fin='+date_fin, function(response, status, xhr) {
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
	var fournisseur = $('#fournisseur').val();
    var produit = $('#product').val();
    var date_debut = $('#date_debut').val();
    var date_fin = $('#date_fin').val();
	//here relative url is given if relative url is not working try giving full url
	var reporturl ="/netpoint/loadReport?ReportName="+currentReportName+"&ReportFormat="+format+"&produit="+produit+"&date_debut="+date_debut+"&date_fin="+date_fin;
	window.location.href = reporturl;
	$(this).dialog("close"); 
}
</script>	
<c:set scope="request" var="pageName" value="rapport"/>
<div id="page">
<h2><spring:message code="label.nombre_essaie"/></h2>
<div id="dialog" title="">
			<%--TODO dialog select export format --%>
			<div id="downloadOptions" align="right">
				<spring:message code="label.downloadOptions"/>
					
			</div>
		</div>
<div class="scrollauto">
	<table style="border: 1px solid gray;width:100%;" >
	              <tr>
						    <td class="col1"><spring:message code="label.fournisseur_prod"/></td>
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
				</tr>
			    
			    <tr>
					<td class="col1"><spring:message code="label.date_fin"/></td>
					<td class="col1">
							<input type="date" name="date_fin" type="text"  id="date_fin" placeholder="jj/dd/mmmm" style="text-align: right;" />
					</td><td id="error_date_fin"></td>
				</tr>  
			    <tr>
				<td class="col1"></td><td class="col1"></td><td class="col1"></td><td class="col1"></td>
				    <td class="col1" style="float:right; margin-right: 22px;">
				        <div  style="margin-left:0;">
						  <input type="button" name="Charger" value="<spring:message code="label.rechercher"/>" onclick="chargerRapport();"/>
					     </div>
				    </td>
				    <td class="col1" style="float:right; margin-right: 22px;"><input type="button" name="Export" id="Export" value="Export" /></td>
			    </tr>	  					   
			</table>
			 <br />
	       <div id="rapport">
	          

	       
	       </div>
 </div>
  </div>
  