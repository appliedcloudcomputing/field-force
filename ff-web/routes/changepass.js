
var express = require('express');
var router = express.Router();

router.get('/', function(req, res) {
	console.log('Rendering change password..');
	res.render('changepass', {error: ""});

});


module.exports = router;
