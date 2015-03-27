$body = $("body");

$(document).ready(function() {

    //HIDING ERROR AND SUCCESS DIVS ON PAGE LOAD
    //$("div.error-box").hide();
    $("div#message").hide();

    //FORM SUBMUIT FUNCTION
	$("form").on("submit", function(e) {
        e.preventDefault();
        var msgBox = $(e.currentTarget).find("div#message");
        var msgText = $(e.currentTarget).find("div#message p");
        var _data = $(e.currentTarget).serializeObject();
        $.ajax({
            type: e.currentTarget.method,
            url: e.currentTarget.action,
            data: JSON.stringify(_data),
            success: function(data) {
                if(data) {
                    console.log("DATA INSIDE SUCCESS: " + JSON.stringify(data));
                    console.log("DATA INSIDE SUCCESS: " + data.status);
                    if(data.status == 200) {
                        /*if($(e.currentTarget).data().uri) {*/
                            msgText.html(data.message);
                            msgBox.addClass("alert-info");
                            msgBox.show();
                            $(window).scrollTop($('div#message').offset().top);
                            $("form")[0].reset();
                            /*window.location=$(e.currentTarget).data().uri;*/
                        /*} else {
                            msgBox.html(data.message);
                            msgBox.addClass("alert-error");
                            msgText.show();
                            $(window).scrollTop($('div#message').offset().top);
                        }*/
                    } else {                  
                        msgText.html(data.message);
                        msgBox.addClass("alert-error");
                        msgBox.show();
                        $(window).scrollTop($('div#message').offset().top);
                    } 
                }
            },
            error: function(xhr, textStatus, errorThrown) {
                msgText.html(xhr.responseText);
                msgBox.addClass("alert-error");
                msgBox.show();
                $(window).scrollTop($('div#message').offset().top);
            },
            dataType: "json",
            contentType: "application/json"
        });
	});

//FUNCTIOZN TO SERIALIZE OBJECT
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                 if (!o[this.name].push) {
                     o[this.name] = [o[this.name]];
                 }
                 o[this.name].push(this.value || '');
            } else {
                 o[this.name] = this.value || '';
            }
        });
        return o;
    };
});