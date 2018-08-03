package com.example.shanna.orbital2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {
    /*RequestFragment.Request req;

    public RequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        req = new RequestFragment.Request();
    }

    public class Request extends AppCompatActivity {
        private DatabaseReference mDatabaseRef;
        private FirebaseUser firebaseUser;
        private ArrayList<String> titleList;
        private ArrayList<String> fromList;
        private ArrayList<String> payList;
        private ArrayList<String> durationList;
        private ArrayList<String> maxChangesList;
        private ArrayList<String> waitList;
        private ArrayList<String> reqDateList;
        private ArrayList<String> fromIDList;
        private RequestAdapter reqAdapter;

        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        public Request() {
            // empty constructor
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_request_fragment);

            recyclerView = findViewById(R.id.Request_listView);

            titleList = new ArrayList<>();
            fromList = new ArrayList<>();
            payList = new ArrayList<>();
            durationList = new ArrayList<>();
            maxChangesList = new ArrayList<>();
            waitList = new ArrayList<>();
            reqDateList = new ArrayList<>();
            fromIDList = new ArrayList<>();

            mDatabaseRef = FirebaseDatabase.getInstance().getReference();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            recyclerView.setHasFixedSize(true);

            // use linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            Toast.makeText(RequestFragment.Request.this, "here :)", Toast.LENGTH_SHORT);
            setUpAdapter();
        }

        private void setUpAdapter() {
            // fill up lists with needed data
            final DatabaseReference mRef;
            mRef = FirebaseDatabase.getInstance().getReference().child("AllCollabsReq").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // clear list for every new search (database is async)
                    fromList.clear();
                    titleList.clear();
                    payList.clear();
                    durationList.clear();
                    maxChangesList.clear();
                    waitList.clear();
                    reqDateList.clear();
                    fromIDList.clear();
                    recyclerView.removeAllViews();

                    //final int counter = 0;
                    //Toast.makeText(SearchBar.this, s, Toast.LENGTH_SHORT).show();

                    for (DataSnapshot innerSnapshot : dataSnapshot.getChildren()) {
                        if (innerSnapshot.child("ProjectStatus").getValue().toString().equals("open")) {
                            fromList.add(innerSnapshot.child("OwnerFullName").getValue().toString());
                            titleList.add(innerSnapshot.child("Title").getValue().toString());
                            payList.add(innerSnapshot.child("Pay").getValue().toString());
                            durationList.add(innerSnapshot.child("Duration").getValue().toString());
                            maxChangesList.add(innerSnapshot.child("MaxChanges").getValue().toString());
                            waitList.add(innerSnapshot.child("BufferWait").getValue().toString());
                            reqDateList.add(innerSnapshot.child("DateOfRequest").getValue().toString());
                            fromIDList.add(innerSnapshot.child("OwnerID").getValue().toString());

                            reqAdapter = new RequestAdapter(RequestFragment.Request.this, fromList, titleList,
                                    payList, durationList, maxChangesList, waitList, reqDateList, fromIDList);
                            recyclerView.setAdapter(reqAdapter);
                        }
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }*/

    private RecyclerView mSenderList;
    String current_id;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_request_fragment, container, false);
       // View view = inflater.inflate(R.layout.activity_users__projects_list, container, false);

        mSenderList = (RecyclerView) view.findViewById(R.id.Request_listView);
       // mSenderList = (RecyclerView) view.findViewById(R.id.projects_list);
        mSenderList.setHasFixedSize(true);
        current_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mSenderList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        startListening();
    }

    public void startListening(){

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("AllCollabsReq")
                .child(current_id)
                .orderByChild("ProjectStatus")
                .equalTo("Open");

        FirebaseRecyclerOptions<AllCollabsReq> options =
                new FirebaseRecyclerOptions.Builder<AllCollabsReq>()
                        .setQuery(query, AllCollabsReq.class)
                        .build();

       // Toast.makeText(getContext(), "id is " + current_id, Toast.LENGTH_SHORT).show();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<AllCollabsReq, RequestFragment.ReqViewHolder>(options) {
            @Override
            public RequestFragment.ReqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_collab_req_list, parent, false);
                   //     .inflate(R.layout.users_project, parent, false);


                return new RequestFragment.ReqViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ReqViewHolder holder, int position, AllCollabsReq model) {
                // Bind the Chat object to the ChatHolder

                holder.setTitle(model.getTitle());
                //final String sender_id = model.getPartner();
                final String sender_name = model.getSenderFullName();


                //final String sender_name = FirebaseDatabase.getInstance().getReference().child("Users").child(sender_id).child("FullName").toString();
                holder.setSenderFullName(sender_name);

                final String pay = model.getPay();
                final String duration = model.getDuration();
                final String wait = model.getBufferWait();
                final String maxChanges = model.getMaxChanges();
                final String requestDate = model.getDateOfRequest();
                final String partner = model.getPartner();
                final String senderFullName = sender_name;
                final String project_title = model.getTitle();

                //Toast.makeText(getContext(),"For debugging title is "+ project_title, Toast.LENGTH_LONG).show();

                holder.mView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent profileIntent = new Intent(getContext(), AcceptRequest.class);
                        profileIntent.putExtra("pay", pay);
                        profileIntent.putExtra("duration", duration);
                        profileIntent.putExtra("bufferWait", wait);
                        profileIntent.putExtra("maxChanges", maxChanges);
                        profileIntent.putExtra("requestDate", requestDate);
                        profileIntent.putExtra("partnerID", partner); //project requester id
                        profileIntent.putExtra("senderFullName", senderFullName);
                        profileIntent.putExtra("projectTitle", project_title);
                        startActivity(profileIntent);
                    }
                });




            }

        };
        mSenderList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ReqViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public ReqViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView titleView = mView.findViewById(R.id.ProjectTitle);
           // TextView titleView = (TextView) mView.findViewById(R.id.textViewTitle);
            titleView.setText("PROJECT TITLE: " + title);
        }
        public void setSenderFullName(String sender){
            TextView aboutView = mView.findViewById(R.id.SenderName);
            //TextView aboutView = (TextView) mView.findViewById(R.id.textViewProjectSummary);
            aboutView.setText("YOU HAVE A REQUEST FROM: " + sender +"\n");
        }
    }

}



