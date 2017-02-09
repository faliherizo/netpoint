<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<c:set scope="request" var="pageName" value="administration"/>
<div>
    <div>
      <div style=" margin-bottom:5px;font-size:14px;font-weight:bold;" >Site Vitrine</div>
	      <form id="form-user">
				<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
					<tr>					
						<td class="col1">Nom du site : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
						
						<td class="col1">Nom de la société : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
				    </tr>
				    
				    <tr>					
						<td class="col1">Email technique : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
						
				
						<td class="col1">Email commercial : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
				    </tr>
				    
				    
				    <tr>					
						<td class="col1">Langue du site : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
						
											
						<td class="col1">Choisir un style : </td>
						<td class="col1"><input id="partenaire_email" class="mandatory" name="partenaire_email"
				         type="text" /></td>
				    </tr>
				    
		
				    <tr>
						<td class="col1">
							<input type="button" name="rechercher" value="Rechercher" />
						</td>
					</tr>
					
				</table>
		   </form>
      </div>
</div>