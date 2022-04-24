package com.example.listview_recyclerviewexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //To display custom data you need 3 things
    //Data to Show    - Would be saved in the activity that will show this data (in this case MainActivity.java)
    //an adapter that tells the app where to put what data
    //An xml element to show the data - this element would be in the xml for this activity
    //FOR RECYCLCER VIEW ONLY
    //give the recycler view the appropriate layout (vertical or horizontal or grid)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make some data to show
        ArrayList<Person> dataToShowInList = new ArrayList<>();
        dataToShowInList.add(new Person("Bob","Blue",20));
        dataToShowInList.add(new Person("Sally","Green",20));
        dataToShowInList.add(new Person("Jim","Brown",20));

        //make the adapter that will translate the data into the listview/recycler
        //In this example we are showing a Toast when you click on an item and that requires the Context
        //You wont always need to pass the context into your adapter class
        Context c = MainActivity.this; //you have to pass in the context where this adapter will be living which is on our main activity
        ListViewAdapter listViewAdapter = new ListViewAdapter(dataToShowInList,c);
        //Get the listview xml
        ListView listView = findViewById(R.id.lstListView);
        //set the listview to use the adapter we created
        listView.setAdapter(listViewAdapter);

        //make the adapter for the rec view
        RecyclerViewAdapter recViewAdp = new RecyclerViewAdapter(dataToShowInList,c);
        //get the rec view xml
        RecyclerView recyclerView = findViewById(R.id.recRecView);
        //set the recview to use the adapter we created
        recyclerView.setAdapter(recViewAdp);
        //recycler views need a layout manager which can be used to make grids and/or decide the
        //scroll orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        //FIND THE GRID LAYOUT EXAMPLE
        layoutManager.scrollToPosition(0); //start to the top
        recyclerView.setLayoutManager(layoutManager);

        //Grid
        //GridLayoutManager grid = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(grid);

    }
}