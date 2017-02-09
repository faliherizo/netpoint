function showTabs(idcol,idmission, type, bouton){
   
   	if (tabs != null){
		tabs.destroy();
	}
   
   if (type != null && type =='C'){
     if(idcol!=0){
			   		tabs = new Ext.TabPanel({
					renderTo: "tabs",
					deferredRender: true,
					activeTab: 0,
					width:800,
					//height: Ext.get('main_content').getHeight()+200,
					height:320,
	                plain:true,
					defaults:{autoScroll: false},
					items:[
						{
							title: 'Récap. Accueil',
							autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
						}
						,
						{
							title: 'Fiche Individuelle',
							autoLoad: {url: 'gerer-la-fiche-collaborateur.htm?idcol='+idcol , scripts: true }
						}
					]
				});
		}else{
		   tabs= new Ext.TabPanel({
			renderTo: "tabs",
			deferredRender: true,
			activeTab: 0,
			width:800,
			//height: Ext.get('main_content').getHeight()+200,
			height:320,
			plain:true,
			defaults:{autoScroll: false},
			items:[
				{
					title: 'Récap. Accueil',
					autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
				}
				
			]
		});
		}
		
	}
	else {
		tabs= new Ext.TabPanel({
			renderTo: "tabs",
			deferredRender: true,
			activeTab: 0,
			width:800,
			//height: Ext.get('main_content').getHeight()+200,
			height:320,
			plain:true,
			defaults:{autoScroll: false},
			items:[
				{
					title: 'Récap. Accueil',
					autoLoad: {url: 'gerer-la-fiche-accueil-es.htm?idmission='+idmission+'&type='+type+'&bouton=show' , scripts: true }
				}
				
			]
		});
	}

}


 function showTabsTransfertBisProcess(idcol,idmission,idtransfert, type, codeStatutTransfert){
   
   	if (tabs != null){
		tabs.destroy();
	}
   
   if (idmission != null && idmission != 0){
   
     if(codeStatutTransfert=='PRE'){
	     tabs = new Ext.TabPanel({
					renderTo: "tabs",
					deferredRender: true,
					activeTab: 1,
					//height: Ext.get('main_content').getHeight()-50,
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
     
          }else if(codeStatutTransfert=='VAL'){
		          tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 2,
						//height: Ext.get('main_content').getHeight()-50,
						width:800,
						height:320,
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
          
          }else if(codeStatutTransfert=='REF'){
		          tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 0,
						//height: Ext.get('main_content').getHeight()-50,
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
						]
					});
          }
		   		
	}
	else {
	      if(codeStatutTransfert=='REF'){
			tabs = new Ext.TabPanel({
				renderTo: "tabs",
				deferredRender: true,
				activeTab: 0,
				width:800,
				height:320,
				// height: Ext.get('main_content').getHeight()-50,
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
		}else{
				tabs = new Ext.TabPanel({
						renderTo: "tabs",
						deferredRender: true,
						activeTab: 1,
						width:800,
						height:320,
						//height: Ext.get('main_content').getHeight()-50,
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
		
		}
	}

}


function showTabsAcProcess(idcol,idmission,type){
   	if (tabs != null){
		tabs.destroy();
	}
	   
   if (idmission != null && idmission != 0){
	    if (tabs != null){
			tabs.destroy();
		}
        if (type != null && type =='C' ){
	   				tabs = new Ext.TabPanel({
							renderTo: "tabs",
							deferredRender: true,
							activeTab: 1,
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
			                		title: 'Récap. AC',
									autoLoad: {url: 'fiche-ac.htm?idmission='+idmission+'&type='+type+'&bouton=show&idcol='+idcol , scripts: true }
								}
							]
						});	
	    }else if (type != null && ( type =='I' ||  type =='T')){
			    if (tabs != null){
				  tabs.destroy();
			    }
	            tabs = new Ext.TabPanel({
							renderTo: "tabs",
							deferredRender: true,
							activeTab: 0,
							width:800,
							height:320,
			                plain:true,
							defaults:{autoScroll: false},
							items:[
								{
									id: 0,
			                		autoWidth: true,
			                		title: 'Récap. AC',
									autoLoad: {url: 'fiche-ac.htm?idmission='+idmission+'&type='+type+'&bouton=show&idcol='+idcol , scripts: true }
								}
							]
						});	
	    }
	}
}
