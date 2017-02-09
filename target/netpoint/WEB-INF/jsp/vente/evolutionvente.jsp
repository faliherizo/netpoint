<%@ taglib uri="/birt.tld" prefix="birt" %>    
<div id="reports_birt">  
<birt:report   id="birtViewer5" reportDesign="${nomFichierBirt}"
                       height="500" width="800" format="html" showParameterPage="false" scrolling="no">
             <birt:param name="revendeur" value="${revendeur}" />
             <birt:param name="produit" value="${produit}" />
             <birt:param name="year" value="${year}" />
             <birt:param name="month" value="${month}" />
              <birt:param name="groupe" value="${groupe}" />
</birt:report>
 </div>