package com.limra.jaipurilohar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

import static com.limra.jaipurilohar.util.Constants.MY_PREFS_NAME;

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

            User usman = new User("Usman","Ghani", "usman123","usman@123","8742020289", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",
                    R.drawable.usman);
            User tony = new User("Tony","Chaudhary", "tony123","tony@123","8742020289", "Sinodiya ke Zindran", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business"
                    ,R.drawable.tony);
            User shadab = new User("Shadab","Ahamad","shadab123","shadab@123", "9589740941", "Badhal ke Agwan", "Flat no. 15, 16, Building 19 , Rakshak Nagar Phase 1", "Pune", "Maharashtra","Software Engineer",
                    R.drawable.shadab);
            User nizamuddin = new User("Mohammad","Nizamuddin Lahori", "nizamuddin@123", "nizamuddin@123","9827266258", "Kuchaman ke Lahori", "Champa Bagh", "Indore", "Madhya Pradesh","Business" ,R.drawable.nizamuddin);
            User nizam = new User("Nizam","Chaudhary", "nizam123", "nizam@123","8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business" , R.drawable.nizam);
            User rauf = new User("Mohammad","Rauf Thekedar","rauf123","rauf@123", "8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business"
                    ,R.drawable.rauf);
            User fayyaz = new User("Fayyaz","Ahamad", "fayyaz123", "fayyaz@123", "9001956350", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Lathe Machine Specialist" , R.drawable.fayyaz);
            User naim = new User("Mohammad","Naim", "naim123", "naim@123", "7300109689", "Badhal ke Agwan", "Luharo Ka Mohalla", "Vikaramgarh Alot", "Madhya Pradesh","Software Engineer" ,R.drawable.naim);
            User wasim = new User("Abdul","Wasim", "wasim123","wasim@123","8989010685", "Farkya ke Mughal", "4, Government Colony Road, BirlaGram", "Nagda", "Madhya Pradesh","Teacher",R.drawable.wasim );
            User zakir = new User("Mohammad","Zakir", "zakir123", "zakir@123","8742020289", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business" ,R.drawable.zakir);
            User saddam = new User("Saddam","Jindran","saddam123","saddam@123", "8742020289", "Joye", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh","Business",
                    R.drawable.saddam);
            User aslam = new User("Aslam","Chohan","aslam123","aslam@123", "8742020289", "Chaksu ke Chohan", "Jalupura", "Jaipur", "Rajasthan","Furniture Business",R.drawable.aslam );
            User siraz = new User("Mohammad","Siraz","siraz123","siraz@123", "9414570377", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",
                    R.drawable.siraz);
            User akram = new User("Akram","Chohan", "akram123","akram@123","8742020289", "Chaksu ke Chohan", "Jalupura", "Jaipur", "Rajasthan","Printing Business",R.drawable.akram );
            User aabid = new User("Aabid","Ahamad", "aabid123","aabid@123","8742020289", "Badhal ke Agwan", "Flat 403, Kirti Palace, Sree Nagar", "Indore", "Madhya Pradesh"," Student",R.drawable.aabid );
            User azaz = new User("Azaz","Ahamad", "azaz123","azaz@123","9001689786", "Badhal ke Agwan", "Gayatri Mandir Road", "Chaumahla", "Rajasthan","Business",R.drawable.azaz );

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
                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    String name = prefs.getString("username", "");//"No name defined" is the default value.
                    if(TextUtils.isEmpty(name)){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        SplashActivity.this.finish();
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                        SplashActivity.this.finish();
                        startActivity(intent);
                    }
                }
            }, SPLASH_TIMEOUT);
        }
    }

}
