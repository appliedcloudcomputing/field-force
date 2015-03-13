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
  var user = new Parse.User();
  user.set("username", req.body.username);
  user.set("password",  req.body.password);
  user.set("email",  req.body.email);
//  user.set("phone", "req.body.phone");
  user.signUp(null, {
    success: function(user) {
      console.log("****************User SAVED SUCESSFULLY******************");
      res.render('user', {error: ""});
    },
    error: function(user, error) {
      //res.render('user', {error: error.message});
      console.log("*****************ERROR ********************");
      res.render('user', {error: "Error"});
    }
  }); 
});

/*userListing*/
router.get('/userlist', function(req, res, next) {
  console.log("userList integration successssssss");
res.render('userList', {error: ""});
  });
module.exports = router;


