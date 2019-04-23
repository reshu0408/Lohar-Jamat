package com.limra.jaipurilohar.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.limra.jaipurilohar.LoharApplication;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dao.AppDataBase;
import com.limra.jaipurilohar.dao.User;
import com.limra.jaipurilohar.dashboard.DashboardActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import com.limra.jaipurilohar.register.RegistrationActivity;

import static com.limra.jaipurilohar.util.Constants.MY_PREFS_NAME;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    EditText userNameView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.sign_up_text)
    TextView signUpTextView;
    @BindView(R.id.sign_in_button)
    TextView signInButton;

    private UserLoginTask mAuthTask = null;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.sign_in_button)
    public void signInClick(View view) {
        attemptLogin();
    }

    @OnEditorAction(R.id.password)
    public boolean onDonePressed(TextView tv, int id, KeyEvent event) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        userNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        userName = userNameView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordLengthValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_length));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid userName address.
        if (TextUtils.isEmpty(userName)) {
            userNameView.setError(getString(R.string.error_username_required));
            focusView = userNameView;
            cancel = true;
        } else if (!isUserNameValid(userName)) {
            userNameView.setError(getString(R.string.error_invalid_username));
            focusView = userNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(userName, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUserNameValid(String userName) {
        return userName.length() > 5;
    }

    private boolean isPasswordLengthValid(String password) {
        return password.length() > 6;
    }

    private void showProgress(boolean showProgress) {
        mProgressView.setVisibility(showProgress ? View.VISIBLE : View.INVISIBLE);
        signInButton.setVisibility(showProgress ? View.INVISIBLE : View.VISIBLE);
    }

    private void showSnackBar(int stringId) {
        Snackbar.make(userNameView, stringId, Snackbar.LENGTH_SHORT).show();
    }

    private void showDashBoard() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", userName);
        editor.apply();

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_up_text)
    public void goToRegister(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    class UserLoginTask extends AsyncTask<Void, Void, Map<Integer, Boolean>> {

        private final String mUserName;
        private final String mPassword;

        Map<Integer, Boolean> resultMap = new HashMap<>();

        UserLoginTask(String userName, String password) {
            mUserName = userName;
            mPassword = password;
            resultMap.put(0, false);
            resultMap.put(1, false);
        }

        @Override
        protected Map<Integer, Boolean> doInBackground(Void... params) {
            AppDataBase db = AppDataBase.getAppDatabase(LoginActivity.this);
            List<User> mContactsList = db.userDao().getAll();
            if(mContactsList != null && mContactsList.size()>0){
                int i = 0;
                for (; i < mContactsList.size(); i++) {
                    String username = mContactsList.get(i).getUserName();
                    String password = mContactsList.get(i).getPassword();
                    if (username.equals(mUserName)) {
                        // Account exists, return true if the password matches.
                        resultMap.put(0, true);
                        resultMap.put(1, password.equals(mPassword));
                        break;
                    }
                }
                if (i == mContactsList.size()) {
                    //user dows not exist
                    resultMap.put(0, false);
                }
            }


            return resultMap;
        }

        @Override
        protected void onPostExecute(final Map<Integer, Boolean> userpwdMap) {
            mAuthTask = null;
            showProgress(false);

            if (userpwdMap.get(0) && userpwdMap.get(1)) {
                showSnackBar(R.string.login_success);
                showDashBoard();
                finish();
            } else {
                if (userpwdMap != null) {
                    if (!userpwdMap.get(0)) {
                        userNameView.setError(getString(R.string.error_no_user_exist));
                        userNameView.requestFocus();
                    } else {
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }

                }

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

