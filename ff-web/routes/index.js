var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
 	console.log('Rendering dashboad page...');
	var currentUser = req.session.user ? JSON.parse(req.session.user) : null;

	if (currentUser) {	
		res.redirect('/users/');
	} else {
		res.render('login', { title: 'Login' });
	}
});

module.exports = router;
