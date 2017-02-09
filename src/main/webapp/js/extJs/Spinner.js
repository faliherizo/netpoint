Ext.namespace("Ext.ux.form");

/**
 * @class Ext.ux.form.Spinner
 * @extends Ext.form.TriggerField
 * Provides a spinner input field.
* @constructor
* Create a new Spinner
* @param {Object} config
 */
Ext.ux.form.Spinner = Ext.extend(Ext.form.TriggerField,  {
	/**
	* @cfg {String} triggerClass
	* An additional CSS class used to style the trigger button.  The trigger will always get the
	* class 'x-form-trigger' and triggerClass will be <b>appended</b> if specified (defaults to 'x-form-spinner-trigger').
	*/
	triggerClass : 'x-form-spinner-trigger',
	/**
	* @cfg {String} splitterClass
	* An additional CSS class used to style the trigger button.
	*/
	splitterClass : 'x-form-spinner-splitter',
	
	/**
	* @cfg {String} alternateKey
	*
	*/
	alternateKey : Ext.EventObject.shiftKey,
	
	/**
	* @cfg {String} strategy
	*/
	strategy : undefined,
	
	//private
	onRender : function(ct, position){
		Ext.ux.form.Spinner.superclass.onRender.call(this, ct, position);

		this.splitter = this.wrap.createChild({tag:'div', cls:this.splitterClass, style:'width:13px; height:2px;'});
		this.splitter.show().setRight( (Ext.isIE) ? 1 : 2 );
		this.splitter.show().setTop(10);
		
		this.initSpinner();
	},
	
	initComponent : function(){
		Ext.ux.form.Spinner.superclass.initComponent.call(this);
		
		this.addEvents({
			'spinup' : true,
			'spindown' : true
		});
	},
	
	// private
    validateValue : function(value){
        // Sauvegarde de la valeur d'origine
        var svalue = value;
        
        value = value.trim();
        if (value.length < 1) {
        	// La donnée est vide
            this.markInvalid(String.format(this.invalidText, svalue, this.format));
            return false;
        }
        
        value = isNaN(value);
        if(value){
        	// La donnée n'est pas un numérique
            this.markInvalid(String.format(this.invalidText, svalue, this.format));
            return false;
        }
        
        if (svalue > this.strategy.maxValue) {
        	// La donnée est > à la valeur max de la stratégie
        	this.markInvalid(String.format(this.invalidText, svalue, this.format));
            return false;
        }
        
        if (svalue < this.strategy.minValue) {
        	// La donnée est < à la valeur min de la stratégie
        	this.markInvalid(String.format(this.invalidText, svalue, this.format));
            return false;
        }
       
		return true;
    },
	
	//private
	initTrigger : Ext.emptyFn,
	
	//private
	initSpinner : function(){
		this.keyNav = new Ext.KeyNav(this.el, {
			"up" : function(e){
				this.onSpinUp();
			},

			"down" : function(e){
				this.onSpinDown();
			},

			"pageUp" : function(e){
				this.onSpinUpAlternate();
			},

			"pageDown" : function(e){
				this.onSpinDownAlternate();
			},

			scope : this
		});

		this.repeater = new Ext.util.ClickRepeater(this.trigger.dom);
		this.repeater.on("click", this.onTriggerClick, this, {preventDefault:true});
		this.trigger.on("mouseover", this.onMouseOver, this, {preventDefault:true});
		this.trigger.on("mouseout", this.onMouseOut, this, {preventDefault:true});
		this.trigger.on("mousemove", this.onMouseMove, this, {preventDefault:true});
		this.trigger.on("mousedown", this.onMouseDown, this, {preventDefault:true});
		this.trigger.on("mouseup", this.onMouseUp, this, {preventDefault:true});
		this.wrap.on("mousewheel", this.handleMouseWheel,  this);

		if(this.strategy == undefined){
			this.strategy = new Ext.ux.form.Spinner.NumberStrategy();
		}
	},
	
	//private
	onMouseOver : function(){
		if(this.disabled){
			return;
		}
		var middle = this.getMiddle();
		this.__tmphcls = (Ext.EventObject.getPageY() < middle) ? 'x-form-spinner-overup' : 'x-form-spinner-overdown';
		this.trigger.addClass(this.__tmphcls);
	},

	//private
	onMouseOut : function(){
		this.trigger.removeClass(this.__tmphcls);
	},

	//private
	onMouseMove : function(){
		if(this.disabled){
			return;
		}
		var middle = this.getMiddle();
		if( ((Ext.EventObject.getPageY() > middle) && this.__tmphcls == "x-form-spinner-overup") ||
			((Ext.EventObject.getPageY() < middle) && this.__tmphcls == "x-form-spinner-overdown")){
		}
	},

	//private
	onMouseDown : function(){
		if(this.disabled){
			return;
		}
		var middle = this.getMiddle();
		this.__tmpccls = (Ext.EventObject.getPageY() < middle) ? 'x-form-spinner-clickup' : 'x-form-spinner-clickdown';
		this.trigger.addClass(this.__tmpccls);
	},

	//private
	onMouseUp : function(){
		this.trigger.removeClass(this.__tmpccls);
	},

	//private
	onTriggerClick : function(){
		if(this.disabled){
			return;
		}
		var middle = this.getMiddle();
		var ud = (Ext.EventObject.getPageY() < middle) ? 'Up' : 'Down';
		this['onSpin'+ud]();
	},

	//private
	getMiddle : function(){
		var t = this.trigger.getTop();
		var h = this.trigger.getHeight();
		var middle = t + (h/2);
		return middle;
	},

	handleMouseWheel : function(e){
		var delta = e.getWheelDelta();
		if(delta > 0){
			this.onSpinUp();
			e.stopEvent();
		} else if(delta < 0){
			this.onSpinDown();
			e.stopEvent();
		}
	},
	
	//private
	onSpinUp : function(){
		if(Ext.EventObject.shiftKey == true){
			this.onSpinUpAlternate();
			return;
		}else{
			this.strategy.onSpinUp(this);
		}
		this.fireEvent("spinup", this);
	},

	//private
	onSpinDown : function(){
		if(Ext.EventObject.shiftKey == true){
			this.onSpinDownAlternate();
			return;
		}else{
			this.strategy.onSpinDown(this);
		}
		this.fireEvent("spindown", this);
	},

	//private
	onSpinUpAlternate : function(){
		this.strategy.onSpinUpAlternate(this);
		this.fireEvent("spinup", this);
	},

	//private
	onSpinDownAlternate : function(){
		this.strategy.onSpinDownAlternate(this);
		this.fireEvent("spindown", this);
	}
	
	
});

Ext.reg('spinner', Ext.ux.form.Spinner);