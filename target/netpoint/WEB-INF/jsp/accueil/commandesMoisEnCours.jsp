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
							downloadReport('doc','vente');
							$(this).dialog("close"); 
						} ,
						"export pdf": function() { 
							downloadReport('pdf','vente');
							$(this).dialog("close"); 
						} ,
						"export xls": function() { 
							downloadReport('xls','vente');
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
	var firstYear = 2000; var lastYear = 2100; for(var i =firstYear; i<=lastYear; i++) { $('#year').append('<option value="'+i+'">'+i+'</option>') }
	
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
			$('#product').change(function() {
			
				$.getJSON("<c:url value="get_rev_list" />", {
					produit : $(this).val(), ajax : 'true'}, function(data) {
					var html = '<option value="">Select</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].user.nom + '</option>';
					}
					html += '</option>';
	
					$('#revendeurss').html(html);
				});
			});
			$('#month').change(function(){
				if($(this).val()!=''){
					$('#error_month').html('');
				}
				
			});
			$('#year').change(function(){
				if($(this).val()!=''){
					$('#error_year').html('');
				}
				
			});
});


	</script>
<script>
currentReportName="";
function chargerRapport(rapport) {
          
    var revendeur = $('#revendeurss').val();
    var produit = $('#product').val();
    var month = $('#month').val();
    var year = $('#year').val();
    if(month =='' || year =='')
    {
    	if(month ==''){
    		$('#error_month').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
    	}
    		
    	else{
    		$('#error_year').html("<div class='error'>"+"<spring:message code='label.error_rev_required'/>"+"</div>");
    	}
    		
    }
    else{
    	 $('#rapport').html("<img src='images/loading.gif'></img>");  
    	 $('#rapport').load('ajax/rapport_birt?rapport='+rapport+'&revendeur='+revendeur+'&produit='+produit+'&month='+month+'&year='+year, function(response, status, xhr) {
       	  if (status == "error") {
       		    var msg = "Sorry but there was an error: ";
       		    $("#rapport").html(msg + xhr.status + " " + xhr.statusText);
       		  }
       	});   
    }
      
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
        var index = valeurs.size()-i-1;
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
	var revendeur = $('#revendeurss').val();
    var produit = $('#product').val();
    var month = $('#month').val();
    var year = $('#year').val();
	//here relative url is given if relative url is not working try giving full url
	var reporturl ='/netpoint/loadReport?ReportName='+currentReportName+'&ReportFormat='+format+'&revendeur='+revendeur+'&produit='+produit+'&month='+month+'&year='+year;
	window.location.href = reporturl;
	$(this).dialog("close"); 
}
</script>


   
<c:set scope="request" var="pageName" value="accueil"/>
<div id="page">
<h2><spring:message code="label.commandemoisencour"/></h2>
	<div id="dialog" title="">
			<%--TODO dialog select export format --%>
			<div id="downloadOptions" align="right">
				<spring:message code="label.downloadOptions"/>
					
			</div>
		</div>
  <div class="scrollauto">
	 <table style="border: 1px solid gray;width:100%;" >
	  <tr>
	    <td class="col1" style="width:100px;"><spring:message code="label.month"/> :</td>
		<td class="col1" >
			<select id="month" name="month">
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
		</td><td id="error_month"></td>
		<td class="col1" style="width:100px;"><spring:message code="label.year"/> :</td>
		<td class="col1" >
		
		 <select id="year" name="year">        
		            <option value="">Select</option>                    		        
    	 </select> 
		</td>
		<td id="error_year"></td>
	 </tr>
	 
	 
	 <tr>
	    <td class="col1" style="width:100px;"><spring:message code="label.fournisseur"/> :</td>
		<td class="col1" >
		 <c:if test="${!empty get_frns_list}">
			<select id="fournisseur" name="fournisseur">
				<option value="">Select</option> 
		        <c:forEach var="frns" items="${get_frns_list}"> 	        
		            <option value="${frns.id}">${frns.nom}</option>                    
		        </c:forEach>
    		 </select>
		</c:if>
		</td>
		<td id="error_frns"></td>
		<td class="col1" style="width:100px;"><spring:message code="label.produit"/> :</td>
		<td class="col1" >
		
		 <select id="product" name="product">        
		            <option value="">Select</option>                    		        
    	 </select> 
		</td>
		<td id="error_prod"></td>
		<td class="col1"><spring:message code="label.revendeur"/></td>
		<td class="col1">
		 	
		 	<select id="revendeurss" name="revendeurss">
		 	    
				<option value="">Select</option>      
		       
    		</select>    			   
			
		</td></td><td id="error_rev"></td>
		
	 </tr>
	 
	 
	  
	   <tr>
	   	<td class="col1"></td><td class="col1"></td><td class="col1"></td><td class="col1"></td>
			<td class="col1"></td>
			<td class="col1"></td>
			<td class="col1"></td>
	   <td class="col1" style="float:right; margin-right: 22px;"><input type="button" name="Charger" value="Rechercher" onclick="chargerRapport('vente');"/></td>
		 <td class="col1" style="float:right; margin-right: 22px;"><input type="button" name="Export" id="Export" value="Export" /></td>
	 </tr>
	 </table>
	 <br />
	       <div id="rapport">
	          

	       
	       </div>

 	 </div>
</div>