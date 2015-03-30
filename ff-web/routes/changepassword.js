var express = require('express');
var router = express.Router();
var User = Parse.Object.extend("User");


router.get('/', function(req, res, next) {
  res.render('changepassword', {error: ""});
}); 

router.post('/save', function(req, res) {
  console.log("Called changepassword.js");
	console.log("oldpassword :"+ req.body.oldPassword);
	console.log("newpassword :"+ req.body.newPassword);


  var data ={
    username: req.body.username,
    //oldPassword: req.body.oldPassword,
    newPassword: req.body.newPassword
  }	;

   Parse.Cloud.run('changePassword', data, {
        success: function(message) {

        console.log("cloud call save changepassword success");

        var response = {
          message: message,
          status: 200
        }
        res.end(JSON.stringify(response));
        res.render('changepassword',{error:""})
      },
      error: function(error) {
        var response = {
          message: error.message,

          status: error.code
        }
        res.end(JSON.stringify(response));
      }
    });
});
module.exports = router;
