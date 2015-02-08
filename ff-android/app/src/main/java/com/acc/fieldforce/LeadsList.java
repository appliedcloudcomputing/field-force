package com.acc.fieldforce;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class LeadsList extends FragmentActivity {
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_list);

        fragment = new TabFragment();

        Log.i("fragment", "" + fragment.getId());

        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_leads_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addLeads:
                addCustomer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addCustomer() {
        Intent i = new Intent(LeadsList.this, AddLeads.class);
        startActivity(i);
    }

}
