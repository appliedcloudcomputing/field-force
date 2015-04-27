package com.acc.fieldforce.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acc.fieldforce.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInActivity extends ActionBarActivity implements Animation.AnimationListener{

    private Button signIn;
    private TextView notMember;
    private EditText email, password;
    private TextView emailError, passwordError;
    private boolean bEmail, bPassword;
    private static Animation animSlideUp, animSlideDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


        animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animSlideUp.setAnimationListener(this);
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animSlideDown.setAnimationListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        emailError = (TextView) findViewById(R.id.email_error);
        passwordError = (TextView) findViewById(R.id.password_error);

        signIn = (Button)findViewById(R.id.sign_in);
        notMember = (TextView) findViewById(R.id.not_a_member);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    Intent i = new Intent(SignInActivity.this, MenusActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }
        });

        notMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
        });
    }

    private boolean isValidate(){
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
            passwordError.setVisibility(View.GONE);
        }

        if(bEmail && bPassword){
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