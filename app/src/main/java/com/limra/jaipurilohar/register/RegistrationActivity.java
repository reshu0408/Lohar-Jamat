package com.limra.jaipurilohar.register;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.search.SearchActivity;
import com.limra.jaipurilohar.util.Util;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {


    @BindView(R.id.gotraSpinner)
    Spinner gotraSpinner;

    private String selectedGotra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        loadGotra();
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
}
