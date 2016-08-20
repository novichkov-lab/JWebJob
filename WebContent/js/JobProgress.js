/*
* Javascript for JobProgress.jsp
*/

function updateStatus(){
	$.ajax({url:"JobProgressHandler", 
	success: function(data){
		console.log("JobProgressandler ajax response recieved");
		console.log("data" + data)
		checkCompletion(data);
		updateHtmlStatus(data);
	},
	complete: function(){
		setTimeout(updateStatus, 3000);
	}});
}

function checkCompletion(data){ 
	console.log("Check on completion " + data[0]);
	if(data[0].jobStatus == 1){
		window.location.replace("JobResultVC?Job=" + data[0].jobId);
	};
	
}

$(updateStatus);

function updateHtmlStatus(data){
	console.log("in updateHtmlStatus");
	console.log("access of subtasks: " + data[0].subtasks);
	console.log("size of subtasks object is: " + data[0].subtasks.length);
	for(i = 0; i<data[0].subtasks.length; i++){
		searchId = data[0].subtasks[i].taskId;
		console.log("taskid: " + searchId);
		status = data[0].subtasks[i].taskStatus;
		console.log("taskStatus: " + status)
		$("#" + searchId + "_Icon").html(data[0].subtasks[i].taskStatus); //TEMPORARY
		if(status == 0){
			$("#" + searchId + "_Icon").html('<img src="images/ajax-loader.gif" style="width:width;height:height;">');
		}else if(status == 1){
			$("#" + searchId + "_Icon").html('<img src="images/check-2.png" style="width:width;height:height;">');
		}else if(status == -1){
			$("#" + searchId + "_Icon").html('<img src="images/cross.png" style="width:width;height:height;">');
		}
	}
}