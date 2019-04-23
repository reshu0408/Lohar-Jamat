package com.limra.jaipurilohar.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.limra.jaipurilohar.LoharApplication;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dao.AppDataBase;
import com.limra.jaipurilohar.dao.User;
import com.limra.jaipurilohar.dashboard.DashboardActivity;
import com.limra.jaipurilohar.search.SearchActivity;
import com.limra.jaipurilohar.search.StateAdapter;
import com.limra.jaipurilohar.search.StateCityModel;
import com.limra.jaipurilohar.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.limra.jaipurilohar.util.Constants.MY_PREFS_NAME;

public class RegistrationActivity extends AppCompatActivity {

    private static final String KEY_STATE = "state";
    private static final String KEY_CITIES = "cities";

    @BindView(R.id.gotraSpinner)
    Spinner gotraSpinner;

    @BindView(R.id.firstName)
    TextView firstNameTextView;

    @BindView(R.id.last_name)
    TextView lastNameTextView;

    @BindView(R.id.userName)
    TextView usernameTextView;

    @BindView(R.id.password)
    TextView passwordTextView;

    @BindView(R.id.stateSpinner)
    Spinner stateSpinner;

    @BindView(R.id.citySpinner)
    Spinner citySpinner;

    @BindView(R.id.occupation)
    TextView occupationTextView;

    @BindView(R.id.phone_number)
    TextView phoneTextView;

    @BindView(R.id.addressTextView)
    TextView addressTextView;

    private String selectedGotra;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String state;
    private String city;
    private String occupation;
    private String phone;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        loadGotra();
        loadStateCityDetails();
    }

    private void loadGotra() {
        List<String> gotraList = Util.getGotraList(this);
        final ArrayAdapter<String> gotraAdapter = new ArrayAdapter<>(this, R.layout.row_spinner,
                R.id.spinnerText, gotraList);
        gotraSpinner.setAdapter(gotraAdapter);

        gotraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedGotra = gotraAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadStateCityDetails() {
        final List<StateCityModel> statesList = new ArrayList<>();

        try {
            JSONArray responseArray = new JSONArray(Util.loadJSONFromAsset(this.getBaseContext(), "state_city.json"));

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject response = responseArray.getJSONObject(i);
                String state = response.getString(KEY_STATE);
                JSONArray cities = response.getJSONArray(KEY_CITIES);
                List<String> citiesList = new ArrayList<>();
                for (int j = 0; j < cities.length(); j++) {
                    citiesList.add(cities.getString(j));
                }
                statesList.add(new StateCityModel(state, citiesList));
            }
            final StateAdapter stateAdapter = new StateAdapter(RegistrationActivity.this, R.layout.row_spinner,
                    R.id.spinnerText, statesList);
            stateSpinner.setAdapter(stateAdapter);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    StateCityModel cityDetails = stateAdapter.getItem(position);
                    List<String> cityList = cityDetails.getCities();
                    ArrayAdapter citiesAdapter = new ArrayAdapter<>(RegistrationActivity.this, R.layout.row_spinner,
                            R.id.spinnerText, cityList);
                    citySpinner.setAdapter(citiesAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.register_button)
    public void register(){
        firstName = firstNameTextView.getText().toString();
        lastName = lastNameTextView.getText().toString();
        userName = usernameTextView.getText().toString();
        password = passwordTextView.getText().toString();
        address = addressTextView.getText().toString();
        phone = phoneTextView.getText().toString();
        occupation = occupationTextView.getText().toString();
        selectedGotra = gotraSpinner.getSelectedItem().toString();
        city = citySpinner.getSelectedItem().toString();
        state = ((StateCityModel)stateSpinner.getSelectedItem()).getStateName();
        if(verifyDetails()){
            resetError();
            User user = new User(firstName,lastName,userName,password,phone,selectedGotra,address,city, state,occupation,"");
            AppDataBase db = AppDataBase.getAppDatabase(this);
            db.userDao().insertIntoUserInfo(user);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("username", userName);
            editor.apply();
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void resetError() {
        firstNameTextView.setError(null);
        lastNameTextView.setError(null);
        usernameTextView.setError(null);
        passwordTextView.setError(null);
        phoneTextView.setError(null);
        addressTextView.setError(null);
        occupationTextView.setError(null);
    }

    private boolean verifyDetails() {
        boolean isVerified = true;
        if(TextUtils.isEmpty(firstName)){
            firstNameTextView.setError(getString(R.string.enter_first_name));
            firstNameTextView.requestFocus();
            isVerified = false;
        }
        if(TextUtils.isEmpty(lastName)){
            lastNameTextView.setError(getString(R.string.enter_last_name));
            lastNameTextView.requestFocus();
            isVerified = false;
        }
        if(TextUtils.isEmpty(userName)){
            usernameTextView.setError(getString(R.string.enter_username));
            usernameTextView.requestFocus();
            isVerified = false;
        }
        if(TextUtils.isEmpty(phone)){
            phoneTextView.setError(getString(R.string.enter_phone));
            phoneTextView.requestFocus();
            isVerified = false;
        }
        if(TextUtils.isEmpty(address)){
            addressTextView.setError(getString(R.string.enter_address));
            addressTextView.requestFocus();
            isVerified = false;
        }
        if(TextUtils.isEmpty(password)){
            passwordTextView.setError(getString(R.string.enter_password));
            passwordTextView.requestFocus();
            isVerified = false;
        }if(TextUtils.isEmpty(occupation)){
            occupationTextView.setError(getString(R.string.enter_occupation));
            occupationTextView.requestFocus();
            isVerified = false;
        }
        return isVerified;
    }
}
