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
router.get('/userList', function(req, res, next) 
{
 var userList = [];
  
  var userQuery = new Parse.Query(Parse.User);
  userQuery.find({
    success: function(users) 
    {
      console.log('USER SUCCESS');
      if(users) {
        users.forEach(function(user) 
        {
          var _user = {

            email: user.getUsername(),
            username:user.get('username'),
            password :user.get('password'),
            address :user.get('address'),
            phone :user.get('phone')
                      }
          userList.push(_user);
        });
        res.render('userList', {userList: userList});
       } 

       else 
       {
        console.log('NO USERS PRESENT');
       }
    },
    error: function(error) {
      console.log('ERROR FINDING USERS: ' + error.message);
    }
  });
});
module.exports = router;


