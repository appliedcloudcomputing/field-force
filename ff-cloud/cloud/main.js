var user = require('cloud/user/user.js');

// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:
Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world!");
});


var Response = {
	ParametersEmpty: 'Please provide complete details',
	InternalServerError: 'Oops! Some error occurred! Please try again',
	NotFound: 'Requested resource not found!',
	LoginError: 'Some error in current session!',
	SaveSuccess: 'Resource saved successfully!',
	UpdateSuccess: 'Resource updated successfully!',
	DeleteSuccess: 'Resource deleted successfully!'
};

/*-----------------------------------------------USER-----------------------------------------------*/

Parse.Cloud.define('saveUser', function(req, res) {
	if(!req.params.id || req.params.id == 0) {
		user.save({
			username: req.params.username,
			name: req.params.name,
			email: req.params.email,
			phone: req.params.phone,
			address: req.params.address,
			userType: req.params.userType,
			imei: req.params.imei,
			currentLocation: req.params.currentLocation,
			success: function(message) {
				res.success(message);
			},
			error: function(error) {
				res.error(error);
			}
		});
	} else {
		user.update({
			id: req.params.id,
			username: req.params.username,
			name: req.params.name,
			email: req.params.email,
			phone: req.params.phone,
			address: req.params.address,
			userType: req.params.userType,
			imei: req.params.imei,
			currentLocation: req.params.currentLocation,
			success: function(message) {
				res.success(message);
			},
			error: function(error) {
				res.error(error);
			}
		});
	}
});

Parse.Cloud.beforeSave('User', function(req, res) {
	var user = req.object;
	var currentUser = client.get('lastUpdatedBy');
	if(currentUser) {
		var tags;
		var name = user.get('name');
		var userType = user.get('userType');
		var email = user.get('email');
		var phone = user.get('phone');
		tags = '#' + name + '#' + userType + '#' + email + '#' + phone;
		client.set('tags', tags);
		res.success();
	} else {
		res.error(Response.LoginError);
	}
});



