package com.limra.jaipurilohar.userDetail;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dao.User;
import com.squareup.picasso.Picasso;

public class UserDetailActivity extends AppCompatActivity  implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.7f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    @BindView(R.id.img_head)
    ImageView imgHead;

    @BindView(R.id.addressTextView)
    TextView addressView;

    @BindView(R.id.gotraTextView)
    TextView gotratextView;

    @BindView(R.id.business)
    TextView businessTextView;

    @BindView(R.id.call)
    ImageView callImageView;

    @BindView(R.id.map)
    ImageView map;

    @BindView(R.id.main_linearlayout_title)
    LinearLayout mTitleContainer;

    @BindView(R.id.main_textview_title)
    TextView mTitle;

    @BindView(R.id.main_appbar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_expanded)
    TextView titleExpanded;

    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ButterKnife.bind(this);
        mUser = getIntent().getExtras().getParcelable("user");
        if(mUser != null){
            loadUserData();
        }

        mAppBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
    }

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

    @OnClick(R.id.call)
    public void callUser(){
        String phone = mUser.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    @OnClick(R.id.map)
    public void showMap(){
        String address = mUser.getAddress()+ " " + mUser.getCity()+ " " + mUser.getState();

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @OnClick(R.id.download_button)
    public void download(){
        Snackbar.make(addressView, getResources().getString(R.string.download_Khandan_information),Snackbar.LENGTH_SHORT).show();
    }

    private void loadUserData(){
        if(mUser.getImageUrl() != 0)
        imgHead.setImageDrawable(getResources().getDrawable(mUser.getImageUrl()));
        else
            imgHead.setImageDrawable(getResources().getDrawable(R.drawable.hammer));
        addressView.setText(mUser.getAddress() + ", " + mUser.getCity() + ", " + mUser.getState());
        businessTextView.setText(mUser.getOccupation());
        mTitle.setText(mUser.getFirstName() + " " + mUser.getLastName());
        titleExpanded.setText(mUser.getFirstName() + " " + mUser.getLastName());
        gotratextView.setText(mUser.getGotra());
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
