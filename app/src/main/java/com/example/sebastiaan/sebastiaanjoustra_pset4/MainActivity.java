package com.example.sebastiaan.sebastiaanjoustra_pset4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@@@MainActivity";
    DBHelper helper;
    ListView lvItems;
    ArrayList<TodoItem> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        lvItems = (ListView) findViewById(R.id.lvItems);

        setAdapter();

        // Create listener on the listview
        lvItems.setOnItemClickListener(new ItemClickListener());

    }

    // Create adapter for the listview
    public void setAdapter() {
        helper = new DBHelper(this);
        todoList = helper.read();
        ListAdapter adapter = new TodoListAdapter(this, todoList);
        lvItems.setAdapter(adapter);
    }

    public class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.v(TAG, todoList.get(i).getTitle());
            System.out.println(todoList.get(i).getId());
//
            Intent intent = new Intent(getApplicationContext(), TaskViewActivity.class);
            intent.putExtra("todoIndex", i);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.menuAddItem) {
//            setAdapter();
//        }

        return super.onOptionsItemSelected(item);
    }
}
