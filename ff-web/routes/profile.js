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


 router.post('/update', function(req, res, next) {
  console.log("********************* SAVE USER CALLED ***********************");
  console.log("Username: "+  req.body.txtName + ", phone"+ req.body.txtphone + " Email :"+ req.body.txtemail);

var currentUser = Parse.User.current();
 
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
             }


  var data = {
    name:req.body.txtName,
    //username: req.body.username,
    //password:req.body.password,
    email:req.body.txtemail,
    phone:req.body.txtphone,
    address:req.body.txtaddress,
    //userType:req.body.userType,
    //imei:req.body.imei,
    //location:req.body.location


  };

    Parse.Cloud.run('saveUser', data, {
        success: function(message) {

        console.log("cloud call save user success");

        var response = {
          message: message,
          status: 200
        }
         res.render('user',{user : _user});
      },
      error: function(error) {
        var response = {
          message: error.message,

          status: error.code
        }
        res.end(JSON.stringify(response));
      }
    });


    }else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }  
});




module.exports = router;

