var express = require('express');
var router = express.Router();


router.get('/', function(req, res, next) {
  			console.log('Rendering dashboard page...');
			var currentUser = Parse.User.current();
			if (currentUser) {
				console.log("CURRENT USER : "+ JSON.stringify(currentUser));
				var _user = {
					 name : currentUser.get("name"),
				}
			    res.render('dashboard', {user : _user});

			} else {
				res.render('login', {title: 'Login', message: Response.InvalidLogin});
			} 	
});

module.exports = router;
