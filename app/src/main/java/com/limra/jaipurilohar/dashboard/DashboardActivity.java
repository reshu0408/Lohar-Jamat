package com.limra.jaipurilohar.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.aboutUs.AboutUsActivity;
import com.limra.jaipurilohar.contacts.ContactModel;
import com.limra.jaipurilohar.contacts.ContactsAdapter;
import com.limra.jaipurilohar.gallery.GalleryActivity;
import com.limra.jaipurilohar.search.SearchActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    ArrayList<Integer> mResources = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.achieversRecyclerView)
    RecyclerView achieversRecyclerView;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    private int currentPage = 0;
    private Timer timer;
    private ArrayList<ContactModel> mContactsList;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_gallery:
                Intent galleryIntent = new Intent(this, GalleryActivity.class);
                startActivity(galleryIntent);
                break;
            case R.id.nav_credits:
                Intent aboutUsIntent = new Intent(this, AboutUsActivity.class);
                startActivity(aboutUsIntent);
                break;
            case R.id.nav_search:
                Intent SearchIntent = new Intent(this, SearchActivity.class);
                startActivity(SearchIntent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.clearFocus();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 5; i++)
            mResources.add(R.drawable.i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(200, 0, 200, 0);
        mViewPager.setPageMargin(40);
        mViewPager.setAdapter(mCustomPagerAdapter);
        //mViewPager.setPageTransformer(true, new ZoomOutTransformation() );

        indicator.setViewPager(mViewPager);
        mCustomPagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());

        int NUM_PAGES = mCustomPagerAdapter.getCount();
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        mContactsList = getContactsList();
        achieversRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter contactsAdapter = new ContactsAdapter(mContactsList, this);
        achieversRecyclerView.setAdapter(contactsAdapter);

    }

    private ArrayList<ContactModel> getContactsList() {
        ContactModel contactModel = new ContactModel("Andrew Symonds", "99% in Higher Secondary", "Award : $500",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.avtar1);
        ContactModel contactModel1 = new ContactModel("Mahendra Singh Dhoni", "Gold Medal in B.Tech", "Award : $500",
                " Bangla No 786, Srinagar, Indore", R.drawable.avtar2);
        ContactModel contactModel2 = new ContactModel("Abdul Rauf", "Gold Medal in MBBS", "Award : $500",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.avtar3);
        ContactModel contactModel3 = new ContactModel("Mohammad Siraj", "Gold Medal in MBBS", "Award : $500",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.avtar4);
        ContactModel contactModel4 = new ContactModel("Nizam Choudhary", "Olympiad Winner", "Award : $500",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.avtar5);
        ContactModel contactModel5 = new ContactModel("Haji Salim Lahori", "US H1B Holder", "Award : $500",
                " Bangla No 786, Srinagar, Indore", R.drawable.avtar6);
        ContactModel contactModel6 = new ContactModel("Chris Gayle", "10th Topper", "Award : $500",
                " Bangla No 786, Srinagar, Indore", R.drawable.avtar6);
        ContactModel contactModel7 = new ContactModel("Sehwag", "8th Topper", "Award : $500",
                " Bangla No 786, Srinagar, Indore", R.drawable.avtar1);

        mContactsList = new ArrayList<>();
        mContactsList.add(contactModel);
        mContactsList.add(contactModel1);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel3);
        mContactsList.add(contactModel4);
        mContactsList.add(contactModel5);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel6);
        mContactsList.add(contactModel7);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel2);

        return mContactsList;
    }

    class CustomPagerAdapter extends PagerAdapter {

        private ArrayList<Integer> images;
        private LayoutInflater inflater;
        private Context context;

        public CustomPagerAdapter(Context context, ArrayList<Integer> images) {
            this.context = context;
            this.images = images;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View myImageLayout = inflater.inflate(R.layout.pager_item, view, false);
            ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
            Bitmap result = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(context.getResources(), images.get(position)), 200, 200, false);
            myImage.setImageBitmap(result);
            view.addView(myImageLayout, 0);
            return myImageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
