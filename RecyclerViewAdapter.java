package com.example.listview_recyclerviewexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    //You need a list to hold all of the data that you want to show
    private ArrayList<Person> allPeople;
    Context mContext;

    //Constructor, this runs first when you make a new adapter. pass in everything you will need in this class
    //which must at least be the data and most of the time you'll need the context
    public RecyclerViewAdapter(ArrayList<Person> data, Context c){
        mContext = c;
        allPeople = data;
    }


    @NonNull
    @Override
    //This creates the view so inflate the correct xml file for one item in the rec view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this method creates what will be shown in this recycler view and defines all action for button clicks
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_one_item, parent, false);
        //call the view holder class to assign xml to java elements
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }

    @Override
    //this actually puts the data where it is supposed to go
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //this method puts the data from the list in this class into the correct spot in our layout
        //Put the data in the text views
        holder.txtName.setText( allPeople.get(position).getName() );
        //the age is an int so we have to convert it to a string to put it in the textview
        holder.txtAge.setText( String.valueOf( allPeople.get(position).getAge() ) );
        holder.txtColor.setText( allPeople.get(position).getEyeColor() );

        //if you click on the whole row
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked on index " + position + " in the recyclerview" , Toast.LENGTH_SHORT).show();
            }
        });
    }


    public Person getItem(int i){
        return allPeople.get(i);
    }

    @Override
    //*****REALLY IMPORTANT******
    //If this returns 0 it wont show any data. This returns how many row items should be shown
    //so it should always match the size of the data to show
    public int getItemCount() {
        return allPeople.size();
    }

    //view holder class helps keep the previously created views in memory so they load faster
    //this should be all the xml elements in your layout and it should map them to their java variables
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtAge,txtColor;
        ConstraintLayout row;
        public ViewHolder(@NonNull View view) {
            super(view);
            txtName = view.findViewById(R.id.txtRecName);
            txtAge = view.findViewById(R.id.txtRecAge);
            txtColor = view.findViewById(R.id.txtRecColor);
            row = view.findViewById(R.id.cnstRecRow);
        }
    }


}

