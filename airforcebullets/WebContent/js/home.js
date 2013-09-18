function Person(id,rank, rankID, firstnm, lastnm, base, email){
	this.id=id;
	this.rank=rank;
	this.rankID=rankID;
	this.firstnm=firstnm;
	this.lastnm=lastnm;
	this.base=base;
	this.email=email;	
}

function Notification(id, date, message){
	this.id=id;
	this.date=date;
	this.message=message;
}

//all three datatables declared
var pendingTable, viewerTable, vieweeTable; 

$(document).ready(function() {	
	
	var now = new Date();
	
	$(function() {
		$( "#notificationAccordion" ).accordion({
			collapsible: true,
			heightStyle: "content",
			active:false
		});
	});
	
	$('#notificationTableDiv').html( '<button id = "notificationDeleteBtn" name = "notificationDeleteBtn" class = "button" disabled>delete</button><table cellpadding="0" cellspacing="0" border="0" class="display" id="notificationTable"></table>' );
	$('#pendingTableDiv').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="pendingTable"></table>' );
	$('#viewerTableDiv').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="viewerTable"></table>' );
	$('#vieweeTableDiv').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="vieweeTable"></table>' );
	
	
	$('#notificationTable').dataTable({
       "aoColumns": [
           { "sTitle": "", sDefaultContent: "n/a"  },
           { "sTitle": "", sDefaultContent: "n/a"  },
           { "bVisible":false,"sTitle": "", sDefaultContent: "n/a"  }
       ],
       "sDom": 't',
       "bSort":false,
       "iDisplayLength": 100,
       "oLanguage": {"sEmptyTable": "You currently have no notifications."},
       "bAutoWidth": false
	});
			
	
	$('#pendingTable').dataTable({
       "aoColumns": [
           { "sTitle": "Rank", "iDataSort": 6 },
           { "sTitle": "First Name" },
           { "sTitle": "Last Name" },
           { "sTitle": "Base"},
           { "sTitle": "Email"},
           { "bVisible":false, "sTitle": "ID"},
           { "bVisible":false, "sTitle": "RankID"}
       ],
       "sDom":"<'H'<'approve'><'deny'>fr>t<'F'ip>",
       "aLengthMenu": [5, 10, 25, 50, 100],      
       "iDisplayLength": 5,
       "oLanguage": {"sEmptyTable": "You currently have no viewer requests."},
       "bJQueryUI": true,
       "bAutoWidth": false
	});
	$("div.approve").html('<button id = "pendingApproveBtn" name = "pendingApproveBtn" class = "button" style="position:absolute; left:5px; top:3px;" disabled>Approve</button>'); //this puts a button in the add div setup in the sDom attribute of the datatable
	$("div.deny").html('<button id = "pendingDenyBtn" name = "pendingDenyBtn" class = "button" style="position:absolute; left:75px; top:3px;" disabled >Deny</button>'); //this puts a button in the edit div setup in the sDom attribute of the datatable
	
		
	$('#viewerTable').dataTable({		
       "aoColumns": [
           { "sTitle": "Rank", "iDataSort": 4},
           { "sTitle": "First Name" },
           { "sTitle": "Last Name" },
           { "bVisible":false, "sTitle": "ID"},
           { "bVisible":false, "sTitle": "RankID"}
       ],
       "sDom":"<'H'<'viewerDelete'>fr>t<'F'ip>",
       "oLanguage": {"sEmptyTable": "No one else can view your bullets."},       
       "bJQueryUI": true,	
	   "bAutoWidth": false,
	   "bScrollInfinite": true,
	   "sScrollY": 100
	});
	$("div.viewerDelete").html('<button id = "viewerDeleteBtn" name = "viewerDeleteBtn" class = "button" style="position:absolute; left:5px; top:3px;" disabled>Delete</button>'); //this puts a button in the add div setup in the sDom attribute of the datatable
	
	
	$('#vieweeTable').dataTable( {		
       "aoColumns": [
           { "sTitle": "Rank", "iDataSort": 4},
           { "sTitle": "First Name" },
           { "sTitle": "Last Name" },
           { "bVisible":false, "sTitle": "ID"},
	       { "bVisible":false, "sTitle": "RankID"}
       ],
       "sDom":"<'H'<'view'><'delete'>fr>t<'F'ip>",
       "oLanguage": {"sEmptyTable": "You do not have permission to view anyone else's bullets"},
       "bJQueryUI": true,	
	   "bAutoWidth": false,
	   "bScrollInfinite": true,
	   "sScrollY": 100
	});
	$("div.view").html('<button id = "vieweeViewBtn" name = "vieweeViewBtn" class = "button" style="position:absolute; left:5px; top:3px;" disabled>View</button>'); //this puts a button in the add div setup in the sDom attribute of the datatable
	$("div.delete").html('<button id = "vieweeDeleteBtn" name = "vieweeDeleteBtn" class = "button" style="position:absolute; left:75px; top:3px;" disabled >Delete</button>'); //this puts a button in the edit div setup in the sDom attribute of the datatable

		
	//click events for row selection for all tables
	$('#notificationTableDiv').on('click', 'tr', function(e){
		$(this).toggleClass('row_selected');
		//toggle buttons ---
		var pendingSelectedRows = getSelectedRowCount($('#notificationTable').dataTable());
		if(pendingSelectedRows==0){
			$('#notificationDeleteBtn').attr("disabled", true);
		}else{
			$('#notificationDeleteBtn').attr("disabled", false);
		}
	});
	
	
	$('#pendingTableDiv').on('click', 'tr', function(e){
		$(this).toggleClass('row_selected');
		//toggle buttons ---
		var pendingSelectedRows = getSelectedRowCount($('#pendingTable').dataTable());
		if(pendingSelectedRows==0){
			$('#pendingApproveBtn').attr("disabled", true);
			$('#pendingDenyBtn').attr("disabled", true);
		}else{
			$('#pendingApproveBtn').attr("disabled", false);
			$('#pendingDenyBtn').attr("disabled", false);
		}
	});
	

	$('#viewerTableDiv').on('click', 'tr', function(e){
        $(this).toggleClass('row_selected');
        //toggle buttons ---
		var pendingSelectedRows = getSelectedRowCount($('#viewerTable').dataTable());
		if(pendingSelectedRows==0){
			$('#viewerDeleteBtn').attr("disabled", true);
		}else{
			$('#viewerDeleteBtn').attr("disabled", false);
		}
    });
	
	
	$('#vieweeTableDiv').on('click', 'tr', function(e){
        $(this).toggleClass('row_selected');
		//toggle buttons ---
		var pendingSelectedRows = getSelectedRowCount($('#vieweeTable').dataTable());
		if(pendingSelectedRows==0){
			$('#vieweeViewBtn').attr("disabled", true);
			$('#vieweeDeleteBtn').attr("disabled", true);
		}else if(pendingSelectedRows==1){
			$('#vieweeViewBtn').attr("disabled", false);
			$('#vieweeDeleteBtn').attr("disabled", false);
		}else{
			$('#vieweeViewBtn').attr("disabled", true);
			$('#vieweeDeleteBtn').attr("disabled", false);
		}
    });	
	
	
	//click events for buttons

	$('#notificationDeleteBtn').click( function() {
		$('#notificationDeleteBtn').attr("disabled", true);
		deleteNotificationsAjax(getNotificationIds());
		refreshNotifications();
		reDraw('#notificationTable');
	});
	$('#pendingApproveBtn').click( function() {
		$('#pendingApproveBtn').attr("disabled", true);
		$('#pendingDenyBtn').attr("disabled", true);
		approveRequestAjax(getPendingRowIds());
		refreshRequest();
		refreshViewer();
		refreshNotifications();
		reDraw('#notificationTable');
		reDraw('#pendingTable');
		reDraw('#viewerTable');
	});
	$('#pendingDenyBtn').click( function() {		
		$('#pendingApproveBtn').attr("disabled", true);
		$('#pendingDenyBtn').attr("disabled", true);
		var rows = getPendingRowIds();
		deleteViewerRelationshipAjax(rows);
		refreshRequest();
		refreshNotifications();
		reDraw('#notificationTable');
		reDraw('#pendingTable');
	});
	$('#viewerDeleteBtn').click( function() {
		$('#viewerDeleteBtn').attr("disabled", true);
		deleteViewerRelationshipAjax(getRowIds('#viewerTable'));
		refreshViewer();	
		refreshNotifications();
		reDraw('#notificationTable');
		reDraw('#viewerTable');
	});
	$('#vieweeViewBtn').click( function() {		
		var userID = getRowIds('#vieweeTable');
		document.getElementById('userID').value = userID;
		document.getElementById('redirectBulletsForm').submit();
	});
	$('#vieweeDeleteBtn').click( function() {
		$('#vieweeDeleteBtn').attr("disabled", true);
		$('#vieweeViewBtn').attr("disabled", true);
		deleteVieweeRelationshipAjax(getRowIds('#vieweeTable'));
		refreshViewee();		
		refreshNotifications();
		reDraw('#notificationTable');
		reDraw('#vieweeTable');
	});	
	getNotificationAjax();
	getViewerRequestsAjax();
	getViewerAjax();
	getVieweeAjax();
});

