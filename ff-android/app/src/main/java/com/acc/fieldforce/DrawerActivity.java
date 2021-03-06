package com.acc.fieldforce;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acc.fieldforce.activities.AccountSummaryActivity;
import com.acc.fieldforce.activities.CustomerListActivity;
import com.acc.fieldforce.activities.ExpensesActivity;
import com.acc.fieldforce.activities.JobListingActivity;
import com.acc.fieldforce.activities.LeadsListActivity;
import com.acc.fieldforce.activities.ListExpensesActivity;
import com.acc.fieldforce.activities.MenusActivity;
import com.acc.fieldforce.activities.TabActivity;

import java.util.ArrayList;


public class DrawerActivity extends ActionBarActivity {
    private static String TAG = DrawerActivity.class.getSimpleName();
    ListView mListView;
    private Toolbar toolbar;
    RelativeLayout mNavPane;
    private ActionBarDrawerToggle mDrawerToggle;
    protected DrawerLayout mDrawerLayout;
    private Context mContext;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    final int blue[] = {
            R.drawable.ic_home168,
            R.drawable.ic_social_network15,
            R.drawable.ic_leaders1,
            R.drawable.ic_jobsblue,
            R.drawable.ic_expensive,
            R.drawable.ic_savings
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavItems.add(new NavItem("Home" , R.drawable.ic_home));
        mNavItems.add(new NavItem("Customer", R.drawable.ic_social_network));
        mNavItems.add(new NavItem("Leads", R.drawable.ic_leaders));
        mNavItems.add(new NavItem("Jobs" , R.drawable.ic_jobs));
        mNavItems.add(new NavItem("Expenses", R.drawable.ic_expense));
        mNavItems.add(new NavItem("Account" , R.drawable.ic_savings1));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.navDrawer);
        mNavPane = (RelativeLayout) findViewById(R.id.navDrawer_pane);
        mListView = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar , R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItemFromDrawer(int position) {
      /*  Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .commit();
*/
        Intent intent = null;
        switch (position){

            case 0:
                intent = new Intent(this , MenusActivity.class);
                startActivity(intent);
                finish();
                break;

            case 1:
                intent = new Intent(this , CustomerListActivity.class);
                startActivity(intent);
                finish();
                break;

            case 2:
                intent = new Intent(this, TabActivity.class);
                startActivity(intent);
                finish();
                break;

            case 3:
                intent = new Intent(this, JobListingActivity.class);
                startActivity(intent);
                finish();
                break;

            case 4:
                intent = new Intent(this, ListExpensesActivity.class);
                startActivity(intent);
                finish();
                break;

            case 5:
                intent = new Intent(this, AccountSummaryActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
        mListView.setItemChecked(position, true);

        // Close the drawer
        mDrawerLayout.closeDrawer(mNavPane);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mNavPane);
        MenuItem item =  menu.findItem(R.id.action_settings);
        item.setVisible(false);
//        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    class NavItem {
        String mTitle;
        int mIcon;

        public NavItem(String title, int icon) {
            mTitle = title;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.navtitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.navicon);

            titleView.setText( mNavItems.get(position).mTitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    public static class PreferencesFragment extends Fragment {

        public PreferencesFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_preferences, container, false);
        }
    }
}
