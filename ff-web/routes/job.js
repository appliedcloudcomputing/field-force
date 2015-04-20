
var express = require('express');
var router = express.Router();
router.get('/save', function(req, res, next) 
{
  var currentUser = Parse.User.current();
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _u = {
       name : currentUser.get("name"),
    }
       //GET CUSTOMER
     var customerList = [];
      
      var userQuery = new Parse.Query(Parse.User);
      userQuery.equalTo("userType","Customer");
      userQuery.find({
        success: function(customer){
          console.log('customer SUCCESS');
          if(customer) {
            customer.forEach(function(customer)
            {
             var _customer = {               
                           name:customer.get('name'),
                           id:customer.id
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
      
      var query = new Parse.Query(Parse.User);
      query.equalTo("userType", "Employee");
      query.find({
        success: function(employee){
          console.log('employee SUCCESS');
          if(employee) {
            employee.forEach(function(emp) //name of html page
            {
              var _employee = {
                name:emp.get('name'),
                id:emp.id
              }
              userList.push(_employee);
            });
            console.log("USER LIST :"+JSON.stringify(userList));
            console.log("CUSTOMER LIST :"+JSON.stringify(customerList));
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

/****************************************** save job******************************/

router.post('/save', function(req, res, next) 
{
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

console.log("CURRENT USER : "+ JSON.stringify(currentUser));
  Parse.Cloud.run('saveJob', data, {
        success: function(message) {

        console.log("cloud call save user success");

        var response = {
          message: message,
          status: 200
        }
       res.render('job', {user : _user});
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

/****************************************** save job******************************/
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
/****************************************** Job List ******************************/

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
      userQuery.include("currentlyAssignedTo");
      userQuery.include("customer");
      userQuery.find({
        success: function(job) 
        {
          console.log('job SUCCESS');
          if(job) {
            job.forEach(function(job) 
            {

              var _job = {

                title: job.get('title'),                
                customer:job.get('customer').get("username"),
                employee:job.get('currentlyAssignedTo').get("username"),
                status:job.get('currentStage')
                          }
              jobList.push(_job);
            });
            res.render('jobList', {jobList: jobList, user : _user});
            console.log("job LIST :"+JSON.stringify(jobList));
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