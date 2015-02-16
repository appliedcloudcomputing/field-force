package com.acc.fieldforce.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acc.fieldforce.R;

/**
 * Created by Sagar on 1/31/2015.
 */
public class JobFragmentOne extends Fragment {

    private ImageView roundImage;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_one, container , false);

        roundImage = (ImageView) view.findViewById(R.id.cust);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        roundImage.setImageBitmap(icon);

        return view;
    }
}
