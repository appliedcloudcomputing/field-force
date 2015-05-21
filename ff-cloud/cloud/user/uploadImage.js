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
	if(!params.email || !params.opwd || !params.npwd) 
	{
		params.error(Response.ParametersEmpty);
	} 
	else {

        var user = new User();
		var query = new Parse.Query(Parse.User);
		query.equalTo("objectId",params.id);
        query.find({
        success: function(results) 
         {
         	user.set("profileImg",params.file);
         	user.save(null, {
						success: function(user) {
							params.success(Response.UpdateSuccess);
						},
						error: function(user, error) {
							console.log("ERROR IN UPDATING USER : " + error.message);
							params.error(Response.InternalServerError);
						}
			 		});
				
          },
         error: function(error) 
          {
          params.error(Response.InternalServerError);
          }
        });

	}

};