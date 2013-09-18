
function sendEmail(){
	showLoading();
	var category = $('#reason :selected').text();	
	var message = $('#message').val();

	$.ajax({
		cache: false,
		type: 'POST',
		url: "../restricted/ContactAjax",
		dataType: 'json',
		data:{"category":category, "message":message},
		success: function(data) {
			if(data.hasOwnProperty("error")){
				closeLoading();
				showError("Your email could not be sent.  If the problem persists, please contact Air Force Bullets.");
			}else{
				$("#emailDialog").dialog("open");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}	
	});
	
	$('#message').val('');
}


$(function() {
    $( "#emailDialog" ).dialog({
    	autoOpen: false,
	    show: 'fade',
		hide: 'drop'
    });
});