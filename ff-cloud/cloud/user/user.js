var User = Parse.Object.extend("User");

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
	if(!params || !params.username || !params.name || !params.email || !params.phone || !params.address || !params.userType) {
		params.error(Response.ParametersEmpty);
	} else {

		//SAVING USER
		var user = new User();
		user.set("name", params.name);
		user.set("username", params.username);
		user.set("email", params.email);
		user.set("phone", params.phone);
		user.set("address", params.address);
		user.set("userType", params.userType);
		user.set("imei", params.imei);
		user.set("currentLocation", params.currentLocation);
		
		user.save(null, {
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

exports.update = function(params) {
	if(!params || !params.id || !params.name || !params.dob || !params.mobile || !params.telephone || !params.email 
		|| !params.enterprise || !params.address || !params.contactTime || !params.personalNote) {
		params.error(Response.ParametersEmpty);
	} else {
		var currentUser = Parse.User.current();
		if(!currentUser)
			params.error(Response.LoginError);

		var userQuery = new Parse.Query(User);
		userQuery.get(params.id, {
			success: function(user) {
				if(user) {
					user.set("name", params.name);
					user.set("username", params.username);
					user.set("email", params.email);
					user.set("phone", params.phone);
					user.set("address", params.address);
					user.set("userType", params.userType);
					user.set("imei", params.imei);
					user.set("currentLocation", params.currentLocation);
					user.set("lastUpdatedBy", currentUser);
					user.save(null, {
						success: function(user) {
							params.success(Response.UpdateSuccess);
						},
						error: function(user, error) {
							console.log("ERROR IN UPDATING USER : " + error.message);
							params.error(Response.InternalServerError);
						}
			 		});
				} else {
					params.error(Response.NotFound);
				}
			},
			error: function(error) {
				params.error(Response.InternalServerError);
			}
		});
	}
};