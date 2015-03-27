
var express = require('express');
var router = express.Router();

/*router.get('/', function(req, res, next) {
   res.render('job', {error: ""});
});
  */ 
router.get('/save', function(req, res) {
	console.log('Rendering job save page...');
	res.render('job', {error: ""});

});

router.post('/save', function(req, res, next) {
  console.log("saving job...............");
  console.log("******************** CHECK BOX VALUE : "+ req.body.checkbox);


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
       res.render('job', {msg: "User Data Save successfully"});
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
module.exports = router;
