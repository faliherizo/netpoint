function getItem(item) {
	if(item != null && item != "")
		return item;
	else
		return "&nbsp;";
}

function DwrGrid(autoLoad, tbodyId, dwrProxy, cellFct, extraParam) {
	this.autoLoad = autoLoad;
	this.tbodyId = tbodyId;
	this.dwrProxy = dwrProxy;
	this.cellFct = cellFct;
	this.extraParam = extraParam;
	
	var start = 0;
	var count =2;
	//var count = 2;
	var totalSize = 0;
	var orderBy = "";
	var parentNodeSort;
	var filter = "";
	var currentPage = 1;
	var totalPage = 1;
	var xeme=1;

	if(currentPage == 1) {
		disable("debut");
		disable("precedent");
		disable("suivant");
		disable("fin");
	}
	// Appel AJAX
	if(autoLoad)
		loadGridAjax();
	else {
		// Mise � jour des variales sur la page
		dwr.util.setValue(tbodyId + "-totalpage", totalSize);
	}
	// Fonction de recalcul
	// vers la page suivante
	this.next = function() {
		
			pagination("next");
		
	}
	// Fonction de recalcul
	// vers la page pr�c�dente
	this.previous = function() {
		
			pagination("previous");
		
	}
	// Fonction de recalcul
	// vers la premi�re page
	this.first = function() {

			pagination("first");
	}
	// Fonction de recalcul
	// vers la derni�re page
	this.last = function() {
		
			pagination("last");
		
	}
	function pagination(type) {
	//alert('type -->'+type);
		// On avance d'une page
		if(type=="next") {
			currentPage++;		
			start = start + count;
			loadGridAjax();
			enable("debut");
			enable("precedent");
			if(currentPage == totalPage) {
				disable("fin");
				disable("suivant");
			}
		}
		// Aller � la fin
		if(type=="last") {
			currentPage = totalPage;
			start = count * (totalPage - 1);
			loadGridAjax();
			enable("debut");
			enable("precedent");
			disable("fin");
			disable("suivant");
		}
		// On recule d'une page
		if(type=="previous") {
			if(currentPage == totalPage) {
				start = totalSize - (count + (totalSize % count));
			}
			else
				start = start - count;
			currentPage--;
			loadGridAjax();
			//D�cocher la case d'en-t�te lors de la navigation
            if(document.getElementById("checkBoxSelectAll")) {
                if(document.getElementById("checkBoxSelectAll").checked){
                    document.getElementById("checkBoxSelectAll").checked = false;
                }
            }
			
			enable("fin");
			enable("suivant");
			
			if(currentPage == 1) {
				disable("debut");
				disable("precedent");
			}
		}
		// Premi�re page
		if(type=="first") {
			currentPage = 1;
			start = 0;
			loadGridAjax();
			//D�cocher la case d'en-t�te lors de la navigation
			if(document.getElementById("checkBoxSelectAll")) {
                if(document.getElementById("checkBoxSelectAll").checked){
                    document.getElementById("checkBoxSelectAll").checked = false;
                }
            }
			disable("debut");
			disable("precedent");
			if(totalPage > 1 ) {
				enable("fin");
				enable("suivant");
			}
		}
		// Aller � la x i�me page
		if(type=="xeme") {
			currentPage=xeme;		
			//start = start + count;
			start = count * (currentPage - 1);
			loadGridAjax();
			enable("debut");
			enable("precedent");
			if(currentPage == totalPage) {
				disable("fin");
				disable("suivant");
			}
			
			if(currentPage == 1 && totalPage == 1) {
				disable("debut");
				disable("precedent");
				disable("fin");
				disable("suivant");
			}
			
			if(currentPage == 1) {
				disable("debut");
				disable("precedent");
		    }
		    
		    if(currentPage == 1 && totalPage > 1) {
				    disable("debut");
					disable("precedent");
					enable("fin");
					enable("suivant");
			}
			
			//Cacher barre de navigation au cas o� la liste est vide
	  		if (totalSize == 0){
	  			disable("currentpage");
	  			disable("totalpage");
	  		}
	  		else {
	  			enable("currentpage");
	  			enable("totalpage");
	  		}
		}
	}
	// D�sactive icone de navigation
	function disable(divName)  {
		divName = tbodyId + "-" + divName;
		var element = document.getElementsByName(divName);
		for(var i=0;i<element.length;i++)
			element[i].style.display = "none";
	}
	// Active icone de navigation
	function enable(divName)  
    {
		divName = tbodyId + "-" + divName;
		var element = document.getElementsByName(divName);
		for(var i=0;i<element.length;i++)
        {
			element[i].style.display = "inline";
        }
	}
	
	// Filtre les donn�es
	this.filtre = function(filtre) {
	// R�initialisation des donn�es car nouveaux crit�res de recherche
		currentPage = 1;	
		start = 0;
		
		filter = filtre;
		loadGridAjax();
	}
	
	// Filtre les crit�res
	this.critere = function(param) {
		// R�initialisation des donn�es car nouveaux crit�res de recherche
		currentPage = 1;	
		start = 0;
	
		extraParam = param;
		loadGridAjax();
	}
	
	// Tri par rapport � une colonne
	this.orderBy = function(object, columnName, trier) {
		var _trier = trier;
		 
		if(parentNodeSort != null)
			parentNodeSort.className = "sortable";
			
		parentNodeSort = object.parentNode;
		
		if(orderBy == columnName + " ASC") {
			orderBy = columnName + " DESC"
			parentNodeSort.className = "order2";
		}
		else {
			if(orderBy == columnName + " DESC")	
				orderBy = columnName + " ASC"
			else
				orderBy = columnName + " ASC"
			parentNodeSort.className = "order1";
		}
		
		if (_trier == null || _trier){
				loadGridAjax();
		}
			
	}
	
	
	// Appel AJAX
	function loadGridAjax() {
		// Affichage du loading
		
		showLoading();
		//if(document.getElementById('loading-mask') != null) 		showLoadingGrid('main_content');
		
		if(extraParam == null || extraParam == "" )
			dwrProxy(start, count, orderBy, filter, handleGetData);
		else
			dwrProxy(extraParam, start, count, orderBy, filter, handleGetData);
			//dwrProxy(handleGetData);
	}
	
	// Affichage du loading si existant dans la page
	function showLoading() {
		var element = document.getElementsByName(tbodyId + "-loading");
		/*if(element != null) {
			for(var i=0;i<element.length;i++) {
				new Effect.Opacity(element[i], {duration:1, from:0.4, to:0.4});
                 //Effect.Appear(element[i]);
			}
		}*/
	}
	
	// Suppression de l'affichage du loading si existant dans la page
	function hideLoading() {
		var element = document.getElementsByName(tbodyId + "-loading");
		if(element != null) {
			for(var i=0;i<element.length;i++)
				element[i].style.visibility = "hidden";
				//Effect.BlindUp(element[i]);
				//Effect.Fade(element[i]);
		}
	}
	
	// Changement de la page
	this.changePage = function(newPage) {
		var newCurrentPage = parseInt(newPage);
		if(!isNaN(newCurrentPage)) {
			if(newCurrentPage >= totalPage) {
			//DWREngine.setAsync(false);
				pagination("last");
			//DWREngine.setAsync(true);	
			//Cocher ou D�cocher la case d'en-t�te lors de la navigation
				
				return
			}
			if(newCurrentPage <= 1) {
				pagination("first");
			//Cocher ou D�cocher la case d'en-t�te lors de la navigation
				return;
			}
			currentPage = newCurrentPage;
			start = currentPage * count;
			loadGridAjax();
			//Cocher ou D�cocher la case d'en-t�te lors de la navigation
						
			enable("debut");
			enable("precedent");
			enable("fin");
			enable("suivant");
		}
	}
	
	// Rechargement du tableau
	 this.reload = function() {
	 	loadGridAjax();
	 }
	
	// Traitement de la r�ponse
	function handleGetData(str) {
	 	
	 	
		// Suppression du contenu du tableau
		dwr.util.removeAllRows(tbodyId);
		
		totalSize = str["totalSize"];
		//alert('totalSize -->'+totalSize); 
		var data = str["data"];
		
		// Mise � jour des variales sur la page
		dwr.util.setValue(tbodyId + "-totalSize", totalSize);
		totalPage = Math.ceil(totalSize / count);
		//alert('totalPage -->' +totalPage); 
		if (totalPage == 0)
			totalPage=1;
		
		//alert(tbodyId + "-totalpage"); 
		//alert(tbodyId + "-currentpage");
		var eltTotalPage = document.getElementsByName(tbodyId + "-totalpage");
		var eltCurrentPage = document.getElementsByName(tbodyId + "-currentpage");
		if(eltTotalPage != null) {
			for(var i=0;i<eltTotalPage.length;i++) {
				eltTotalPage[i].innerHTML = "/&nbsp;" + totalPage;
				eltCurrentPage[i].value = currentPage;
				//alert(eltTotalPage[i] + "        "+eltCurrentPage[i]);
			}
		}
		
		if(currentPage == 1 && totalPage > 1) {
		    disable("debut");
			disable("precedent");
			
			
			enable("fin");
			enable("suivant");
		}
		
		if(currentPage == 1 && totalPage == 1) {
			disable("debut");
			disable("precedent");
			disable("fin");
			disable("suivant");
		}
		
		dwr.util.addRows(tbodyId, data, cellFct, { 
			rowCreator:function(options) {
			    var row = document.createElement("tr");
			    var className = "even";
			    if(options.rowIndex % 2 == 0)
			    	className = "odd";
			    row.className = className;
			    return row;
			  },
  			escapeHtml:false });
  		
  		
  		//Cacher barre de navigation au cas o� la liste est vide
  		if (totalSize == 0){
  			disable("currentpage");
  			disable("totalpage");
  		}
  		else {
  			enable("currentpage");
  			enable("totalpage");
  		}
  			
  		// Suppression du loading
		hideLoading();
		//hideLoadingGrid();
		cocherDecocherEnTete();
		
	}
	
	// Retourne le num�ro de la page courante
	this.getCurrentPage = function() {
		return currentPage;
	}
	
	// Retourne le crit�re de tri
	this.getOrderBy = function() {
		return orderBy;
	}
	
	// Modifie la page courante
	this.setCurrentPage = function(val) {
	    xeme = val;
	   // alert('totalPage  ==> '+totalPage + '   currentPage ==> '+currentPage +' xeme  ==> ' +xeme);
	    pagination("xeme");
	   // alert('totalPage  ==> '+totalPage + '   currentPage ==> '+currentPage +' xeme  ==> ' +xeme);
	}
	
	//Cocher ou D�cocher la case d'en-t�te lors de la navigation
	function cocherDecocherEnTete(){
			
			//Cocher ou D�cocher la case d'en-t�te lors de la navigation
			
				var temp = document.getElementsByName('idSelected');
				var tempCount = new Array();
				for(var i=0;i<temp.length;i++){
					if(temp[i].checked){
					 	tempCount.push(temp[i]);
					}
				}
				
				if(tempCount.length < temp.length){
					if(document.getElementById("checkBoxSelectAll")) {
                        document.getElementById("checkBoxSelectAll").checked = false ;
                    }
				}
				
				if(tempCount.length == temp.length){
					if(document.getElementById("checkBoxSelectAll")) {
                        document.getElementById("checkBoxSelectAll").checked = true ;
                    }
					
				}
	
	}

	
}