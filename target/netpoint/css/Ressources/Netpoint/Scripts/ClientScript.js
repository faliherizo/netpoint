
function loadCarroussel()
{
    swfobject.embedSWF("Carousel.swf", "carousel2", "160", "130", "9.0.0", false, {xmlfile:"/XmlCarousel.aspx", loaderColor:"0x666666", messages:" :: :: :: "}, {bgcolor: "#000000"});
}


function loadFooter()
{
    var iFooterTop = $(window).height() - $("#footer").height();
    var iScrollHeight = iFooterTop - $("#divHeader").height() + 11;
    
    $("#footer").css({ "top": eval(iFooterTop) + "px" });
    $("#scroll").css({ "height": eval(iScrollHeight) + "px" });
    
    //    var iCorpLeft = ($(window).width() - $("#divCorps").width()) / 2;
//    $("#divCorps").css({ "left": eval(iCorpLeft) + "px" });
}

$(document).ready(function () {loadFooter();});
$(window).resize(function () { loadFooter();});