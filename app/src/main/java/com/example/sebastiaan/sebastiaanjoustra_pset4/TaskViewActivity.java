package com.example.sebastiaan.sebastiaanjoustra_pset4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class TaskViewActivity extends AppCompatActivity {

    EditText etTaskTitle;
    ArrayList<TodoItem> todoList;
    CheckBox checkBox;
    DBHelper helper;
    TodoItem todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get to-do item values
        Intent intent = this.getIntent();
        int index = intent.getIntExtra("todoIndex", 0);
        helper = new DBHelper(this);
        todoList = helper.read();
        todoItem = todoList.get(index);

        // Initialize components
        etTaskTitle = (EditText) findViewById(R.id.etTaskTitle);
        etTaskTitle.setText(todoItem.getTitle());
        etTaskTitle.setSelection(todoItem.getTitle().length());
        checkBox = (CheckBox) findViewById(R.id.cbCompleted);
        if(todoItem.isCompleted() == 1) {
            checkBox.setChecked(true);
        }

    }

    public void clickedDone(View view) {
        if(checkBox.isChecked()) {
           todoItem.setCompleted(1);
        } else {
            todoItem.setCompleted(0);
        }
        todoItem.setTitle(etTaskTitle.getText().toString());
        helper.update(todoItem);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickedDelete(View view) {
        helper.deleteRow(todoItem);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
