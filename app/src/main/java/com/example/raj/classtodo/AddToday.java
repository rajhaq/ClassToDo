package com.example.raj.classtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.raj.classtodo.adapter.StudentAdapter;
import com.example.raj.classtodo.model.ListItem;
import com.example.raj.classtodo.model.StudentData;

import java.util.ArrayList;

public class AddToday extends AppCompatActivity implements StudentAdapter.ItemClickCallback {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    private RecyclerView recView;
    private StudentAdapter adapter;
    private ArrayList listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_today);
        listData = (ArrayList) StudentData.getListData();

        recView = (RecyclerView)findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudentAdapter(StudentData.getListData(), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);

    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //update our data
        if (item.isFavourite()){
            item.setFavourite(false);
        } else {
            item.setFavourite(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }

}
