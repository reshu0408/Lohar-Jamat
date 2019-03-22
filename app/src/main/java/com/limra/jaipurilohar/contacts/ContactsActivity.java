package com.limra.jaipurilohar.contacts;

import android.os.Bundle;
import android.view.MenuItem;

import com.limra.jaipurilohar.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.contactsRecyclerView)
    RecyclerView contactsRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ArrayList<ContactModel> mContactsList;

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
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContactsList = getContactsList();
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter contactsAdapter = new ContactsAdapter(mContactsList, this);
        contactsRecyclerView.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactModel> getContactsList() {
        ContactModel contactModel = new ContactModel("Shadab Ahamad", "Bhadhal Ke agwan", "+91-9589740941",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.img_shadab);
        ContactModel contactModel1 = new ContactModel("Tony Chaudhary", "Sinodiya k Zindran", "+91-9589740941",
                " Bangla No 786, Srinagar, Indore", R.drawable.tony);
        ContactModel contactModel2 = new ContactModel("Abdul Rauf", "Bhadhal Ke agwan", "+91-9589740941",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.sadar);
        ContactModel contactModel3 = new ContactModel("Mohammad Siraj", "Joye", "+91-9589740941",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.img_shadab);
        ContactModel contactModel4 = new ContactModel("Nizam Choudhary", "Bhadhal Ke agwan", "+91-9589740941",
                "FLat No 15, 16, Building No. 19, Rakshak Nagar Phase 1, Pune", R.drawable.img_shadab);
        ContactModel contactModel5 = new ContactModel("Haji Salim Lahori", "Lahori", "+91-9589740941",
                " Bangla No 786, Srinagar, Indore", R.drawable.tony);
        ContactModel contactModel6 = new ContactModel("Tony Chaudhary", "Bhadhal Ke agwan", "+91-9589740941",
                " Bangla No 786, Srinagar, Indore", R.drawable.tony);
        ContactModel contactModel7 = new ContactModel("Tony Chaudhary", "Sinodiya k Zindran", "+91-9589740941",
                " Bangla No 786, Srinagar, Indore", R.drawable.tony);

        mContactsList = new ArrayList<>();
        mContactsList.add(contactModel);
        mContactsList.add(contactModel1);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel3);
        mContactsList.add(contactModel4);
        mContactsList.add(contactModel5);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel6);
        mContactsList.add(contactModel7);
        mContactsList.add(contactModel2);
        mContactsList.add(contactModel2);

        return mContactsList;
    }
}
