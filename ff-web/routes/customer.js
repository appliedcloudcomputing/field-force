var express = require('express');
var router = express.Router();


  router.get('/save', function(req, res, next) {
  res.render('customer', {error: ""});
});
 router.get('/', function(req, res, next) {
  res.render('customerList', {error: ""});
});
 
 router.post('/save', function(req, res, next) {
  console.log("********************* SAVE CUSTOMER CALLED ***********************");
  
  var data = {
    fullname:req.body.name,
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
         res.render('customer', {msg: "customer Data Save successfully"});
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
 var customerList = [];
  
var userQuery = new Parse.Query(Parse.User);
  userQuery.find({
    success: function(users) 
    {
      console.log('USER SUCCESS');
      if(customer) {
        users.forEach(function(user) 
        {
          var _customer = {
            email: user.get('email'),
            name:user.get('name'),
            address :user.get('address'),
            phone :user.get('phone')
            
        }
          customerList.push(_customer);
        });
        res.render('customerList', {customerList: customerList});
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


