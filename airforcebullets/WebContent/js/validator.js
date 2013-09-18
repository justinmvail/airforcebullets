
if (!Array.prototype.indexOf) { 
    Array.prototype.indexOf = function(obj, start) {
         for (var i = (start || 0), j = this.length; i < j; i++) {
             if (this[i] === obj) { return i; }
         }
         return -1;
    }
}

//regexp for checking email
var emailCheck = new RegExp("[a-z0-9]@[a-z]+\\.af\\.mil");  
//regexp for special character for names
var specialCharacterCheck = new RegExp("^[a-zA-Z-']*$");

//method to check email against regexp
function isEmailValid(){
	var strEmail = document.getElementById("email").value;    		
	if(emailCheck.test(strEmail, "i")){
		return true;
	}else{
		return false;
	}
}


function isRankValid(){
	var strRank = document.getElementById("rank").value;    		
	if(ranks.indexOf(strRank)==-1){
		return false;
	}else{
		return true;
	}
}

function isNameValid(strName){
	if(strName.length<2){
		return false;
	}
	if(specialCharacterCheck.test(strName)==false){
		return false;
	}
	return true;	
}

function isBaseValid(){
	var strBase = document.getElementById("base").value;    		
	if(bases.indexOf(strBase)==-1){
		return false;
	}else{
		return true;
	}
}

function isSquadronValid(){
	var strSquadron = document.getElementById("squadron").value;    		
	if(strSquadron.length==0){
		return false;
	}else{
		return true;
	}
}

function arePasswordsFilledIn(){
	if(document.getElementById("password").value.length>0){
		if(document.getElementById("passwordTwo").value.length>0){
			return true;
		}
	}
	return false;
}

function doPasswordsMatch(){
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("passwordTwo").value;
	if(password==password2){
		return true;
	}
	return false;
}