package com.example.shanna.orbital2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//no need anymore but just keep in case

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ReqViewHolder> {
    Context context;
    ArrayList<String> fromList;
    ArrayList<String> titleList;
    ArrayList<String> payList;
    ArrayList<String> durationList;
    ArrayList<String> maxChangesList;
    ArrayList<String> waitList;
    ArrayList<String> reqDateList;
    ArrayList<String> fromIDList;

    class ReqViewHolder extends RecyclerView.ViewHolder {

        TextView from, title;

        public ReqViewHolder(View itemView) {
            super(itemView);
            from = (TextView) itemView.findViewById(R.id.SenderName);
            title = (TextView) itemView.findViewById(R.id.ProjectTitle);
            // owner = (TextView) itemView.findViewById(R.id.search_owner);
        }
    }

    public RequestAdapter(Context context, ArrayList<String> from, ArrayList<String> title,
        ArrayList<String> pay, ArrayList<String> duration, ArrayList<String> maxChanges,
                          ArrayList<String> wait, ArrayList<String> reqDate, ArrayList<String> fromID) {
        this.context = context;
        this.fromList = from;
        this.payList = pay;
        this.durationList = duration;
        this.maxChangesList = maxChanges;
        this.waitList = wait;
        this.reqDateList = reqDate;
        this.fromIDList = fromID;

    }

    //When the Adapter creates its first item, onCreateViewHolder is called.
    // This is what allows the Adapter to reuse a reference to a view instead of
    // re-inflating it. Typically this implementation will just
    // inflate a view and return a ViewHolder object.
    public RequestAdapter.ReqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_collab_req_list, parent, false);
        return new RequestAdapter.ReqViewHolder(view);
    }


    //onBindViewHolder() is called for each and every item and
    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ReqViewHolder holder, int position) {
        holder.title.setText(titleList.get(position));
        holder.from.setText(fromList.get(position));
        //holder.owner.setText(ownerList.get(position));

        //if got time, can look into loading images here.

        final String title = titleList.get(position);
        final String ownerId = fromIDList.get(position);
        final String pay = payList.get(position);
        final String duration = durationList.get(position);
        final String maxChanges = maxChangesList.get(position);
        final String wait = waitList.get(position);
        final String reqDate = reqDateList.get(position);
        final String ownerName = fromList.get(position);


        //  Toast.makeText(context,"id is "+ownerId, Toast.LENGTH_LONG).show();
        //  Toast.makeText(context,"Title is "+ title, Toast.LENGTH_LONG).show();

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //  Toast.makeText(context,"Loading ", Toast.LENGTH_LONG).show();
                Intent profileIntent = new Intent(context, AcceptRequest.class);
                profileIntent.putExtra("pay", pay);
                profileIntent.putExtra("duration", duration);
                profileIntent.putExtra("bufferWait", wait);
                profileIntent.putExtra("maxChanges", maxChanges);
                profileIntent.putExtra("requestDate", reqDate);
                profileIntent.putExtra("partnerID", ownerId); //project requester id
                profileIntent.putExtra("senderFullName", ownerName);
                profileIntent.putExtra("projectTitle", title);
                context.startActivity(profileIntent);

/*
                final String project_title = getIntent().getStringExtra("Title");
                final String project_owner_id = getIntent().getStringExtra("Owner");
*/
            }
        });


    }

    public int getItemCount() {
        return titleList.size();
    }
}
