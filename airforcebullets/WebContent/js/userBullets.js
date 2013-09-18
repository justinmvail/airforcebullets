//constructor for bullets--id will be used for ajax calls
function Bullet(id,date,catagory,text){
	this.id=id;
	this.date=date;
	this.catagory=catagory;
	this.text=text;
}

var oTable; 
//Loads the bullets when the page loads via ajax call

if(bulletID==null){
	getBulletsAjax();
}else{
	getBulletsAjax(bulletID);
}

$(function() {
	$( "#date" ).datepicker({dateFormat: "M d, yy"});
});

$(document).ready(function() {	
	
	/* Init the table */   
	$('#table').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="bulletTable"></table>' );
	
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
});


//Ajax calls---
function getBulletsAjax(userID){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/GetBulletAjax",
		data: {"userID":userID},
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

//front end support for the ajax(makes the dataTable reflect the db)
function addBulletToTable(bullet){
	$('#bulletTable').dataTable().fnAddData([bullet.date,bullet.catagory,bullet.text,bullet.id]);	
}



