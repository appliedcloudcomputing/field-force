var express = require('express');
var router = express.Router();

router.get('/', function(req, res) {
	console.log("Rendering expenses save page...");
	
	var currentUser = Parse.User.current();
	if (currentUser) {
		console.log("CURRENT USER : "+ JSON.stringify(currentUser));
		var _user = {
			 name : currentUser.get("name"),
		}
	    res.render('expenses', {user : _user});

	} else {
	    // show the signup or login page
		res.render('login', {title: 'Login', message: Response.InvalidLogin});
	} 	
	
});
router.post('/save', function(req, res, next) {
  console.log("saving expenses...............");

  var currentUser = Parse.User.current();
 
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
             }


   var data = {
   
    expensestype:req.body.expensesType,
    expensesamount:req.body.expensesAmt,
    expensesapproved:req.body.expensesApproved



  };

    Parse.Cloud.run('saveExpenses', data, {
        success: function(message) {

        console.log("cloud call save expenses success");

        var response = {
          message: message,
          status: 200
        }
       res.render('expenses', {user : _user});
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

/*dropdown listing*/


router.get('/save', function(req, res, next) 
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
              console.log("ID: "+user.id);
              var _user = {

                email: user.get('email'),

                name:user.get('name'),
                id :user.id
              }

              userList.push(_user);
            });
            res.render('expenses', {userList: userList, user : _u});
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