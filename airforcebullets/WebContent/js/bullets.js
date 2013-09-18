//constructor for bullets--id will be used for ajax calls
function Bullet(id,date,catagory,text){
	this.id=id;
	this.date=date;
	this.catagory=catagory;
	this.text=text;
}




var oTable; 
//Loads the bullets when the page loads via ajax call


$(function() {
	$( "#date" ).datepicker({
		dateFormat: "M d, yy", 
		changeMonth: true,
		changeYear: true
	});
});

$(document).ready(function() {
	/* Init the table */   
	$('#table').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="bulletTable"></table>' );
	
	/* Add a click handler to the rows - this could be used as a callback */  
	$('#bulletTable').on('click', 'tr', function(e){
		$(this).toggleClass('row_selected');
		var selectedRows = getSelectedRowCount(oTable);
		if(selectedRows==0){
			$('#edit').attr("disabled", true);
			$('#delete').attr("disabled", true);
		}else if (selectedRows==1){
			$('#edit').attr("disabled", false);
			$('#delete').attr("disabled", false);
		}else{
			$('#edit').attr("disabled", true);
			$('#delete').attr("disabled", false);
		}
	});
	
	oTable = $('#bulletTable').dataTable({
		"sDom":'T<"H"<"add"><"edit"><"delete">lfr>t<"F"ip>',
		"oTableTools": {
			"sSwfPath": "../js/lib/copy_csv_xls_pdf.swf",		
			"aButtons": [
			     {
			    	 "sExtends": "pdf",
			    	 "sButtonText": "Save to PDF",
			    	 "mColumns": [ 0, 1, 2 ],
			    	 "sFileName": userName+ " - Air Force Bullets.pdf",
			    	 "sPdfMessage": userName
			     }
			]
			     
		},
		"aoColumns": [
			{ "sWidth": "15%", "sTitle": "Date", "sClass": "center" },
			{ "sWidth": "15%", "sTitle": "Category", "sClass": "center" },
			{ "sWidth": "70%", "sTitle": "Bullet" },
			{ "bVisible":false, "sTitle": "ID"}
		],
		"aaSorting": [[0, "desc"],[3, "desc"]],
		"bJQueryUI": true,	
		"bAutoWidth": false
	});
	
	$("div.add").html('<button id = "add" name = "add" class = "button" style="position:absolute; left:340px; top:24px;">Add</button>'); //this puts a button in the add div setup in the sDom attribute of the datatable
	$("div.edit").html('<button id = "edit" name = "edit" class = "button" style="position:absolute; left:392px; top:24px;" disabled >Edit</button>'); //this puts a button in the edit div setup in the sDom attribute of the datatable
	$("div.delete").html('<button id = "delete" name = "delete" class = "button" style="position:absolute; left:444px; top:24px;" disabled >Delete</button>'); //this puts a button in the delete div setup in the sDom attribute of the datatable

	
	/* Add a click handler for the delete row */    
	$('#delete').click( function() {
		var bulletIds = new Array();
		var anSelected = fnGetSelected(oTable);
		for(var i=0;i<anSelected.length;i++){
			var row = anSelected[i];
			bulletIds[i]=oTable.fnGetData(row)[3];
			oTable.fnDeleteRow(anSelected[i]);
		}
		$('#edit').attr("disabled", true);
		$('#delete').attr("disabled", true);
		var ids = bulletIds.toString();
		deleteBulletAjax(ids);		
	});
	
	//this has to be done onLoad so that user can see his/her bullets immediately. 
	getBulletsAjax();

});

//functions---
function fnGetSelected( oTableLocal ){
	return oTableLocal.$('tr.row_selected');
}

function getSelectedRowCount(oTableLocal){
	return oTableLocal.$('tr.row_selected').length;
}
		
