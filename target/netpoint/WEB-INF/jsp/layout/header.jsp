<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js"></script>
<link href="${pageContext.request.contextPath}/css/Ressources/Netpoint/style.css" rel="stylesheet" type="text/css" />
<div id="divHeader"> 
	<div id="header">
	<div class="header_login" style="background: url(${pageContext.request.contextPath}/img/main.gif) repeat-y;
		border-bottom: 1px solid #C5C5C5;height:80px;margin-left:10px;">
		<img style="border:solid 1px gray;" src="${pageContext.request.contextPath}/img/logo_netpoint.jpg" width="180" height="60" alt="LOGO"/>
			<span style="float:right;margin-right:10px;">
										<spring:message code="label.text_header_left"/>
			</span>
	</div>
</div>
<div id="menu">
	<ul id="nav">
		<li>
			<a class="fNiv" href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.acceuill"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><!-- Do not forget the "fNiv" class for the first level links !! -->
			<ul>
				<li class="arrow"></li>
				<li><a href="${pageContext.request.contextPath}/commandesMoisEnCours" ><spring:message code="label.orderofmonth"/></a></li>
				<li><a href="${pageContext.request.contextPath}/renouvellementDuMois"><spring:message code="label.renouvmonth"/></a></li>
				<li><a href="${pageContext.request.contextPath}/renouvellementEchus"><spring:message code="label.renouvechus"/></a></li>
			</ul>
		</li>
		<c:if test="${access_admin}">
		<li>
			<a class="fNiv" href="#"><spring:message code="label.Administration"/></a>
			<ul>
				
				<li class="arrow"></li>
				<li><a href="#" ><spring:message code="label.users"/></a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/newUser"><spring:message code="label.create"/></a></li>
						
						<li><a href="${pageContext.request.contextPath}/listUser"><spring:message code="label.rechercher"/></a></li>
						
					</ul>
					
				</li>
				<li><a href="#"><spring:message code="label.Revendeurs"/></a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/newRevendeur"><spring:message code="label.create"/></a></li>
						<li><a href="${pageContext.request.contextPath}/listRevendeurs"><spring:message code="label.rechercher"/></a></li>
						<li><a href="${pageContext.request.contextPath}/listEmailAutomatique"><spring:message code="label.email"/></a></li>
					</ul>
				</li>
				<c:if test="${access_frns}">
				<li><a href="#"><spring:message code="label.produit_list"/></a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/newProduct"><spring:message code="label.create"/></a></li>
						<li><a href="${pageContext.request.contextPath}/listProduct"><spring:message code="label.rechercher"/></a></li>
					</ul>
				</li>
				</c:if>
			</ul>
		</li>
		</c:if>
		<li>
			<a class="fNiv" href="#"><spring:message code="label.orders"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="#" ><spring:message code="label.customers"/></a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/newClient"><spring:message code="label.newcustomer"/></a></li>
						<li><a href="${pageContext.request.contextPath}/listClients"><spring:message code="label.listcustomer"/></a></li>
					</ul>
				</li>
				<li><a href="${pageContext.request.contextPath}/listCommandes"><spring:message code="label.orderlist"/></a></li>
				<li><a href="${pageContext.request.contextPath}/listClientsEssai"><spring:message code="label.testcustomer"/></a></li>
			</ul>
		</li>
		<li>
			<a class="fNiv" href="#">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.reports"/>&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<ul>
				<li class="arrow"></li>
				<li><a href="${pageContext.request.contextPath}/evolutionsVentes" ><spring:message code="label.evolutionvente"/></a></li>
				<li><a href="${pageContext.request.contextPath}/triVentes"><spring:message code="label.trivente"/></a></li>
				<li><a href="${pageContext.request.contextPath}/nombreEssais"><spring:message code="label.testnumber"/></a></li>
			</ul>
		</li>
		<li>
			<a class="fNiv" href="${pageContext.request.contextPath}/myProfil"><spring:message code="label.myprofil"/></a>
		</li>
		<li>
			<a class="fNiv" href="${pageContext.request.contextPath}/aide"><spring:message code="label.help"/></a>
		</li>
		<li class="menu_right">
			<a class="fNiv" href="${pageContext.request.contextPath}/logout" style="float:height;"><spring:message code="label.logout"/></a>
		</li>
	</ul>
</div>
</div>