function refreshNotifications(){
	deleteAllRows('#notificationTable');
	getNotificationAjax();
}

function refreshRequest(){
	deleteAllRows('#pendingTable');
	getViewerRequestsAjax();	
}

function refreshViewer(){
	deleteAllRows('#viewerTable');
	getViewerAjax();
}

function refreshViewee(){
	deleteAllRows('#vieweeTable');
	getVieweeAjax();
}





//Ajax calls

function getNotificationAjax(){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/GetNotificationAjax",
		dataType: 'json',
		success: function(data) {
//			var notificationOne = new Notification(1, new Date(), "Hello there!");
//			addNotificationToTable('#notificationTable',notificationOne);
			if(!$.isEmptyObject(data)){
				$.each(data, function(index, value) {
//					alert(value.notificationID);
//					alert(value.date);
//					alert(value.message);
					var notification = new Notification(value.notificationID, value.date, value.message);
					addNotificationToTable('#notificationTable', notification);
				});				
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}


function getViewerRequestsAjax(){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/GetViewerRequestAjax",
		dataType: 'json',
		success: function(data) {
			if(!$.isEmptyObject(data)){
				$.each(data, function(index, value) {
					var person = new Person(value.userID, value.rank, value.rankID, value.firstName, value.lastName, value.base, value.email);
					addPersonToTableLong('#pendingTable', person);
				});
			}else{
				$('#pendingTable').dataTable().fnDestroy;
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}


function getViewerAjax(){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/GetViewerAjax",
		dataType: 'json',
		success: function(data) {
			if(!$.isEmptyObject(data)){
				$.each(data, function(index, value) {
					var person = new Person(value.userID, value.rank, value.rankID, value.firstName, value.lastName, value.base, value.email);
					addPersonToTable('#viewerTable',person);
				});	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}

function getVieweeAjax(){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',
		url: "../restricted/GetVieweeAjax",
		dataType: 'json',
		success: function(data) {
			if(!$.isEmptyObject(data)){
				$.each(data, function(index, value) {
					var person = new Person(value.userID, value.rank, value.rankID, value.firstName, value.lastName, value.base, value.email);
					addPersonToTable('#vieweeTable', person);
				});	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}


function deleteNotificationsAjax(ids){
	showLoading();
	$.ajax({
		cache: false,
		async: false,		
		type: 'POST',
		url: "../restricted/DeleteNotificationAjax",
		data: {"viewerIds":ids},
		success: function() {
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}


function approveRequestAjax(ids){
	showLoading();
	$.ajax({
		cache: false,
		async: false,		
		type: 'GET',
		url: "../restricted/ApproveRequestAjax",
		data: {"viewerIds":ids},
		success: function() {
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}

function deleteViewerRelationshipAjax(ids){
	showLoading();
	$.ajax({
		cache: false,
		type: 'GET',		
		async: false,
		url: "../restricted/DeleteViewerRelationshipAjax",
		data: {"viewerIds":ids},
		success: function() {
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}

function deleteVieweeRelationshipAjax(ids){
	showLoading();
	$.ajax({
		cache: false,
		type: 'POST',
		async: false,
		url: "../restricted/DeleteVieweeRelationshipAjax",
		data: {"vieweeIds":ids},
		success: function() {
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			showError(jqXHR+" - "+textStatus+" - "+errorThrown+".");
		},
		complete: function(){
			closeLoading();
		}
	});	 
}

function getNotificationIds(){
	var bulletIds = new Array();
	var anSelected = fnGetSelected($('#notificationTable').dataTable());
	for(var i=0;i<anSelected.length;i++){
		var row = anSelected[i];
		bulletIds[i]=$('#notificationTable').dataTable().fnGetData(row)[2];//this gets the id column
		//$('#pendingTable').dataTable().fnDeleteRow(anSelected[i]);
	}
	var ids = bulletIds.toString();
	return ids;
}

function getPendingRowIds(){
	var bulletIds = new Array();
	var anSelected = fnGetSelected($('#pendingTable').dataTable());
	for(var i=0;i<anSelected.length;i++){
		var row = anSelected[i];
		bulletIds[i]=$('#pendingTable').dataTable().fnGetData(row)[5];//this gets the id column
		//$('#pendingTable').dataTable().fnDeleteRow(anSelected[i]);
	}
	var ids = bulletIds.toString();
	return ids;
}

function getRowIds(table){
	var bulletIds = new Array();
	var anSelected = fnGetSelected($(table).dataTable());
	for(var i=0;i<anSelected.length;i++){
		var row = anSelected[i];
		bulletIds[i]=$(table).dataTable().fnGetData(row)[3];//this gets the id column
		//$(table).dataTable().fnDeleteRow(anSelected[i]);
	}
	var ids = bulletIds.toString();
	return ids;
}

//convenience method for all tables to get the selected rows
function fnGetSelected(localTable){
    return localTable.$('tr.row_selected');
}	

function addNotificationToTable(table, notification){
//	alert("date = "+notification.date+" ...message = "+notification.message+ " ...id = "+notification.id);
	$(table).dataTable().fnAddData([notification.date, notification.message, notification.id]);	
}

function addPersonToTableLong(table, person){
	$(table).dataTable().fnAddData([person.rank, person.firstnm, person.lastnm, person.base, person.email, person.id, person.rankID]);	
}

function addPersonToTable(table, person){
	$(table).dataTable().fnAddData([person.rank, person.firstnm, person.lastnm, person.id, person.rankID]);	
}

function getSelectedRowCount(oTableLocal){
	return oTableLocal.$('tr.row_selected').length;
}

function deleteAllRows(table){
	var oSettings = $(table).dataTable().fnSettings();
	var iTotalRecords = oSettings.fnRecordsTotal();
	for (var i=0;i<=iTotalRecords;i++) {
		$(table).dataTable().fnDeleteRow(0,null,true);
	}
}

function reDraw(table){
	$(table).dataTable().fnDraw();
}

function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}