$(function() {
	var date = $( "#date" ),
		category = $( "#category" ),
		bullet = $( "#bullet" );
		
	$( "#dialog-form" ).dialog({
		show: 'fade',
		hide: 'drop',
		autoOpen: false,
		height: 292,
		width: 460,
		modal: true,
		buttons: {
			"Submit": function() {
				if(date.val()==""||category.val()==""||bullet.val()==""){
					showError("Please fill out all fields to complete the bullet.");
				}				
				//When creating a new bullet			
				else if($('#dialog-form').dialog('option','title')=="Create New Bullet"){
					//insert the bullet in database.  insertBulletAjax also calls the method for the front end update.
					insertBulletAjax(date.val(),category.val(),bullet.val());
					$( this ).dialog( "close" );
				//When editing a bullet
				}else{
					//update a bullet in database and front end
					var row = fnGetSelected(oTable)[0];
					var id = oTable.fnGetData(row)[3];//gets the id of selected row.
					updateBulletAjax(id, date.val(),category.val(),bullet.val(), row);
					$( this ).dialog( "close" );
				}
				
			},
			"Cancel": function() {
				$( this ).dialog( "close" );
			}					
		}
	});

	$( "#add" )
	.click(function() {
		$('#dialog-form').dialog('option', 'title', 'Create New Bullet');
		$( "#dialog-form" ).dialog( "open" );
		$( "#date" )[0].value = "";
		$( "#category" )[0].value = "";
		$( "#bullet" )[0].value = "";
	});
	$( "#edit" )
	.click(function() {				
		var cells = oTable.fnGetData(fnGetSelected(oTable)[0]);
		$('#dialog-form').dialog('option', 'title', 'Edit Bullet');
		$( "#dialog-form" ).dialog( "open" );
		$( "#date" )[0].value = cells[0];
		$( "#category" )[0].value = cells[1];
		$( "#bullet" )[0].value = cells[2];
	});
});


$(function() {
	var availableTags = [
		"Work",
		"Education",
		"Community"
	];
	$( "#category" ).autocomplete({
		source: availableTags
	});
});

//Ajax calls---
function getBulletsAjax(){
	
	showLoading();
	$.ajax({
		//async: false,  //might not be necessary 
		cache: false,
		type: 'GET',
		url: "../restricted/GetBulletAjax",
		dataType: 'json',
		success: function(data) {			
			$.each(data, function(index, value) {
				var bullet = new Bullet(value.bulletID, value.accomplishedDate, value.catagory, value.bulletText);
				addBulletToTable(bullet);
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

function insertBulletAjax(date, catagory, text){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/InsertBulletAjax",
		data: {"date":date, "catagory":catagory, "text":text},
		dataType: 'json',     
		success: function(data) {
			var bullet = new Bullet(data.bulletID, data.accomplishedDate, data.catagory, data.bulletText);
			addBulletToTable(bullet);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError("insert "+jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});	
}

function updateBulletAjax(id ,date, catagory, text, row){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/UpdateBulletAjax",
		data: {"id":id, "date":date, "catagory":catagory, "text":text},
		dataType: 'json',     
		success: function(data) {
			var bullet = new Bullet(data.bulletID, data.accomplishedDate, data.catagory, data.bulletText);
			updateBulletInTable(bullet, row);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError("update "+jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});
}

function deleteBulletAjax(ids){
	showLoading();
	$.ajax({
		cache: false,
		type: 'POST',
		url: "../restricted/DeleteBulletAjax",
		data: {"ids":ids},
		error: function(jqXHR, textStatus, errorThrown) {
			showError("update "+jqXHR+" - "+textStatus+" - "+errorThrown);
		},
		complete: function(){
			closeLoading();
		}
	});
}

//front end support for the ajax(makes the dataTable reflect the db)
function addBulletToTable(bullet){
	$('#bulletTable').dataTable().fnAddData([bullet.date,bullet.catagory,bullet.text,bullet.id]);	
}

function updateBulletInTable(bullet, row){
	$('#bulletTable').dataTable().fnUpdate([bullet.date,bullet.catagory,bullet.text,bullet.id], row);
}
