package com.acc.fieldforce.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.acc.fieldforce.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends ActionBarActivity implements Animation.AnimationListener{

    private TextView name, email, password, confirmPassword, mobile, location;
    private TextView nameError, emailError, passwordError, confirmPasswordError, mobileError, locationError;
    private Button signUp;
    private TextView alreadyMember;
    private boolean bName, bEmail, bPassword, bConfirmPassword, bMobile, bLocation;
    private static Animation animSlideUp, animSlideDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animSlideUp.setAnimationListener(this);
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animSlideDown.setAnimationListener(this);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        confirmPassword = (TextView) findViewById(R.id.confirm_password);
        mobile = (TextView) findViewById(R.id.mobile_number);
        location = (TextView) findViewById(R.id.location);

        nameError = (TextView) findViewById(R.id.name_error);
        emailError = (TextView) findViewById(R.id.email_error);
        passwordError = (TextView) findViewById(R.id.password_error);
        confirmPasswordError = (TextView) findViewById(R.id.confirm_password_error);
        mobileError = (TextView) findViewById(R.id.mobile_number_error);
        locationError = (TextView) findViewById(R.id.location_error);

        signUp = (Button) findViewById(R.id.sign_up);
        alreadyMember = (TextView) findViewById(R.id.already_member);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    Intent i = new Intent(SignUpActivity.this, MenusActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }
        });

        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
                finish();
            }
        });
    }

    private boolean isValidate(){
        final String uName = name.getText().toString();
        if (isValidName(uName)) {
            bName = false;
            //nameError.startAnimation(animSlideDown);
            nameError.setVisibility(View.VISIBLE);
        }else {
            bName = true;
            nameError.setVisibility(View.GONE);
            //nameError.startAnimation(animSlideUp);
        }

        final String uEmail = email.getText().toString();
        if (!isValidEmail(uEmail)) {
            bEmail = false;
            //emailError.startAnimation(animSlideDown);
            emailError.setVisibility(View.VISIBLE);
        }else {
            bEmail = true;
            emailError.setVisibility(View.GONE);
        }

        final String uPassword = password.getText().toString();
        if (!isValidPassword(uPassword)) {
            bPassword = false;
            //passwordError.startAnimation(animSlideDown);
            passwordError.setVisibility(View.VISIBLE);
        }else {
            bPassword = true;
            //passwordError.startAnimation(animSlideUp);
            passwordError.setVisibility(View.GONE);
        }

        final String uPasswordConfirm = confirmPassword.getText().toString();
        if (!isValidConfirmPassword(uPasswordConfirm)) {
            bConfirmPassword = false;
            //confirmPasswordError.startAnimation(animSlideDown);
            confirmPasswordError.setVisibility(View.VISIBLE);
        }else {
            bConfirmPassword = true;
            confirmPasswordError.setVisibility(View.GONE);
        }

        final String uMobNo = mobile.getText().toString();
        if (!isValidMobNo(uMobNo)) {
            bMobile = false;
            //mobileError.startAnimation(animSlideDown);
            mobileError.setVisibility(View.VISIBLE);
        }else {
            bMobile = true;
            mobileError.setVisibility(View.GONE);
        }

        final String uLocation = location.getText().toString();
        if (!isValidLocation(uLocation)) {
            bLocation = false;
            //locationError.startAnimation(animSlideDown);
            locationError.setVisibility(View.VISIBLE);
        }else {
            bLocation = true;
            locationError.setVisibility(View.GONE);
        }

        if(bName && bEmail && bPassword && bConfirmPassword && bMobile && bLocation){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidName(String uName) {
        if(uName.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidEmail(String uEmail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(uEmail);
        return matcher.matches();
    }

    private boolean isValidPassword(String uPassword) {
        if(uPassword.length()<6){
            return false;
        }else{
            return true;
        }
    }

    private boolean isValidConfirmPassword(String uPassword) {
        if(uPassword.equals(password.getText().toString())){
            return true;
        }else{
            return false;
        }
    }

    private boolean isValidMobNo(String uMobNo) {
        if(uMobNo.equals("") || uMobNo.length()<10){
        return false;
        }else{
            return true;
        }
    }

    private boolean isValidLocation(String uLocation) {
        if(uLocation.equals("")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
