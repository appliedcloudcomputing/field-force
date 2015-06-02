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

  var b64str = req.body.myPhoto;       //Casting from image Object TO base64
  var decodedFile = new Buffer(b64str, 'base64');;
  var name ="a.png";
  var parseFile = new Parse.File(name,decodedFile,"image/png");
  console.log("*File:"+decodedFile+"*Name :"+name);
  console.log("File Name :"+parseFile.name());

            Parse.Cloud.useMasterKey();
            var query = new Parse.Query(Parse.User);
            query.equalTo("objectId","S8qZJM7XIA");
            query.first({
              success: function(results) {
                console.log("Inside success");
                results.set("profileImg",parseFile.toString("base64"));
                results.save(null,{
                             success: function(user) {
                             console.log("Saved");
                             },
                             error: function(user, error) {
                             console.log("ERROR IN SAVING USER : " + error.message);
                             
                        }
                 });
              },
              error: function(error) {
              console.log("Error: " + error.code + " " + error.message);
        }
    });
});



module.exports = router;

