var Job = Parse.Object.extend("Job");

var Response = {
	ParametersEmpty: "Please provide complete details",
	InternalServerError: "Oops! Some error occurred! Please try again",
	NotFound: "Requested resource not found!",
	LoginError: "Some error in current session!",
	SaveSuccess: "Resource saved successfully!",
	UpdateSuccess: "Resource updated successfully!",
	DeleteSuccess: "Resource deleted successfully!"
};


exports.save = function(params) {
	if(!params || !params.title || !params.currentlyAssignedTo || !params.customer || !params.assigned || !params.description || !params.completed || !params.currentStage) 
	{
		params.error(Response.ParametersEmpty);
	} 
	else {

	 var job = new Job();
    
    var _customer = {__type: "Pointer", className:"_User", 'objectId': params.customer};// pointer variable
    var _currentlyAssignedTo = {__type: "Pointer", className: "_User", 'objectId':params.currentlyAssignedTo};


		job.set("title", params.title);
		job.set("customer", _customer);
		job.set("currentlyAssignedTo", _currentlyAssignedTo);
		job.set("assigned", params.assigned);
		job.set("description", params.description);
		job.set("completed", params.completed);
		job.set("currentStage", params.currentStage);
		
		job.save(null, {
			success: function(user) {
				params.success(Response.SaveSuccess);
			},
			error: function(user, error) {
				console.log("ERROR IN SAVING USER : " + error.message);
				params.error(Response.InternalServerError);
			}
 		});
	}
};
