<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set scope="request" var="pageName" value="administration" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytag.css" />
<title>Personnalisation des E-mails</title>
<%@ page isELIgnored ="false" %>
<div id="page">
<h2></h2>
	<div class="scrollauto">
		<form:form commandName="mailhdr" method="POST" action="cron-mail">
			<table class="table1 table-groupe" cellpadding="3" cellspacing="1">
			 <thead>
	            <tr>
		            <th>
		            </th>
	                <th scope="col" style="vertical-align:center;">
	                    <input type="checkbox" id="checkbox" name="checkbox" onclick="toggleChecked(this.checked)" />
	                </th>
	            </tr>
        	</thead>        
            <tbody> 
				<c:if test="${!empty mailhdr.maildtl}">
					<c:forEach items="${mailhdr.maildtl}" var="dtl" varStatus="i">
						<tr>
							
							<td>
								${dtl.subject}
							</td>
							<td>
								<form:checkbox path="maildtl[${i.index}].active" class="list"/>
								<form:input path="maildtl[${i.index}].contenu" type="hidden" />
								  <form:input path="maildtl[${i.index}].logo" type="hidden" />
								  <form:input path="maildtl[${i.index}].type" type="hidden" />
								  <form:input path="maildtl[${i.index}].societe" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].codemail" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].id" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].description" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].phone" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].mailFrom" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].societe" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].website" type="hidden" />
							  	  <form:input path="maildtl[${i.index}].subject" type="hidden" />
							</td>
						</tr>
						
					</c:forEach>
				</c:if>

				<tr>
					<td>
						  <form:input path="phonesupport" type="hidden" />
						  <form:input path="mailsupport" type="hidden" />
						  <form:input path="code" type="hidden" />
						  <form:input path="id" type="hidden" />
						  <form:input path="mailreception" type="hidden"  />
					 </td>
					 <td></td>
				</tr>
				<tr>
					<td>
						<div style="margin-left: 0px;"><input type="submit" id="valider" value="Valider" /> 
									<input type="reset"  name="retour"  value="Annuler" />
						</div>
					</td>
					<td></td>
				</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</div>
<script type="text/javascript">
    function toggleChecked(status) {
        $(".list").each( function() {
            $(this).attr("checked",status);
        })
    }
</script>