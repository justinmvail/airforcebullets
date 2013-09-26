//Sign Up Info Dialog code
$(function() {
	$( "#dialog" ).dialog({
		draggable: "false",
		show: 'fade',
		hide: 'drop',
		modal: true,
		open: function(){
            $('.ui-widget-overlay').hide().fadeIn();
        },
		autoOpen: false
		
	});
	$( "#nav" ).click(function() {
		$( "#dialog" ).dialog( "open" );
	});
});




function isPageValid(){
	if(!isEmailValid()){
		showError("The email address you provided is not a valid Air Force account.\ne.g.: firstname.lastname@somewhere.af.mil");
		return;
	}
	if(!isRankValid()){
		showError("That is not a valid rank. This field has an auto-complete feature.\nBe sure to select one of the populated options.");
		return;
	}
	if(!isNameValid(document.getElementById("firstName").value)){
		showError("The first name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");	
		return;
	}
	if(document.getElementById("middleName").value!="" && !isNameValid(document.getElementById("middleName").value)){
		showError("The middle name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");
		return;
	}
	if(!isNameValid(document.getElementById("lastName").value)){
		showError("The last name you have entered is not valid.\nNames must be at least two characters long\nand cannnot contain any special characters.");
		return;
	}
	if(!isBaseValid()){
		showError("That is not a valid base. This field has an auto-complete feature.\nBe sure to select one of the populated options.");
		return;
	}
	if(!isSquadronValid()){
		showError("The Squadron field is empty.");
		return;
	}
	if(!arePasswordsFilledIn()){
		showError("At least one of the password fields is empty.");
		return;
	}
	if(!doPasswordsMatch()){
		showError("The passwords you entered do no match.");
		return;
	}
	document.forms["signUp"].submit();
}