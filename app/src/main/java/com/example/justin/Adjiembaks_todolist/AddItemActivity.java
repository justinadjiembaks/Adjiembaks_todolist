package com.example.justin.Adjiembaks_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    EditText etTodo;
    Button btAdd;
    TodoItem todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.justin.Adjiembaks_todolist.R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(com.example.justin.Adjiembaks_todolist.R.id.toolbar);
        setSupportActionBar(toolbar);

        etTodo = (EditText) findViewById(com.example.justin.Adjiembaks_todolist.R.id.etAddItem);
        btAdd = (Button) findViewById(com.example.justin.Adjiembaks_todolist.R.id.btAddItem);
    }

    public void addItem(View view) {
        todoItem = new TodoItem(etTodo.getText().toString());
        DBHelper helper = new DBHelper(this);
        helper.addRow(todoItem);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
