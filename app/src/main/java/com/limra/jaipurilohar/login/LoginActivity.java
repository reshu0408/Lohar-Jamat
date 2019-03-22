package com.limra.jaipurilohar.login;

import android.content.Intent;
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
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dashboard.DashboardActivity;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class LoginActivity extends AppCompatActivity {

    private static final String[] DUMMY_CREDENTIALS = new String[]{"ahamad_shadab:Ahad@123", "salim_reshma:Ahad@123"};
    @BindView(R.id.userName)
    EditText userNameView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.forgot_password_text)
    TextView forgotPasswordTextView;
    @BindView(R.id.sign_up_text)
    TextView signUpTextView;
    @BindView(R.id.sign_in_button)
    TextView signInButton;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        forgotPasswordTextView.setPaintFlags(forgotPasswordTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
        String userName = userNameView.getText().toString();
        String password = mPasswordView.getText().toString();

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
        return userName.length() > 8;
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
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
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
            // TODO: attempt authentication against a network service.
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {

                return resultMap;
            }

            int i = 0;
            for (; i < DUMMY_CREDENTIALS.length; i++) {
                String credential = DUMMY_CREDENTIALS[i];
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUserName)) {
                    // Account exists, return true if the password matches.
                    resultMap.put(0, true);
                    resultMap.put(1, pieces[1].equals(mPassword));
                    break;
                }
            }
            if (i == DUMMY_CREDENTIALS.length) {
                //user dows not exist
                resultMap.put(0, false);
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

