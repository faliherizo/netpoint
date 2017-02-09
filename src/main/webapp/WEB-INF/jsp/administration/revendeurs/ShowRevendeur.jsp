<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<div id="page">
<div id="scrollauto">
<h2>Administration >> revendeurs > Liste des revendeurs</h2>
	<form action="searchUser.do" method="post">
				<table class="table1 table-groupe" cellpadding="3" cellspacing="0">
					<tr>					
						<td class="col1">Société</td>
						<td class="col1">
						  <select id="user_droit"
							name="user_droit">
							<option value="">&nbsp;&nbsp;Choisir&nbsp;&nbsp;</option>
							<option value="">société1</option>
							<option value="">société2</option>
						  </select>
						</td>
						
						<td class="col1">Etat</td>
						<td class="col1">
							<select id="user_droit"
								name="user_droit">
								<option value="">&nbsp;&nbsp;Choisir&nbsp;&nbsp;</option>
								<option value="">etat1</option>
								<option value="">etat2</option>
							</select>
						</td>
						
						<td class="col1">
							<input type="button" name="rechercher" value="Rechercher" />
						</td>
					</tr>
				</table>
	</form>
	<table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">
		<tr bgcolor="lightblue">
			<th>Id</th>
			<th>Nom</th>			
			<th>Prenom</th>	
			<th>Telephone</th>
			<th>Mail</th>
		</tr>
		<c:if test="${empty SEARCH_USER_RESULTS_KEY}">
		<tr>
			<td colspan="4">No Results found</td>
		</tr>
		</c:if>
		<c:if test="${! empty SEARCH_USER_RESULTS_KEY}">
			<c:forEach var="user" items="${SEARCH_USER_RESULTS_KEY}">		
		    <tr>
				<td><c:out value="${user.nom}"></c:out></td>
				<td><c:out value="${user.prenom}"></c:out></td>
				<td><c:out value="${user.telephone}"></c:out> </td>
				<td><c:out value="${user.login}"></c:out></td>
				<td>
					&nbsp;<a href="updateContact.do?id=${user.id}">Edit</a>
					&nbsp;&nbsp;<a href="javascript:deleteUser('deleteUser.do?id=${user.id}');">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</c:if>				
	</table>	
</div>
</div>
<script type="text/javascript">
function go(url)
{
	window.location = url;
}

function newUser()
{
	window.location = "newUser.do";
}

function deleteUser(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}


</script>	