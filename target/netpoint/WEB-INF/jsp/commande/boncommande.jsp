<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<c:set scope="request" var="pageName" value="command"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css"/>              
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.all.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css" />
<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8rc3.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8rc3.custom.css" />
<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/menutree.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/Netpoint.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.datepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/devbundle/ui/jquery.ui.dialog.js"></script>
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
							$('#form_client2').submit();
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
<div class="scrollauto">
	<h2><spring:message code="label.boncommande"/></h2>
		<div id="dialog" title="">
			<p><spring:message code="label.confirmboncommande"/></p>
			
		</div>
		
		<div id="web_flow"  style="height: 80px;margin-bottom:-5px;width:100%" >
			<div style="margin-left:40px">
				<ul id="flow">
					<li class="depart"  style="width:30%;"><h1 class="etape">1</h1></li>
					<li class="intermediaire"  style="width:30%;"><h1 class="etape-active">2</h1></li>
					<li class="fin"  style="width:30%;"><h1 class="etape">3</h1></li>
				</ul>
				<div id="flow-titles">
					<div class="actif"  style="width:30%;"><spring:message code="label.infos_client"/></div>
					<div class="inactif" style="width:30%;"><spring:message code="label.choix_solution"/></div>
					<div class="inactif"  style="width:30%;"><spring:message code="label.racapitulation"/></div>
				</div>
			</div>
		</div>
		<div class="form_bon_command">
		<form:form commandName="command" method="POST" action="newClient?_finish" id="form_client2">
		<table style="width: 100%">
					<tr colspan="3" bgcolor="#CCCCCC" style="height:30px; font-size: 13px; text-align: center;border:1px;">
						<td>
							<spring:message code="label.produit"/>
						</td>
						<td>
							<spring:message code="label.quantite"/>
						</td>
						<td>
							<spring:message code="label.prixunitaire"/>
						</td>
						<td style="width: 150px;">
							
						</td>
						<td>
							<spring:message code="label.totalht"/>
						</td>
					</tr>
					<tr>
						<td>
							${produit.nomProduit}
						</td>
						<td>
							${command.nombreposte}
						</td>
						<td>
							${produit.prix}
						</td>
						<td>
							-${command.tauxremise}
						</td>
						<td>
							${command.prixsansremise}
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td>
							
						</td>
						<td>
							
						</td>
						<td>
							<spring:message code="label.totalremise"/>
						</td>
						<td>
							${command.totalremise}
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td>
							
						</td>
						<td>
							
						</td>
						<td>
							<spring:message code="label.Prixachatrevendeur"/>
						</td>
						<td>
							${command.prixproduit}
						</td>
					</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="margin-left: 0px;"><input type="submit" name="valider" value="<spring:message code="label.next"/>" id="valider" /> 
				<input type=button value="Retour" onClick="history.back()"></div>
			</td>
		</tr>
		
		</table>
		
	</form:form>
		</div>
</div>
</div>