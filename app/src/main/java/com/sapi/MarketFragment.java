package com.sapi;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment {


    private ViewPager marketviewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public FloatingActionButton fab_add_market, fab_market_search;
    SharedPreferences pref;

    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View market=inflater.inflate(R.layout.fragment_market, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        pref = getActivity().getPreferences(0);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        marketviewPager = (ViewPager) market.findViewById(R.id.market_container);
        marketviewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) market.findViewById(R.id.market_tab);
        tabLayout.setupWithViewPager(marketviewPager);
        market.findViewById(R.id.fab_add_market).setVisibility(View.GONE);
        if(pref.getBoolean(Constants.IS_LOGGED_IN,true)) {
            market.findViewById(R.id.fab_add_market).setVisibility(View.VISIBLE);
        }
        fab_market_search= (FloatingActionButton) market.findViewById(R.id.fab_market_search);
        fab_add_market = (FloatingActionButton) market.findViewById(R.id.fab_add_market);
        fab_add_market.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AddMarketFragment addmarket = new AddMarketFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_frame, addmarket, addmarket.getTag())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return market;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    NoteMarket noteMarket = new NoteMarket();
                    return noteMarket;
                case 1:
                    RentMarket rentMarket = new RentMarket();
                    return rentMarket;
                case 2:
                    OtherMarket otherMarket = new OtherMarket();
                    return otherMarket;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tanuláshoz";
                case 1:
                    return "Albérlet";
                case 2:
                    return "Egyéb";
            }
            return null;
        }
    }
}
