package com.ap.advisor.ui.adapter;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.advisor.R;

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    Cursor dataCursor;

    public ContactListAdapter(Activity mContext) {
        context = mContext;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contacts_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    public void setCursor(Cursor cursor) {
        this.dataCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        dataCursor.moveToPosition(position);

        String name = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        ((ItemViewHolder) holder).contactName.setText(name);
        ((ItemViewHolder) holder).initial.setText(name);
    }

    @Override
    public int getItemCount() {
        return dataCursor == null ? 0 : dataCursor.getCount();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView initial;
        private TextView contactName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.text_advisor_name);
            initial = (TextView) itemView.findViewById(R.id.initials_text);
        }
    }
}
