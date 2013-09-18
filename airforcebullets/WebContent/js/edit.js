function getUserInfo(){
	showLoading();
	$.ajax({
		cache: false,
		async: false,
		type: 'GET',
		url: "../restricted/GetUserAjax",
		dataType: 'json',
		success: function(data) {		
			$('#firstname').val(data.firstName);
			$('#lastname').val(data.lastName);
			$('#middlename').val(data.middleName);
			$('#rank').val(data.rank);
			$('#squadron').val(data.squadron);
			$('#base').val(data.base);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});	
}

//jquery autocomplete bases 
$(function() {
	$( "#base" ).autocomplete({    			
		source: bases
	});
});

//jquery autocomplete ranks
$(function() {
	$( "#rank" ).autocomplete({    			
		source: ranks
	});
});

$(document).ready(function() {
	getUserInfo();
	//setting up click events
	$("#passwordButton" ).click(function() {
		clearPasswordDialog();
		$( "#passwordDialog" ).dialog("open");
	});
	$("#passwordsubmit" ).click(function() {		
		editPassword();
	});
	$("#emailButton" ).click(function() {
		
		$( "#emailDialog" ).dialog("open");
	});
	$("#emailsubmit" ).click(function() {
		changeEmail();
	});	
	$("#deleteButton" ).click(function() {
		
		$( "#deleteDialog" ).dialog("open");
	});
	$("#deleteSubmit" ).click(function() {
		deleteAccount();
	});
	
	
	
	
	//showError("testing");

});

function editAccount(){	
//	showLoading();
	var firstName = $('#firstname').val();
	var lastName = $('#lastname').val();
	var middleName = $('#middlename').val();
	var rank = $('#rank').val();
	var squadron = $('#squadron').val();
	var base = $('#base').val();
	$.ajax({
		cache: false,
		async: false,
		type: 'POST',
		url: "../restricted/UpdatePersonAjax",
		dataType: 'json',
		data: {"firstName":firstName, "lastName": lastName, "middleName":middleName, "rank":rank, "squadron":squadron, "base":base},
		success: function(data) {			

		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
//			closeLoading();
			location.reload(); 
		}
	});	
}

function editPassword(){	
	showLoading();
	var oldPassword = $('#oldpassword').val();
	var password = $('#password1').val();
	var password2 = $('#password2').val();
	if(password==password2){	
		$.ajax({
			cache: false,
			type: 'POST',
			url: "../restricted/UpdatePasswordAjax",
			dataType: 'json',
			data: {"oldPassword":oldPassword, "password": password},
			success: function(data) {
				if(data.hasOwnProperty("error")){
					showError(data.error);
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				showError(jqXHR+" - "+textStatus+" - "+errorThrown);
			},
			complete: function(){
				closeLoading();
			}
		});	
		$( "#passwordDialog" ).dialog("close");
	}else{
		closeLoading();
		showError("Your new passwords do not match");
	}
}

function changeEmail(){	
	showLoading();
	setTimeout(function(){

	var password = $('#emailPassword').val();
	var email1 = $('#email').val();
	var email2 = $('#email2').val();
	if(email1==email2){
		if(!isEmailValid()){
			showError("That is not a valid email.  Please insert a valid Air Force email.");
			closeLoading();
			return;
		}
		$.ajax({
			cache: false,
			type: 'POST',
			url: "../restricted/UpdateEmailAjax",
			dataType: 'json',
			data: {"password":password, "email": email1},
			success: function(data) {
				if(data.hasOwnProperty("error")){
					showError(data.error);
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				showError(jqXHR+" - "+textStatus+" - "+errorThrown);
			},
			complete: function(){
				closeLoading();
			}
		});	
		$( "#emailDialog" ).dialog("close");
	}else{
		closeLoading();
		showError("Your email fields do not match.");
	}
	},500);
}

function deleteAccount(){	
	showLoading();	
	var password = $('#deletePassword').val();
	$.ajax({
		cache: false,
		type: 'POST',
		url: "../restricted/DeleteAccountAjax",
		dataType: 'json',
		data: {"password": password},
		success: function(data) {
			if(data.hasOwnProperty("error")){
				showError(data.error);
			}else{
				window.location = "../";//takes the user back to the home page.
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});	
	
}


function isPageValid(){	
	if(!isNameValid(document.getElementById("firstname").value)){
		showError("The first name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");	
		return;
	}
	if(document.getElementById("middlename").value != "" && !isNameValid(document.getElementById("middlename").value)){
		showError("The middle name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");
		return;
	}
	if(!isNameValid(document.getElementById("lastname").value)){
		showError("The last name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");
		return;
	}
	if(!isRankValid()){
		showError("That is not a valid rank. This field has an auto-complete feature.\nBe sure to select one of populated options.");
		return;
	}
	if(!isSquadronValid()){
		showError("The Squadron field is empty.");
		return;
	}
	if(!isBaseValid()){
		showError("That is not a valid base. This field has an auto-complete feature.\nBe sure to select one of populated options.");
		return;
	}	
	editAccount();
}

function clearPasswordDialog(){
	document.getElementById('oldpassword').value = "";
	document.getElementById('password1').value = "";
	document.getElementById('password2').value = "";
}

$(function() {
	$( "#passwordDialog" ).dialog({
		autoOpen: false,
		modal: true,
		show: 'fade',
		hide: 'drop', 
		width: '280px'
	});
});

$(function() {
	$( "#emailDialog" ).dialog({
		autoOpen: false,
		modal: true,
		show: 'fade',
		hide: 'drop', 
		width: '300px'
	});
});

$(function() {
	$( "#deleteDialog" ).dialog({
		autoOpen: false,
		modal: true,
		show: 'fade',
		hide: 'drop', 
		width: '300px'
	});
});


