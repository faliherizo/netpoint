<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>    
<div id="reports_birt">
    <birt:report   id="birtViewer5" reportDesign="${nomFichierBirt}"
                       height="500" width="800" format="xls" showParameterPage="true" scrolling="no">
                       <birt:param name="month" value="${month}" />
                       <birt:param name="year" value="${year}" />
             <birt:param name="revendeur" value="${revendeur}" />
             <birt:param name="produit" value="${produit}" />
    </birt:report>
 </div>