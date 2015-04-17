
var express = require('express');
var router = express.Router();

/*router.get('/', function(req, res, next) {
   res.render('job', {error: ""});
});
  */ 
router.get('/save', function(req, res, next) 
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
     
    //GET CUSTOMER
     var customerList = [];
      
      var userQuery = new Parse.Query(Parse.User);
      userQuery.equalTo("userType", "Customer");
      userQuery.find({
        success: function(customer){
          console.log('customer SUCCESS');
          if(customer) {
            customer.forEach(function(customer) //name of html page
            {
              console.log("ID: "+customer.id);
              var _customer = {
               
                name:customer.get('name')
                          }
              customerList.push(_customer);
            });
          }
        },
        error: function(error) {
          console.log('ERROR FINDING USERS: ' + error.message);
        }      
      });
        
      // EMPLOYEE
      var userList = [];
      
      var _userQuery = new Parse.Query(Parse.User);
      _userQuery.equalTo("userType", "Employee");
      _userQuery.find({
        success: function(employee){
          console.log('employee SUCCESS');
          if(employee) {
            employee.forEach(function(emp) //name of html page
            {
              var _employee = {
                name:emp.get('name')
              }
              userList.push(_employee);
            });
            res.render('job', { userList: userList,customerList :customerList, user : _u});
          }
        },
        error: function(error) {
          console.log('ERROR FINDING USERS: ' + error.message);
        }      
      });
        



 
}

  else 
  {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
}); 

	

/* savin job*/

router.post('/save', function(req, res, next) {
  console.log("saving job...............");
  

  var currentUser = Parse.User.current();
 
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
             }


   var data = {
    title:req.body.title,
    customer: req.body.customer,
    currentlyAssignedTo:req.body.currentlyAssignedTo,
    assigned:req.body.assigned,
    description:req.body.description,
    completed:req.body.completed,
    currentStage:req.body.currentStage


  };

    Parse.Cloud.run('saveJob', data, {
        success: function(message) {

        console.log("cloud call save user success");

        var response = {
          message: message,
          status: 200
        }
        res.end(JSON.stringify(response));  
       //res.render('job', {user : _user});
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


router.get('/jobDetail', function(req, res) {
  console.log('Rendering job save page...');

var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
    }
      res.render('jobDetails', {user : _user});

  } else {
      // show the signup or login page
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
  
});

router.get('/jobList', function(req, res) {
  console.log('Rendering job save page...');

var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
    }
      var jobList = [];
      
      var job = Parse.Object.extend("Job");
      var userQuery = new Parse.Query(job);
      userQuery.find({
        success: function(job) 
        {
          console.log('job SUCCESS');
          if(job) {
            job.forEach(function(job) 
            {
              var _job = {

                title: job.get('title'),
                customer:job.get('customer'),
                employee:job.get('currentlyAssignedTo'),
                status:job.get('currentStage')
                          }
              jobList.push(_job);
            });
            res.render('jobList', {jobList: jobList, user : _user});
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
    res.render('joblist', {title: 'Login', message: Response.InvalidLogin});
  }   
});

module.exports = router;