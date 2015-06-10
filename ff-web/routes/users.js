var express = require('express');
var router = express.Router();


router.get('/save', function(req, res, next) 
{
  var currentUser = Parse.User.current();
  if (currentUser) {
     var data = {};
     if(req.query.id != null){
        // UPDATE USER -- AUTO FILL ALL TEXTBOX 

        // QUERY FROM PARSE WITH REQUEST OBJECT ID
        var query = new Parse.Query(Parse.User);
        query.equalTo("objectId", req.query.id);
        query.first({
          success: function(_user) {
            // USER FOUND
             console.log("User FOUND : "+ JSON.stringify(_user));
              var _u = {
              id: _user.id,
              name: _user.get("name"),
              username: _user.get("username"),
              email: _user.get("email"),
              phone: _user.get("phone"),
              address: _user.get("address"),
              userType: _user.get("userType"),
              imei: _user.get("imei"),
              location: _user.get("location")
            };
            console.log("USER DETAILS : "+ JSON.stringify(_u));
            res.render('user', {user : _u});
          },
          error: function(object, error) {
            // ERROR OCCUR
             console.error(error);
             res.end("UNABLE TO FIND USER");
          }
        });
     } else {
            // NEW USER 
            res.render('user',{user : data});
     }
  } else {
      // show the signup or login page
      res.redirect('/login/');
    }
  });





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
            console.log("Cloud call save user success");

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
      res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }  
});

/*userListing*/

router.get('/', function(req, res, next) 
{
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
                id: user.id,
                email: user.get('email'),
                username:user.get('username'),
                phone :user.get('phone'),
                id:user.id,
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
       email : currentUser.get("email"),
       phone : currentUser.get("phone"),
       address : currentUser.get("address"),
    }
      res.render('userDetails', {user : _user});

  } else {
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});
/* ***********************************************************************/


router.get('/fetchDetails', function(req, res, next) 
 {
  console.log("username "+req.query.id);
     
  var currentUser = Parse.User.current();
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
     var userList = [];      
      var userQuery = new Parse.Query(Parse.User);
      userQuery.equalTo("objectId",req.query.id);
      userQuery.first({
        success: function(users) 
        {
          console.log('USER SUCCESS');       
              var _user = {
                email: users.get('email'),
                name:users.get('name'),
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
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});
/*------------to delete user-------------*/

router.get('/delete',function(req,res){
  var query = new Parse.Query(Parse.User);
  Parse.Cloud.useMasterKey();
  query.equalTo("objectId", req.query.id);
  query.first({
    success:function(obj){
            obj.destroy({});      
      var response = {
                message: "User deleted successfully !!!",
                status: 200
                      }
            
              res.end(JSON.stringify(response));
                         },
                            error:function(error){
                           var response = {
                message: error.message,
                status: error.code
              }
              res.end(JSON.stringify(response));
            }
          });
       });

/*------------Log out -------------*/

router.get('/logout', function(req, res, next) {
  var currentUser = Parse.User.current();

  if (currentUser) {
    console.log('Inside Log out');
    Parse.User.logOut();
    res.render('login',{title:'logOut'});
  } else {
    res.render('login', { title: 'Login' });
  }
});


module.exports = router;


