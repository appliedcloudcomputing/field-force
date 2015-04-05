var express = require('express');
var router = express.Router();



router.get('/', function(req, res, next) {
console.log('Rendering profile page...');

var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
       email : currentUser.get("email"),
       phone : currentUser.get("phone"),
       address : currentUser.get("address"),
    }
      res.render('profile', {user : _user});

  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});

module.exports = router;

