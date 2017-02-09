     // Affichage du loading si existant dans la page
     function showLoadingGrid(pageElement) 
     {
        var maskHtml = document.getElementById('loading-mask');
        
        var horsMainContentSize = 
        	(document.getElementById("page_main") != null) ? (document.getElementById("page_main").offsetTop + 69) : (document.getElementById("page_main2").offsetTop + 69);
		maskHtml.style.display = "block";
                   
        // Taille totale du main_content                    
        var mainContentSize = parseInt(document.body.offsetHeight) - horsMainContentSize;
        
        document.getElementById('loading-mask').style.height = mainContentSize;
        document.getElementById('loading-mask').style.width = document.getElementById(pageElement).offsetWidth - 2 + "px";
        maskHtml.style.visibility = "visible";
        maskHtml.style.display = "block";
        new Effect.Opacity('loading-mask', {duration:1, from:0.4, to:0.4});
     }

	// Suppression de l'affichage du loading si existant dans la page
	function hideLoadingGrid() 
    {
	    var maskHtml = document.getElementById('loading-mask');
	    maskHtml.style.visibility = "hidden";
	    maskHtml.style.display = "none"
	}  