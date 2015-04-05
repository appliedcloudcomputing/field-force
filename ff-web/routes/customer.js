var express = require('express');
var router = express.Router();


  router.get('/', function(req, res, next) {
  var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
    }
      res.render('customer', {user : _user});

  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});


 router.post('/save', function(req, res, next) {
  console.log("********************* SAVE CUSTOMER CALLED ***********************");
  
  var data = {
    name:req.body.name,
    email:req.body.email,
    password:req.body.password,
    phone:req.body.phone,
    address:req.body.address
   

  };

    Parse.Cloud.run('saveCustomer', data, {
        success: function(message) {

        console.log("cloud call save customer success");

        var response = {
          message: message,
          status: 200
        }
        res.render('customer', {msg: "customer Data Save successfully"});  //
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

//customerListing

router.get('/', function(req, res, next) 
{
 // start.. checks if user is registered in our database
  var currentUser = Parse.User.current();

  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _u = {
       name : currentUser.get("name"),
    }
    // end.. checks if user is registered in our databases
     var customerList = [];
      
      var userQuery = new Parse.Query(Parse.User);
      userQuery.find({
        success: function(customer) 
        {
          console.log('USER SUCCESS');
          if(customer) {
            customer.forEach(function(customer) //name of html page
            {
              var _customer = {
               
                username:customer.get('name'),
                address :customer.get('email'),
                phone :customer.get('phone'),
                usertype:customer.get('address')
                          }
              customerList.push(_customer);
            });
            res.render('customerList', {customerList: customerList, user : _u});
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
module.exports = router;

