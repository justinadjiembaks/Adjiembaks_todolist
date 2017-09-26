package com.example.sebastiaan.sebastiaanjoustra_pset4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etTodo = (EditText) findViewById(R.id.etAddItem);
        btAdd = (Button) findViewById(R.id.btAddItem);
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
