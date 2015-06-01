var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
console.log('Rendering profile page...');

var currentUser = Parse.User.current();
  if (currentUser) {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
    var _user = {
       name : currentUser.get("name"),
       email : currentUser.get("email"),
       phone : currentUser.get("phone"),
       address : currentUser.get("address"),
             }
      res.render('profile', {user : _user});

   } else {
    res.render('login', {title: 'Login', message: Response.InvalidLogin});
  }   
});


router.post('/update', function(req, res, next) {
  console.log("********************* SAVE USER CALLED ***********************");
  console.log("Username: "+  req.body.txtName + ", phone"+ req.body.txtphone + " Email :"+ req.body.txtemail);

  var currentUser = Parse.User.current(); 
  if (currentUser) 
  {
    console.log("CURRENT USER : "+ JSON.stringify(currentUser));
         var _user = {
                      name : currentUser.get("name"),
                     }

          var data = {
            name:req.body.txtName,
            //username: req.body.username,
            email:req.body.txtemail,
            phone:req.body.txtphone,
            address:req.body.txtaddress,
            //userType:req.body.userType,
            //imei:req.body.imei,
            //location:req.body.location
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
            console.log("cloud call error");
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

router.post('/uploadImage',function(req,res){
  console.log("Upload image called");
  console.log("File : "+req.body.myPhoto);
  
  var file = req.body.myPhoto;
  var name =file.name;
  console.log("line 76");
  var parseFile = new Parse.File(name,{base64:file});

  parseFile.save().then
         (
           function() 
           {
            Parse.Cloud.useMasterKey();
            var query = new Parse.Query(Parse.User);
            query.equalTo("objectId","S8qZJM7XIA");
            query.find({
            success: function(results) 
            {
            results.set("profileImg",parseFile);
            results.save(null, {
            success: function(results) {
              params.success(Response.UpdateSuccess);
            },
            error: function(gameScore, error) {
            console.log('Failed to create new object, with error code: ' + error.description);
          }
     });
  }

})
               
           }, 
           function(error) 
           {
             console.log("error"+error);
           }
         );
    
    

});



module.exports = router;

