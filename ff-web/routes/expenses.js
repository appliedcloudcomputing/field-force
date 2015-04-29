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

    var Expenses = Parse.Object.extend("Expenses");
    var expenses = new Expenses();
 
    expenses.set("employeeName",req.body.employeeName);
    expenses.set("expensesType",req.body.expensesType);
    expenses.set("expensesAmount",req.body.expensesAmt);
    expenses.set("expensesApproved",req.body.approved);
 
    expenses.save(null, {
      success: function(expenses) {
          res.end("save success");
              },
      error: function(expenses, error) {
              alert('Failed to create new object, with error code: ' + error.message);
            }
        });

  }else {
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }  
});

router.get('/expensesList', function(req, res, next) 
{
  var currentUser = Parse.User.current();

  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _u = {
       name : currentUser.get("name"),
    }
     var userList = [];
      var Expenses = Parse.Object.extend("Expenses");
      var userQuery = new Parse.Query(Expenses);
      userQuery.find({
        success: function(users) 
        {
          console.log('USER SUCCESS');
          if(users) {
            users.forEach(function(user)
            { 
              console.log("ID: "+user.id);
              var _user = {
                expensesName: user.get('employeeName'),
                expensesType:user.get('expensesType'),
                expensesAmt:user.get('expensesAmount'),
                expensesApproved:user.get('expensesApproved')
              }
              userList.push(_user);
            });
            res.render('expensesList', {exList:userList, user : _u});
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

module.exports = router;