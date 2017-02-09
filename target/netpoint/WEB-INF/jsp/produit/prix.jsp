<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<c:set scope="request" var="pageName" value="administration" />

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
					width: 300,
					buttons: {
						"Oui": function() { 
							$('#form_user').submit();
							$(this).dialog("close"); 
						}, 
						"Non": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#valider').click(function(){
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
<div id="page">
	<h2><spring:message code="label.ajout_prix_produit"/></h2>
	<div class="scrollauto">
	<!-- ui-dialog -->
		<div id="dialog" title="">
			<p><spring:message code="label.save_prix"/></p>
		</div>
	
			<form:form commandName="prix" id="form_produit" method="POST">
				<table class="table1 table-groupe" cellpadding="3" cellspacing="0" style="width: 100%;">
					<tr>
						<td class="col1"><spring:message code="label.codeProduitSage"/> :</td>
						<td class="col1"><form:checkbox path="codeProduitSage" value="1"/> </td>
						<td><form:errors path="codeProduitSage" cssClass="error" /></td>
					</tr>
					<tr>
						<td class="col1"><spring:message code="label.codeProduit"/> :</td>
						<td class="col1"><form:checkbox path="codeProduit" value="1"/> </td>
						<td><form:errors path="codeProduit" cssClass="error" /></td>
					</tr>
					
					<tr>
						<td class="col1"><spring:message code="label.duree"/> :</td>
						<td class="col1"><form:input path="duree" /></td>
						<td><form:errors path="duree" cssClass="error" /></td>
					</tr>
					<tr>
						<td class="col1"><spring:message code="label.tarif"/> :</td>
						<td class="col1"><form:input path="tarif" /></td>
						<td><form:errors path="tarif" cssClass="error" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
						<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.valider"/>" id="valider" /> 
							<input type="button"  name="retour"  value="<spring:message code="label.retour"/>" /></div>
						</td>
					</tr>
					<tr>
						<td>
							${produit.nomProduit}
						</td>
						<td>&nbsp;</td>
						<td>
					</tr>
				</table>
			</form:form>
	</div>
</div>
			