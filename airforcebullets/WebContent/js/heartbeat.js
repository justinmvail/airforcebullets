
$(document).ready(function() {
    $.active = false;
    $('body').bind('click keypress', function() {
    	$.active = true; 
    });
    checkActivity(600000, 30000, 0); // timeout = 10 minutes, interval = 30 seconds.
//    checkActivity(60000, 3000, 0); //this is for testing, it is much faster.
});

function checkActivity(timeout, interval, elapsed) {
    if ($.active) {    	
        elapsed = 0;
        $.active = false;
        $.get('../restricted/HeartbeatAjax');//call the HTTP get of HeartBeat servlet.
    }
    if (elapsed < timeout) {
        elapsed += interval;
        setTimeout(function() {
            checkActivity(timeout, interval, elapsed);
        }, interval);
    } else {
        window.location = "../"; // Redirect to signIn page.
    }
}