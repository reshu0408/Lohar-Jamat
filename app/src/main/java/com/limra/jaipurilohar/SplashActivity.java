package com.limra.jaipurilohar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.limra.jaipurilohar.dao.AppDataBase;
import com.limra.jaipurilohar.dao.User;
import com.limra.jaipurilohar.dashboard.DashboardActivity;
import com.limra.jaipurilohar.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 3000;
    @BindView(R.id.headTextView1)
    TextView mHeadTextView1;
    @BindView(R.id.headTextView2)
    TextView mHeadTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "arabic.otf");
        mHeadTextView1.setTypeface(face);
        mHeadTextView2.setTypeface(face);

        InsertUserData insertUserDataTask = new InsertUserData();
        insertUserDataTask.execute();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void addUserList(List<User> userList) {
        AppDataBase db = AppDataBase.getAppDatabase(this);
        List<User> currentUserList = db.userDao().getAll();
        if(currentUserList == null || currentUserList.size() ==0){
            AppDataBase.getAppDatabase(this).userDao().insertIntoUserList(userList);
        }
    }

    class InsertUserData extends AsyncTask {
        Intent intent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            List<User> userList = new ArrayList<>();

            User usman = new User(1,"Usman","Ghani", "8742020289", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",
                    R.drawable.usman);
            User tony = new User(2,"Tony","Chaudhary", "8742020289", "Sinodiya ke Zindran", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business"
                    ,R.drawable.tony);
            User shadab = new User(3,"Shadab","Ahamad", "9589740941", "Badhal ke Agwan", "Flat no. 15, 16, Building 19 , Rakshak Nagar Phase 1", "Pune", "Maharashtra","Software Engineer",
                    R.drawable.shadab);
            User nizamuddin = new User(4,"Mohammad","Nizamuddin Lahori", "9827266258", "Kuchaman ke Lahori", "Champa Bagh", "Indore", "Madhya Pradesh","Business" ,R.drawable.nizamuddin);
            User nizam = new User(5,"Nizam","Chaudhary", "8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business" , R.drawable.nizam);
            User rauf = new User(6,"Mohammad","Rauf Thekedar", "8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business"
                    ,R.drawable.rauf);
            User fayyaz = new User(7,"Fayyaz","Ahamad", "9001956350", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Lathe Machine Specialist" , R.drawable.fayyaz);
            User naim = new User(8,"Mohammad","Naim", "7300109689", "Badhal ke Agwan", "Luharo Ka Mohalla", "Vikaramgarh Alot", "Madhya Pradesh","Software Engineer" ,R.drawable.naim);
            User wasim = new User(9,"Abdul","Wasim", "8989010685", "Farkya ke Mughal", "4, Government Colony Road, BirlaGram", "Nagda", "Madhya Pradesh","Teacher",R.drawable.wasim );
            User sadar = new User(10,"Sadar","Sahab", "8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business",
                    R.drawable.sadar);
            User saddam = new User(11,"Saddam","Jindran", "8742020289", "Joye", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business",
                    R.drawable.saddam);
            User aslam = new User(12,"Aslam","Chohan", "8742020289", "Chaksu ke Chohan", "Jalupura", "Jaipur", "Rajasthan","Furniture Business",R.drawable.aslam );
            User siraz = new User(13,"Mohammad","Siraz", "9414570377", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",
                    R.drawable.siraz);
            User akram = new User(14,"Akram","Chohan", "8742020289", "Chaksu ke Chohan", "Jalupura", "Jaipur", "Rajasthan","Printing Business",R.drawable.akram );
            User aabid = new User(15,"Aabid","Ahamad", "8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh"," Student",R.drawable.aabid );
            User zakir = new User(16,"Mohammad","Zakir", "8742020289", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business" ,R.drawable.zakir);
            User azaz = new User(17,"Azaz","Ahamad", "9001689786", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",R.drawable.azaz );

            userList.add(shadab);
            userList.add(tony);
            userList.add(usman);
            userList.add(nizam);
            userList.add(rauf);
            userList.add(siraz);
            userList.add(wasim);
            userList.add(akram);
            userList.add(aslam);
            userList.add(naim);
            userList.add(saddam);
            userList.add(azaz);
            userList.add(fayyaz);
            userList.add(sadar);
            userList.add(zakir);
            userList.add(aabid);
            userList.add(nizamuddin);
            addUserList(userList);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    SplashActivity.this.finish();
                    startActivity(intent);
                }
            }, SPLASH_TIMEOUT);
        }
    }

}
