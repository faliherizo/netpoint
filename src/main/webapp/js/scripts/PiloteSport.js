function SetImage(objControl) {

    if (objControl.getAttribute("disabled")) {
        objControl.src = objControl.getAttribute("ImageDisabled");
    }
    else {
        objControl.src = objControl.getAttribute("ImageEnabled");
    }
}

function initImages(tag) {

    var objControls = document.getElementsByTagName(tag);

    for (var i = 0; i < objControls.length; i++) {


        if (objControls[i].getAttribute("ImageEnabled") != null) {


            var sId = objControls[i].id;

            objControls[i].onmouseout = function () { SetOverImage(this, false); };
            objControls[i].onmouseover = function () { SetOverImage(this, true); };

            SetImage(objControls[i]);


        }
    }
}

function SetOverImage(objControl, bOver) {

    if (bOver == true) {
        objControl.src = objControl.getAttribute("ImageOver");
    }
    else {
        objControl.src = objControl.getAttribute("ImageEnabled");
    }

}
function LoadGallerry(iWidth, iWeight, iAutoplay)
{
 Galleria.loadTheme('Scripts/galleria/themes/classic/galleria.classic.min.js');
        $("#gallery").galleria({
                        width: iWidth,
                        height: iWeight,
                        imagePan: true,
                        autoplay: iAutoplay,
                        pauseOnInteraction: false,
                        transition: "pulse",
                        dataConfig: function(img) {return {title: $(img).attr('rel'), description: $(img).next('.desc').html()}}
                         });
}
function submitControl(btnId, e) {

    var iKeyCode = 0;
    if (e) {
        iKeyCode = e.keyCode;
    }
    else {
        iKeyCode = window.event.keyCode;
    }

    if (iKeyCode == 13) {
        document.getElementById(btnId).click();
        if (e) {
            e.keyCode = 0;
        }
        else {
            window.event.keyCode = 0;
        }

        return false;
    }
    else {
        return true;
    }

}
function LoadSlider(SliderId, iTimeOut)
{
    $("#"+ SliderId).s3Slider({timeOut: iTimeOut});
}

$(document).ready(
    function () {
        var isIE = (window.navigator.userAgent.indexOf("MSIE") > 0);

        if (!isIE) {
            HTMLElement.prototype.__defineGetter__("innerText",
              function () { return (this.textContent); });
            HTMLElement.prototype.__defineSetter__("innerText",
              function (txt) { this.textContent = txt; });
        }

        initImages("input");
        initImages("img");
    }
);


