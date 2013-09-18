
$(function() {
    $("#errorDialog").dialog({
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
    	height: 150,
    	width: 300
    });
});

function showError(message){
	$("#errorDialog").dialog('option', 'title', 'Error');
	$("#errorDialog").text("");
	$("#errorDialog").append(message);	
	$("#errorDialog").dialog("open");
}
