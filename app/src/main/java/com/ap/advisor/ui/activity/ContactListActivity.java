package com.ap.advisor.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.ap.advisor.R;
import com.ap.advisor.networking.FirebaseService;
import com.ap.advisor.ui.adapter.ContactListAdapter;

import javax.inject.Inject;

public class ContactListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    @Inject
    FirebaseService firebaseService;

    public static void start(Context context) {
        Intent intent = new Intent(context, ContactListActivity.class);
        context.startActivity(intent);
    }

    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.DISPLAY_NAME
    };

    private final static int[] TO_IDS = {
            android.R.id.text1,
            R.id.initials_text
    };

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME,
    };

    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            ContactsContract.Contacts.DISPLAY_NAME + " != '' AND "
                    + ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1 AND "
                    + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL";

    private ContactListAdapter contactListAdpater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactListAdpater = new ContactListAdapter(this);
        recyclerView.setAdapter(contactListAdpater);

        // Initializes the loader
        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Put the result Cursor in the adapter for the ListView
        contactListAdpater.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        contactListAdpater.setCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }
}
