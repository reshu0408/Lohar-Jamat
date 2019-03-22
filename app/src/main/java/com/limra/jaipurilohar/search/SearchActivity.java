package com.limra.jaipurilohar.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.contacts.ContactsActivity;
import com.limra.jaipurilohar.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    private static final String KEY_STATE = "state";
    private static final String KEY_CITIES = "cities";

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.citySpinner)
    Spinner citySpinner;

    @BindView(R.id.gotraSpinner)
    Spinner gotraSpinner;

    @BindView(R.id.stateSpinner)
    Spinner stateSpinner;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog pDialog;
    private String selectedGotra;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        displayLoader();
        loadStateCityDetails();
        loadGotra();
    }

    @OnClick(R.id.search_button)
    public void search(View view) {
        StateCityModel state = (StateCityModel) stateSpinner.getSelectedItem();
        String city = citySpinner.getSelectedItem().toString();

        //TODO call api for filtering

        startActivity(new Intent(this, ContactsActivity.class));
        finish();
    }

    private void loadGotra() {
        ArrayList<String> gotraList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Util.loadJSONFromAsset(this.getBaseContext(), "gotra.json"));
            for (int i = 0; i < jsonArray.length(); i++) {
                String gotra = jsonArray.getString(i);
                gotraList.add(gotra);
            }
            final ArrayAdapter<String> gotraAdapter = new ArrayAdapter<>(SearchActivity.this, R.layout.row_spinner,
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.loading));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void loadStateCityDetails() {
        final List<StateCityModel> statesList = new ArrayList<>();

        pDialog.dismiss();
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
            final StateAdapter stateAdapter = new StateAdapter(SearchActivity.this, R.layout.row_spinner,
                    R.id.spinnerText, statesList);
            stateSpinner.setAdapter(stateAdapter);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    StateCityModel cityDetails = stateAdapter.getItem(position);
                    List<String> cityList = cityDetails.getCities();
                    ArrayAdapter citiesAdapter = new ArrayAdapter<>(SearchActivity.this, R.layout.row_spinner,
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
}
