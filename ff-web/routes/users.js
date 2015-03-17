var express = require('express');
var router = express.Router();

/* GET users listing. */

router.get('/index', function(req, res, next) {
  res.render('index', {error: ""});
});
 router.get('/userList', function(req, res) {
  console.log('Rendering user list page...');
  res.render('userList',{error: ""});
});

 router.get('/save', function(req, res, next) {
   res.render('user', {error: ""});
});


router.post('/save', function(req, res, next) {
  console.log("Called User Save");
  var data = {
          
          'username':req.body.username,
          'phone':req.body.phone,
          'email': req.body.email,
           'password': req.body.password,
            'address': req.body.address
          
          
        };
    Parse.Cloud.run('saveUser', data, {
      success: function(message) {
        var response = {
          message: message,
          status: 200
        }
        res.end(JSON.stringify(response));
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


