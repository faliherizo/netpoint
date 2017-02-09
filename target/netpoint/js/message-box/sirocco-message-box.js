function SiroccoMsgBox(type, titre, message) {
    
    Ext.MessageBox.show({
        title:titre,
        msg:message,
        buttons: Ext.Msg.OK,
        fn: function (btn) {},
        animEl: 'elId',    
	icon: type
    });
}