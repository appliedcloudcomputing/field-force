var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');

GLOBAL.Parse = require('parse').Parse;
if(process && process.env && process.env.APPLICATION_ID && process.env.JAVASCRIPT_KEY && process.env.MASTER_KEY) {
    Parse.initialize(process.env.APPLICATION_ID, process.env.JAVASCRIPT_KEY, process.env.MASTER_KEY);
} else {    
      Parse.initialize("O3zQqGoHN3vFLHnxftd2zzdUV3Rwu1KZ8fEclJ8n", "ANEYTgsWpOcH5VyB7RhM2E95tvNRBUcicMkifrRs", "fwdGtSRoEOa07ZnHgsozoVm9kV0qzW4XqMhZZw3U");
}
var job = require('./routes/job');
var routes = require('./routes/index');
var users = require('./routes/users');
var login = require('./routes/login');
var customer = require('./routes/customer');
var profile = require('./routes/profile');
var expenses = require('./routes/expenses');
var dashboard = require('./routes/dashboard');
var changepassword = require('./routes/changepassword');
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.engine('html',require('ejs').renderFile);
app.set('view engine', 'html');

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(session({secret:'O3zQqGoHN3vFLHnxftd2zzdUV3Rwu1KZ8fEclJ8n',
                resave: true,
                saveUninitialized: false}));

app.use('/', routes);
app.use('/users', users);
app.use('/login', login);
app.use('/dashboard', dashboard);
app.use('/job',job);
app.use('/changepassword',changepassword);
app.use('/customer',customer);
app.use('/profile',profile);
app.use('/expenses',expenses);
// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
        console.log("------------ERROR OBJECT: " + JSON.stringify(err));
        console.log("------------ERROR MESSAGE: " + err.message);
        console.log("------------REQUEST URL: " + req.url);
        if(req.session.user){            
            res.render('404', {
                message: err.message,
                error: err,
                loggedIn: "true"
            });
        } else {            
            res.render('404', {
                message: err.message,
                error: err     
            });            
        }
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  console.log("----------------------------------------------ERROR OBJECT: " + JSON.stringify(err));
  console.log("----------------------------------------------ERROR MESSAGE: " + err.message);
  console.log("----------------------------------------------REQUEST URL: " + req.url);
  res.render('404', {
      message: err.message,
      error: {}
  });
});


module.exports = app;