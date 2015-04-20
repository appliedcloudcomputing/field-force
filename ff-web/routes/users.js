var express = require('express');
var router = express.Router();

/****************************************************************************/
router.get('/index', function(req, res, next) {
  res.render('index', {error: ""});
});

/****************************************************************************/
router.get('/signup', function(req, res, next) {
  res.render('signup', {error: ""});
});
/****************************************************************************/

 router.get('/save', function(req, res, next) 
 {
  var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
    }
      res.render('user', {user : _user});

  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});
/****************************************************************************/  
 router.post('/save', function(req, res, next) {
  console.log("********************* SAVE USER CALLED ***********************");
  console.log("Username: "+  req.body.username + ", Password"+ req.body.password + " Email :"+ req.body.email);

var currentUser = Parse.User.current();
 
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
             }


  var data = {
    name:req.body.name,
    username: req.body.username,
    password:req.body.password,
    email:req.body.email,
    phone:req.body.phone,
    address:req.body.address,
    userType:req.body.userType,
    imei:req.body.imei,
    location:req.body.location


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

/****************************************************************************/

/*userListing*/

router.get('/', function(req, res, next) 
{
 // checks if user is registered in our databases
  var currentUser = Parse.User.current();

  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _u = {
       name : currentUser.get("name"),
    }
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

                email: user.get('email'),
                username:user.get('username'),
                phone :user.get('phone'),
                userType:user.get('userType')
                          }
              userList.push(_user);
            });
            res.render('userList', {userList: userList, user : _u});
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

  }else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
});

/****************************************************************************/

router.get('/userDetails', function(req, res, next) 
 {
  var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
              name : currentUser.get("name"),
       email : currentUser.get("email"),
       phone : currentUser.get("phone"),
       address : currentUser.get("address"),
    }
      res.render('userDetails', {user : _user});

  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});
/* ************........................................................................................*/

router.get('/fetchDetails', function(req, res, next) 
 {
  console.log("username "+req.query.email);
     
  var currentUser = Parse.User.current();
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
     var userList = [];      
      var userQuery = new Parse.Query(Parse.User);
      userQuery.equalTo("email",req.query.email);
      userQuery.first({
        success: function(users) 
        {
          console.log('USER SUCCESS');       
              var _user = {
                email: users.get('email'),
                username:users.get('username'),
                phone :users.get('phone'),
                address: users.get('address'),
                userType:users.get('userType')
                          }             
            res.render('userDetails', {user: _user});                    
        },
        error: function(error) {
          console.log('ERROR FINDING USERS: ' + error.message);
        }
      });
  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});

module.exports = router;


