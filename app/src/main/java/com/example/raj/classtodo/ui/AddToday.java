package com.example.raj.classtodo.ui;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.adapter.StudentAdapter;
import com.example.raj.classtodo.model.ListItem;
import com.example.raj.classtodo.model.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddToday extends AppCompatActivity implements StudentAdapter.ItemClickCallback {
    public static StudentHelper studentDB;
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_1 = "EXTRA_1";
    private static final String EXTRA_2 = "EXTRA_2";
    private static final int[] icons = {R.drawable.student01,R.drawable.student02,R.drawable.student03,R.drawable.student04,R.drawable.student05,
            R.drawable.student06,R.drawable.student07,R.drawable.student08,R.drawable.student09,R.drawable.student10};


    private RecyclerView recView;
    private StudentAdapter adapter;
    private ArrayList listData;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
    String strDate = sdf.format(cal.getTime());
    String[] values = strDate.split("/",0);
    int dbv=Integer.parseInt(values[0]);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_today);
////database this

        studentDB=new StudentHelper(this, dbv);

        listData = (ArrayList) this.getListData();

        recView = (RecyclerView)findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudentAdapter(this.getListData(), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

/*        Cursor res = studentDB.getAllData();
        int x= Integer.parseInt(values[2])+4;
        ListItem item = (ListItem) listData.get(p);
        if ("1" == res.getString(x)){
            item.setFavourite(false);
            Toast.makeText(AddToday.this,"Star empty",Toast.LENGTH_SHORT).show();
        } else {
            item.setFavourite(true);
            Toast.makeText(AddToday.this,"Star fill",Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.actionbar_add_student,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barSms:
                startActivity(new Intent(this,Sms.class));
                return true;

            case R.id.barAddStudent:
                startActivity(new Intent(this,EditStudent.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        extras.putString(EXTRA_1, item.getId());
        extras.putString(EXTRA_2, item.getMobile());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);

    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //update our data
        // res.getPosition()==1, int= values[2]+4;,Cursor res = studentDB.getAllData();
        if (item.isFavourite()){
            item.setFavourite(false);
            String x=studentDB.updateData2(item.getId(),values[2]);
            Toast.makeText(AddToday.this,x,Toast.LENGTH_LONG).show();
        } else {
            item.setFavourite(true);
            String x=studentDB.updateData(item.getId(),values[2],"1");
            Toast.makeText(AddToday.this,x,Toast.LENGTH_LONG).show();
        }


        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }
    public void setFav() {



        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }
    public static List<ListItem> getListData() {


        Cursor res = studentDB.getAllData();
        java.util.List<ListItem> data = new ArrayList<>();

        //Repeat process 4 times, so that we have enough data to demonstrate a scrollable
        //RecyclerView
        for (int x = 0; x < 4; x++) {
            //create ListItem with dummy data, then add them to our List
            for (int i = 0; res.moveToNext(); i++) {
                ListItem item = new ListItem();
                item.setImageResId(icons[i%10]);
                item.setTitle(res.getString(2));
                item.setSubTitle(res.getString(1));
                item.setId(res.getString(0));
                item.setMobile(res.getString(3));

                data.add(item);
            }
        }
        return data;
    }

}
