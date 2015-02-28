package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acc.fieldforce.R;


public class SignUpActivity extends Activity {

    private Button signUp;
    private ImageView roundImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = (Button) findViewById(R.id.signup);

        roundImage = (ImageView) findViewById(R.id.roundImage);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        roundImage.setImageBitmap(icon);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, MenusActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        });
    }
}