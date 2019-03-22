package com.limra.jaipurilohar.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.limra.jaipurilohar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<ContactModel> mContactsList;
    private Context mContext;

    public ContactsAdapter(ArrayList<ContactModel> contactsList, Context context){
        this.mContactsList = contactsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_contacts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ContactModel contactModel = mContactsList.get(position);
            holder.phoneTextView.setText(contactModel.getPhoneNo());
            Picasso.get().load(contactModel.getImageUrl())
                         .into(holder.contactsImageView);
            holder.addressTextView.setText(contactModel.getAddress());
            holder.gotraTextView.setText(contactModel.getGotra());
            holder.nameTextView.setText(contactModel.getName());
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
