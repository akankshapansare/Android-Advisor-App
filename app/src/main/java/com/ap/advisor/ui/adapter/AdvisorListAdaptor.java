package com.ap.advisor.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.advisor.R;
import com.ap.advisor.data.Advisor;

import java.util.List;

public class AdvisorListAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Advisor> advisorList;
    private LayoutInflater layoutInflater;
    private final AdvisorListAdaptorListener listener;

    public interface AdvisorListAdaptorListener {
        void onAdvisorSelected(Advisor advisor);
    }

    public AdvisorListAdaptor(Context context, List<Advisor> advisorList, AdvisorListAdaptorListener listener) {
        this.context = context;
        this.advisorList = advisorList;
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.advisor_list_item, parent, false);
        return new AdvisorListAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textViewAdvisorname.setText(advisorList.get(position).getName());
        ((ViewHolder) holder).textViewAdvisorType.setText(advisorList.get(position).getAdvisorType());
        ((ViewHolder) holder).initialsText.setText(String.valueOf(advisorList.get(position).getAdvisorType().charAt(0)));
    }

    @Override
    public int getItemCount() {
        return advisorList.size();
    }

    public void setAdvisorList(List<Advisor> advisorList) {
        this.advisorList = advisorList;
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView initialsText;
        TextView textViewAdvisorType;
        TextView textViewAdvisorname;

        ViewHolder(View itemView) {
            super(itemView);
            initialsText = (TextView) itemView.findViewById(R.id.initials_text);
            textViewAdvisorType = (TextView) itemView.findViewById(R.id.text_advisor_type);
            textViewAdvisorname = (TextView) itemView.findViewById(R.id.text_advisor_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAdvisorSelected(advisorList.get(getAdapterPosition()));
                }
            });
        }
    }
}
