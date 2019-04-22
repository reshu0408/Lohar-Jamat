package com.limra.jaipurilohar.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.dao.User;
import com.limra.jaipurilohar.userDetail.UserDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Transformation;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<User> mContactsList;
    private Context mContext;

    public ContactsAdapter(List<User> contactsList, Context context) {
        this.mContactsList = contactsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_contacts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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
        holder.phoneTextView.setText(user.getPhoneNumber());
        if(user.getImageUrl() != 0)
            Picasso.get().load(user.getImageUrl()).transform(transformation).into(holder.contactsImageView);
        else
            Picasso.get().load(R.drawable.hammer).transform(transformation).into(holder.contactsImageView);

        holder.addressTextView.setText(user.getAddress() + ", " + user.getCity()+ ", "+ user.getState());
        holder.gotraTextView.setText(user.getGotra());
        holder.nameTextView.setText(user.getFirstName() + " " + user.getLastName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("user", user);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, (View)holder.contactsImageView,  ViewCompat.getTransitionName(holder.contactsImageView));
                mContext.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contactsImageView)
        ImageView contactsImageView;

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.gotraTextView)
        TextView gotraTextView;

        @BindView(R.id.addressTextView)
        TextView addressTextView;

        @BindView(R.id.phoneTextView)
        TextView phoneTextView;

        @BindView(R.id.contactDetailLayout)
        ViewGroup container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
