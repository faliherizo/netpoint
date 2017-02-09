//Create user extensions namespace (Ext.ux)
Ext.namespace('Ext.ux');
 
/**
  * Ext.ux.IconCombo Extension Class
  *
  * @author  Jozef Sakalos
  * @version 1.0
  *
  * @class Ext.ux.IconCombo
  * @extends Ext.form.ComboBox
  * @constructor
  * @param {Object} config Configuration options
  */
  
  /**
    * Modified by ALi LABIED the 06/07/2009 b
    *
    */
Ext.ux.IconCombo = function(config) {
 
    //call parent constructor
    Ext.ux.IconCombo.superclass.constructor.call(this, config);
    
   
    this.tpl = config.tpl ||
          '<tpl for="."><div class="x-combo-list-item x-icon-combo-item {' 
        + this.iconClsField 
        + '}"> {' 
        + this.displayField 
        + '}</div></tpl>'
    ;
       
        
                              
    this.on({
        render:{scope:this, fn:function() {
            var wrap = this.el.up('div.x-form-field-wrap');
            this.wrap.applyStyles({position:'relative'});
            this.el.addClass('x-icon-combo-input');
            
        }}
    });
}//end of Ext.ux.IconCombo constructor
 
//extend

Ext.extend(Ext.ux.IconCombo, Ext.form.ComboBox, {
     setIconCls: function() {
       var rec=null;
       var wrap = this.el.up('div.x-form-field-wrap');
       this.flag = Ext.DomHelper.append(wrap, {
                tag: 'div', style:'position:absolute'
       });
       
       
       rec = this.store.query(this.valueField, this.getValue(),true,true).itemAt(0);
       var idSocieteTmp = this.getValue();
       var flagValue =null;
    
       if(this.valueField=='idSocieteEtt') {
			     this.store.each(function(el) {
			          if(el.data.idSocieteEtt == idSocieteTmp)  flagValue = el.data.statutFlag;
								
					});
					
				if(flagValue!=null) this.flag.className = 'x-icon-combo-icon ' + flagValue;
		        else this.flag.className = 'x-icon-combo-icon ' + 'valide';
        }else  this.flag.className = 'x-icon-combo-icon ' + rec.get(this.iconClsField);
          
        if(this.valueField=='idAgenceEtt' && (this.getValue()==0  || this.getValue()=='0'))  this.flag.className = 'x-icon-combo-icon ' + 'valide';
        rec = null;
    },
 
    setValue: function(value) {
        Ext.ux.IconCombo.superclass.setValue.call(this, value);
        this.setIconCls();
    }
}); //end of extend
 
//end of file