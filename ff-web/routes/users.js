var express = require('express');
var router = express.Router();

/* GET users listing. */
<<<<<<< HEAD

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




=======
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
>>>>>>> 91708a61b73fdca23c19ab7dde42b68a2ddb622d
module.exports = router;


