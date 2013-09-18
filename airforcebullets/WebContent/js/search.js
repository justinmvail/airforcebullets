if (!Array.prototype.indexOf) { 
    Array.prototype.indexOf = function(obj, start) {
         for (var i = (start || 0), j = this.length; i < j; i++) {
             if (this[i] === obj) { return i; }
         }
         return -1;
    }
}

function Person(id,rank, rankID, firstnm, lastnm, base, email){
	this.id=id;
	this.rank=rank;
	this.rankID=rankID;
	this.firstnm=firstnm;
	this.lastnm=lastnm;
	this.base=base;
	this.email=email;	
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
	$("#errorDialog").dialog({
		autoOpen: false,
		show: {
			effect: "fade",
			duration: 500
		},
		hide: {
			effect: "explode",
			duration: 500
		}
	});	
	
	$('#table').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="personTable"></table>' );
	
	$('#personTable').dataTable({
		"sDom":'<"H"<"button">lfr>t<"F"ip>',
		"aoColumns": [
           { "sTitle": "Rank","iDataSort": 6},
           { "sTitle": "First Name" },
           { "sTitle": "Last Name" },
           { "sTitle": "Base"},
           { "sTitle": "Email"},
           { "bVisible":false, "sTitle": "ID"},
           { "bVisible":false, "sTitle": "rankID"}
       ],
       "oLanguage": {"sEmptyTable": "Please enter the required criteria and click \"Find\" to see results."},
       "bJQueryUI": true,
       "bAutoWidth": false
       
	} );
	
	$("div.button").html('<button id = "requestBtn" name = "requestBtn" style="position:absolute; left:185px; top:5px;" disabled >Request Viewing Privileges</button>'); //this puts the button in the button div setup in the sDom attribute of the datatable
	$("#requestBtn").click(requestPrivileges); //addind a click event to the button i stuck in the header of the dataTable

	$('#personTable').on('click', 'tr', function(e){

		if ( $(this).hasClass('row_selected') ) {
            $(this).removeClass('row_selected');
        }
        else {
        	$('#personTable').dataTable().$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }

        //toggle buttons ---
		var pendingSelectedRows = getSelectedRowCount($('#personTable').dataTable());
		if(pendingSelectedRows==0){
			$('#requestBtn').attr("disabled", true);
		}else{
			$('#requestBtn').attr("disabled", false);
		}
    });
});

function searchUsers(){
		
	var firstName = $('#firstname').val();
	var lastName = $('#lastname').val();
	var rank = $('#rank').val();
	var base = $('#base').val();
	if(rank!="" && ranks.indexOf(rank)==-1){
		showError("You must select one of the prepopulated ranks or leave it blank");
		return;
	}
	if(base!="" && bases.indexOf(base)==-1){
		showError("You must select one of the prepopulated bases or leave it blank");
		return;
	}
	
	showLoading();
	$('#requestBtn').attr("disabled", true);	
	deleteAllRows('#personTable');	
	
	$.ajax({
		async: false,
		cache: false,
		type: 'GET',
		url: "../restricted/SearchAjax",
		dataType: 'json',
		data:{"firstName":firstName, "lastName":lastName, "rank":rank, "base":base},
		success: function(data) {			
			$.each(data, function(index, value) {
				var person = new Person(value.userID, value.rank, value.rankID, value.firstName, value.lastName, value.base, value.email);
				addPersonToTable('#personTable', person);
			});			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});	 	
}

function requestPrivileges(){	
	showLoading();
	$('#requestBtn').attr("disabled", true);	
	var id = $('#personTable').dataTable().fnGetData(fnGetSelected($('#personTable').dataTable())[0])[5];
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/RequestAjax",
		dataType: 'json',
		data:{"id":id},
		success: function(data) {
			if (!$.isEmptyObject(data)){
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
	deleteAllRows('#personTable');
	$('#firstname').val("");
	$('#lastname').val("");
	$('#rank').val("");
	$('#base').val("");	
}

function addPersonToTable(table, person){
	$(table).dataTable().fnAddData([person.rank, person.firstnm, person.lastnm, person.base, person.email,  person.id, person.rankID]);	
}

function deleteAllRows(table){
	var oSettings = $(table).dataTable().fnSettings();
	var iTotalRecords = oSettings.fnRecordsTotal();
	for (var i=0;i<=iTotalRecords;i++) {
		$(table).dataTable().fnDeleteRow(0,null,true);
	}
}

function getSelectedRowCount(oTableLocal){
	return oTableLocal.$('tr.row_selected').length;
}

function getRowIds(table){
	var bulletIds = new Array();
	var anSelected = fnGetSelected($(table).dataTable());
	for(var i=0;i<anSelected.length;i++){
		var row = anSelected[i];
		bulletIds[i]=$(table).dataTable().fnGetData(row)[3];
	}
	var ids = bulletIds.toString();
	return ids;
}

function fnGetSelected(localTable){
    return localTable.$('tr.row_selected');
}



