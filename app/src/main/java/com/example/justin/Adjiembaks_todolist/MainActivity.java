package com.example.justin.Adjiembaks_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DBHelper helper;
    ListView lvItems;
    ArrayList<TodoItem> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.justin.Adjiembaks_todolist.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.example.justin.Adjiembaks_todolist.R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.justin.Adjiembaks_todolist.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewItemActivity.class);
                startActivity(intent);
            }
        });

        lvItems = (ListView) findViewById(com.example.justin.Adjiembaks_todolist.R.id.lvItems);

        setAdapter();

        lvItems.setOnItemClickListener(new ItemClickListener());

    }

    // pressing back button closes app
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

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
        getMenuInflater().inflate(com.example.justin.Adjiembaks_todolist.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
