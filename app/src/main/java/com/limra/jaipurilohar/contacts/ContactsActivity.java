package com.limra.jaipurilohar.contacts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import com.limra.jaipurilohar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.limra.jaipurilohar.dao.AppDataBase;
import com.limra.jaipurilohar.dao.SearchModel;
import com.limra.jaipurilohar.dao.User;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.contactsRecyclerView)
    RecyclerView contactsRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private List<User> mContactsList;
    private SearchModel mSearchModel;
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mContactsList = getContactsList();
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter contactsAdapter = new ContactsAdapter(mContactsList, this);
        contactsRecyclerView.setAdapter(contactsAdapter);
    }

    private List<User> getContactsList() {
        AppDataBase db = AppDataBase.getAppDatabase(this);
        Bundle data = getIntent().getExtras();
        SearchModel searchModel = (SearchModel) data.getParcelable("search_bundle");
        if(searchModel != null){
            String name = searchModel.getName();
            String gotra = searchModel.getGotra();
            String city = searchModel.getCity();
            if(TextUtils.isEmpty(name) && TextUtils.isEmpty(gotra)){
                mContactsList = db.userDao().getSearchedContactsFromCity(city);
            }else if(TextUtils.isEmpty(name) && TextUtils.isEmpty(city)){
                mContactsList = db.userDao().getSearchedContactsFromGotra(gotra);
            }else if(TextUtils.isEmpty(city) && TextUtils.isEmpty(gotra)){
                mContactsList = db.userDao().getSearchedContactsFromName(name);
            }else if(TextUtils.isEmpty(city)){
                mContactsList = db.userDao().getSearchedContactsFromGotraName(gotra, name);
            }else if(TextUtils.isEmpty(gotra)){
                mContactsList = db.userDao().getSearchedContactsFromNameCity(city, name);
            }else if(TextUtils.isEmpty(name)){
                mContactsList = db.userDao().getSearchedContactsFromGotraCity(gotra, city);
            } else {
                mContactsList = db.userDao().getSearchedContacts(gotra, city, name);
            }
        }else{
            mContactsList = db.userDao().getAll();
        }
        return mContactsList;
    }
}
