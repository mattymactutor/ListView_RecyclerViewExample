package com.example.listview_recyclerviewexample;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Whatever variable type you will need to get all the data for one row in the listview
//is what you pass into the ArrayAdapter<>
//For example, if you're only showing names then a String is the variable type you need to show all of your data
//ArrayAdapter<String>
//In this case we want to show all of the properties inside of a Person object so Person is the
//variable you will need to show all of your data
//ArrayAdapter<Person>
public class ListViewAdapter extends ArrayAdapter<Person> {

    //You need a list to hold all of the data that you want to show
    private ArrayList<Person> allPeople;
    Context mContext; //we need this to show the toast, you might not always need this

    //Using a viewholder makes the data load faster and scroll smoother
    //the viewholder actually saves the rows that were already created in a small cache
    //this will hold all the XML elements required for our one row layout
    private static class ViewHolder {
        TextView txtName,txtAge,txtColor;
        ConstraintLayout row; //in this case we want to be able to click on the whole row so we're going to use
        //an onclick listener on the whole row  YOU WONT ALWAYS NEED THIS
    }

    //Constructor, this runs first when you make a new adapter. pass in everything you will need in this class
    //which must at least be the data and most of the time you'll need the context
    public ListViewAdapter(ArrayList<Person> data, Context context) {
        //in the super call below use the XML layout that you want for each row
        super(context, R.layout.listview_one_item, data);
        this.allPeople = data;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        //should return the number of data points you are trying to show
        //most likely will always be the size of your data list
        return allPeople.size();
    }

    @Nullable
    @Override
    public Person getItem(int position) {
        //This will call getItem() on the list that is holding your data
        //if the list holding your data is a map you would have to change this to get the item from the map
        return super.getItem(position);
    }

    @Override
    //this is where android actually draws the row that we're trying to show
    public View getView(final int position, View view, ViewGroup parent) {
        // Get the data item for this position
        Person personForThisRowInTheListView = allPeople.get(position);

        ViewHolder viewHolder; //make viewholder for this row

        //Android will only fill up the screen with list view items
        //If you have 20 items and only 5 fit on the screen android will draw the first 5
        //and each row's data will get saved into their respective view holder (down below with setTag)
        //As we scroll down to other options they will be created and drawn too but as we scroll back
        //up to the top we dont need to re-inflate the first 5 list items that we already drawn earlier
        //so in the else down there we just point this viewholder to the one that was already made

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) { //if the current position of our listview hasnt been made yet
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listview_one_item, parent, false);
        } else {
            //the item that is supposed to be displayed here has already been drawn before so
            //just grab that one from memory instead of re-inflating above
            viewHolder = (ViewHolder) view.getTag();
        }

        //Assign the java elements to the xml elements
        viewHolder.txtName = view.findViewById(R.id.txtName);
        viewHolder.txtAge = view.findViewById(R.id.txtAge);
        viewHolder.txtColor = view.findViewById(R.id.txtColor);
        viewHolder.row = view.findViewById(R.id.cnstRow);

        //Put the data in the text views
        viewHolder.txtName.setText( personForThisRowInTheListView.getName() );
        //the age is an int so we have to convert it to a string to put it in the textview
        viewHolder.txtAge.setText( String.valueOf( personForThisRowInTheListView.getAge() ) );
        viewHolder.txtColor.setText( personForThisRowInTheListView.getEyeColor() );

        //if you click on the whole row
        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked on index " + position + " in the listview" , Toast.LENGTH_SHORT).show();
            }
        });

        //store the data into this view holder
        view.setTag(viewHolder); //save this view to cache so in the future we can just reference it instead of remaking
        // Return the completed view to render on screen
        return view;
    }



}

