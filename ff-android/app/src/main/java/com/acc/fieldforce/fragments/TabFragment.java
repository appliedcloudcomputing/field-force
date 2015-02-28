package com.acc.fieldforce.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.TabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nilesh on 1/31/2015.
 */
public class TabFragment extends Fragment implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {
    public static final int ASSIGNED = 0;
    public static final int UNASSIGNED = 1;

    private TabHost tabHost;
    private int currentTab = ASSIGNED;
    private ViewPager viewPager;
    private TabsPagerAdapter pageAdapter;
    private List<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment, null);
        tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(this);
        fragments = new ArrayList<Fragment>();
        fragments.add(new Assigned());
        fragments.add(new Unassigned());

        tabHost.setOnTabChangedListener(TabFragment.this);
        tabHost.setCurrentTab(currentTab);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        pageAdapter = new TabsPagerAdapter(getChildFragmentManager(), fragments, getArguments());
        pageAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pageAdapter);
        setupTabs();
    }

    public void setupTabs() {
        tabHost.setup();
        tabHost.addTab(newTab(R.string.title_activity_assigned));
        tabHost.addTab(newTab(R.string.title_activity_unassigned));

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Helvetica Neue.ttf");
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {

            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));

            final View view = tabHost.getTabWidget().getChildTabViewAt(i);
            final View textView = view.findViewById(android.R.id.title);

            ((TextView) textView).setTextColor(Color.parseColor("#0681BE"));
            ((TextView) textView).setSingleLine(true);
            ((TextView) textView).setAllCaps(false);
            ((TextView) textView).setTypeface(type);
            ((TextView) textView).setTextSize(16);
        }

        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#FFFFFF"));
        final View view = tabHost.getTabWidget().getChildTabViewAt(1);
        final View textView = view.findViewById(android.R.id.title);
        ((TextView) textView).setTextColor(Color.parseColor("#FFFFFF"));
        ((TextView) textView).setSingleLine(true);
        ((TextView) textView).setAllCaps(false);
        ((TextView) textView).setTypeface(type);
        ((TextView) textView).setTextSize(16);
    }

    private TabHost.TabSpec newTab(int titleId) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(titleId));
        tabSpec.setIndicator(getString(titleId));
        tabSpec.setContent(new TabFactory(getActivity()));
        return tabSpec;
    }

    @Override
    public void onPageScrollStateChanged(int position) {

    }

    @Override
    public void onPageScrolled(int position, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onTabChanged(String tabId) {
        currentTab = tabHost.getCurrentTab();
        viewPager.setCurrentItem(currentTab);
        updateTab();
    }

    @SuppressWarnings("unused")
    private void updateTab() {
        switch (currentTab) {
            case ASSIGNED:
                Assigned assigned = (Assigned) fragments.get(currentTab);
                break;
            case UNASSIGNED:
                Unassigned unassigned = (Unassigned) fragments.get(currentTab);
                break;
        }
    }

    class TabFactory implements TabHost.TabContentFactory {

        private final Context context;

        public TabFactory(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(context);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }
}
