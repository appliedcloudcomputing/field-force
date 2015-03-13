var express = require('express');
var router = express.Router();

/* GET users listing. */
/*function for user to appear on userlist   */
router.get('/save', function(req, res, next) {
   res.render('user', {error: ""});
});

router.post('/save', function(req, res, next) {
  console.log("********************* SAVE USER CALLED ***********************");
  console.log("Username: "+  req.body.username + ", Password"+ req.body.password + " Email :"+ req.body.email);

  var data = {
    name:req.body.name,
    username: req.body.username,
    password:req.body.password,
    email:req.body.email,
    phone:req.body.phone,
    address:req.body.address,
    userType:req.body.userType


  };

    Parse.Cloud.run('saveUser', data, {
        success: function(message) {

        console.log("cloud call save user success");
        var response = {
          message: message,
          status: 200
        }
        res.end(JSON.stringify(response));
      },
      error: function(error) {
        var response = {
          message: error.message,
         }
        res.end(JSON.stringify(response));
      }
    });
});



/*userListing*/
router.get('/userlist', function(req, res, next) {
  console.log("userList integration successssssss");
res.render('userList', {error: ""});
  });
module.exports = router;


