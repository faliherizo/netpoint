<%@ taglib uri="/birt.tld" prefix="birt" %>    
<div id="reports_birt">
  <birt:report   id="birtViewer5" reportDesign="${nomFichierBirt}"
                       height="500" width="800" format="html" showParameterPage="false" scrolling="no">
             <birt:param name="produit" value="${produit}" />
             <birt:param name="date_debut" value="${date_debut}" />
             <birt:param name="date_fin" value="${date_fin}" />
              <birt:param name="groupe" value="${groupe}" />
    </birt:report>
 </div>