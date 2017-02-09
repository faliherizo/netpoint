Ext.ux.MyDatePicker = Ext.extend(Ext.DatePicker, {
	initComponent:function() {
		// call parent initComponent
		Ext.ux.MyDatePicker.superclass.initComponent.call(this);
	},
	onRender:function(container, position) {
		// call parent onRender
		Ext.ux.MyDatePicker.superclass.onRender.call(this, container, position);
			
		var delBtn = new Ext.Button({
			renderTo: this.el.child("td.x-date-bottom", true) 
				,text: 'Initialiser'
				,handler: this.clearField				
				,scope: this
		}); 
	},
    clearField : function(){        
        this.fireEvent("select", this, "");
    }
});        
// register xtype
Ext.reg('mydatepicker', Ext.ux.MyDatePicker);  

Ext.ux.MyDateItem = function(config){
    Ext.ux.MyDateItem.superclass.constructor.call(this, new Ext.ux.MyDatePicker(config), config);
    /** The Ext.DatePicker object @type Ext.DatePicker */
    this.picker = this.component;
    this.addEvents('select');
    
    this.picker.on("render", function(picker){
        picker.getEl().swallowEvent("click");
        picker.container.addClass("x-menu-date-item");
    });

    this.picker.on("select", this.onSelect, this);
};

Ext.extend(Ext.ux.MyDateItem, Ext.menu.Adapter, {
    // private
    onSelect : function(picker, date){
        this.fireEvent("select", this, date, picker);
        Ext.ux.MyDateItem.superclass.handleClick.call(this);
    }
});


Ext.ux.MyDateMenu = function(config){
    Ext.ux.MyDateMenu.superclass.constructor.call(this, config);
    this.plain = true;
    var di = new Ext.ux.MyDateItem(config);
    this.add(di);
    /**
     * The {@link Ext.DatePicker} instance for this DateMenu
     * @type DatePicker
     */
    this.picker = di.picker;
    /**
     * @event select
     * @param {DatePicker} picker
     * @param {Date} date
     */
    this.relayEvents(di, ["select"]);

    this.on('beforeshow', function(){
        if(this.picker){
            this.picker.hideMonthPicker(true);
        }
    }, this);
};
Ext.extend(Ext.ux.MyDateMenu, Ext.menu.Menu, {
    cls:'x-date-menu'
});



Ext.ux.MyDateField = function(config){
    Ext.ux.MyDateField.superclass.constructor.call(this, config);
};
Ext.extend(Ext.ux.MyDateField, Ext.form.DateField, {
    initComponent : function(){
        Ext.ux.MyDateField.superclass.initComponent.call(this);
        if(typeof this.minValue == "string"){
            this.minValue = this.parseDate(this.minValue);
        }
        if(typeof this.maxValue == "string"){
            this.maxValue = this.parseDate(this.maxValue);
        }
        this.ddMatch = null;
        if(this.disabledDates){
            var dd = this.disabledDates;
            var re = "(?:";
            for(var i = 0; i < dd.length; i++){
                re += dd[i];
                if(i != dd.length-1) re += "|";
            }
            this.ddMatch = new RegExp(re + ")");
        }
    },
    onTriggerClick : function(){
        if(this.disabled){
            return;
        }
        if(this.menu == null){
            this.menu = new Ext.ux.MyDateMenu();
        }
        
        
        Ext.apply(this.menu.picker,  {
            minDate : this.minValue,
            maxDate : this.maxValue,
            disabledDatesRE : this.ddMatch,
            disabledDatesText : this.disabledDatesText,
            disabledDays : this.disabledDays,
            disabledDaysText : this.disabledDaysText,
            format : this.format,
            minText : String.format(this.minText, this.formatDate(this.minValue)),
            maxText : String.format(this.maxText, this.formatDate(this.maxValue))
        });
        this.menu.on(Ext.apply({}, this.menuListeners, {
            scope:this
        }));
        this.menu.picker.setValue(this.getValue() || new Date());
        this.menu.show(this.el, "tl-bl?");
    }
});