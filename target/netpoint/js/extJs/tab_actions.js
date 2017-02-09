
function showTabs(idcol,idmission,typeCol,onglet, bouton,alertes){
	
	
	if (tabs != null){
		tabs.destroy();
	}
	//Collaborateur 
	if (onglet == '2' && typeCol == 'C'){
	   		tabs = new Ext.TabPanel({
			renderTo: "tabs",
			deferredRender: true,
			activeTab: 1,
			width:800,
			//height: Ext.get('main_content').getHeight()-50,
			height:320,
			plain: true,
			border: false,
			defaults:{autoScroll: false},
			items:[
				{
					id: 1,					
					title: 'Récap. Accueil',
					autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+typeCol+'&bouton='+bouton+'&aletres='+alertes , scripts: true }
				}
				,
				{
					id: 2,
					title: 'Fiche Individuelle',
					autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
				}
			]
				
		});
	}
	
	else if (onglet == '2' && typeCol != 'C'){ 
	
	tabs = new Ext.TabPanel({
			renderTo: "tabs",
			deferredRender: true,
			activeTab: 1,
			width:800,
		    //height: Ext.get('main_content').getHeight()-50,
		    height:320,
			plain: true,
			defaults:{autoScroll: false},
			items:[
				{
					id: 1,					
					title: 'Récap. Accueil',
					autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+typeCol+'&bouton='+bouton+'&aletres='+alertes , scripts: true }
				}
				
			]
				
		});
		
	
	}
	
	else {
			tabs = new Ext.TabPanel({
			renderTo: "tabs",
			deferredRender: true,
			activeTab: 0,
			width:800,
			//height: Ext.get('main_content').getHeight()-50,
			height:320,
			plain: true,
			defaults:{autoScroll: false},
			items:[
			    {
				id : 1,
				title: 'Fiche Individuelle',
				autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol+'&alertes='+alertes , scripts: true }
			    }
			]
		});
	}
 return tabs;
}

function addTab(tabsadd,idcollabo){
        tabsadd.add({
            id:2,
            title: 'Fiche Individuelle',
            autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcollabo , scripts: true }
        }).show();
}

function addTabTransfert(tabsadd,idtransfert,type){
       var tab1 = tabs.add({
            id:3,
            title: 'Récap Transfert',
            autoLoad: {url: 'gerer-la-fiche-transfert-process.htm?idtransfert='+idtransfert+'&type='+type+'&bouton=show' , scripts: true }
        }).show();
        return tab1;
   }
   
   
   function showTabsTransfertBisProcess(idcol,idmission,idtransfert, type, codeStatutTransfert){
   
   	if (tabs != null){
		tabs.destroy();
	}
   
   if (idmission != null && idmission != 0){
   
     if(idtransfert !=null && idtransfert!=0 && codeStatutTransfert=='PRE'){
	     tabs = new Ext.TabPanel({
					renderTo: "tabs",
					deferredRender: true,
					activeTab: 1,
					//height: Ext.get('main_content').getHeight()-30,
					width:800,
					height:320,
					plain:true,
					defaults:{autoScroll: false},
					items:[
						{
						    id: 0,
	                        autoWidth: true,
							title: 'Fiche Individuelle',
							autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
						}
						,
						{
							id: 1,
	                		autoWidth: true,
	                		title: 'Récap. Transfert',
							autoLoad: {url: 'gerer-la-fiche-transfert-process.htm?idtransfert='+idtransfert+'&idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
						}
					]
				});
     
          }else if(idtransfert !=null && idtransfert!=0 && codeStatutTransfert=='VAL'){
		          tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 2,
						//height: Ext.get('main_content').getHeight()-30,
						height:320,
						width:800,
		                plain:true,
						defaults:{autoScroll: false},
						items:[
							{
							    id: 0,
		                        autoWidth: true,
								title: 'Récap. Accueil',
								autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
							}
							,
							{
							    id: 1,
		                        autoWidth: true,
								title: 'Fiche Individuelle',
								autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
							}
							,
							{
								id: 2,
		                		autoWidth: true,
		                		title: 'Récap. Transfert',
								autoLoad: {url: 'gerer-la-fiche-transfert-process.htm?idtransfert='+idtransfert+'&idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
							}
						]
					});
          
          }else if(idtransfert !=null && idtransfert!=0 &&  codeStatutTransfert=='REF'){
		          tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 0,
						width:800,
						//height: Ext.get('main_content').getHeight()-30,
						height:320,
		                plain:true,
						defaults:{autoScroll: false},
						items:[
							{
							    id: 0,
		                        autoWidth: true,
								title: 'Fiche Individuelle',
								autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
							}
						]
					});
          }
		   		
	}
	else {
	      if(idtransfert !=null && idtransfert!=0 && codeStatutTransfert=='REF'){
			tabs = new Ext.TabPanel({
				renderTo: "tabs",
				deferredRender: true,
				activeTab: 0,
				width:800,
				//height: Ext.get('main_content').getHeight()-30,
				height:320,
				plain:true,
				defaults:{autoScroll: false},
				items:[
					{
					    id: 0,
                        autoWidth: true,
						title: 'Fiche Individuelle',
						autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
					}
				]
			});
		}else if(idtransfert !=null && idtransfert!=0) {
				tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 1,
						width:800,
						//height: Ext.get('main_content').getHeight()-50,
						height:320,
		                plain:true,
						defaults:{autoScroll: false},
						items:[
							{
							    id: 0,
		                        autoWidth: true,
								title: 'Fiche Individuelle',
								autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
							}
							,
							{
								id: 1,
		                		autoWidth: true,
		                		title: 'Récap. Transfert',
								autoLoad: {url: 'gerer-la-fiche-transfert-process.htm?idtransfert='+idtransfert+'&idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
							}
						]
					});
		
		}else{
		
		tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 1,
						width:800,
						//height: Ext.get('main_content').getHeight()-30,
						height:320,						
		                plain:true,
						defaults:{autoScroll: false},
						items:[
							{
							    id: 0,
		                        autoWidth: true,
								title: 'Fiche Individuelle',
								autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
							}
						]
					});
		
		}
	}

}


