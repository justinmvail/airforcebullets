var startTime;
var endTime;

$(function() {
    $("#loadingDialog").dialog({
    	autoOpen: false,
    	show: {
	    	effect: "fade",
	    	duration: 250
	    },
    	hide: {
	    	effect: "fade",
	    	duration: 250
    	},
    	modal: true,
    	height: 100,
    	width: 200
    });
    $("#loadingDialog").siblings(".ui-dialog-titlebar").hide(); //this hides the Menu bar of the jquery Dialog making one hell of a loading screen.
});


function showLoading(message){
	//this loads the loading gif.
	$("#loadingDialog").text("");
	$("#loadingDialog").append("<img src=\"../css/images/loading.gif\" alt=\"Smiley face\" height=\"30\" width=\"30\">");
	$("#loadingDialog").append("<br>");
	$("#loadingDialog").append("Processing... Please wait.");	
	$("#loadingDialog").append("<br>");
	if(message == null || message == ""){
		$("#loadingDialog").append("Your request will finish soon.");
	}else{
		$("#loadingDialog").append(message);
	}
	$("#loadingDialog").dialog("open");
	
	startTime = new Date();
}


function closeLoading(){
	endTime = new Date();
	var difference = endTime.getTime()-startTime.getTime();
	if (difference<500){
		setTimeout(function() {
			$("#loadingDialog").dialog('close');
		}, 500-difference);
	}else{
		$("#loadingDialog").dialog('close');	
	}
}



