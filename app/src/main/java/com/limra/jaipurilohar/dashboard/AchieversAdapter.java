package com.limra.jaipurilohar.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dao.User;
import com.limra.jaipurilohar.userDetail.UserDetailActivity;
import com.limra.jaipurilohar.util.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class AchieversAdapter extends RecyclerView.Adapter<AchieversAdapter.ViewHolder> {

    private List<User> mContactsList;
    private Context mContext;

    public AchieversAdapter(List<User> contactsList, Context context) {
        this.mContactsList = contactsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_achiever, parent, false);
        return new ViewHolder(v);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String[] achievements = new String[]{
            "H1B Processed", "80% in 12th", "Highest Marks in 12th", "Doctorate Degree","Amsterdam Visa", "Urdu Olympiad WInner"
        };

        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap result = Bitmap.createScaledBitmap(source, 50, 50, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        User user = mContactsList.get(position);
        Picasso.get().load(Util.getImageUrl(user.getImageUrl())).transform(transformation).into(holder.contactsImageView);
        holder.nameTextView.setText(user.getFirstName() + " " + user.getLastName());
        holder.achievementTextView.setText(achievements[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contactsImageView)
        ImageView contactsImageView;

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.achievementTextView)
        TextView achievementTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
