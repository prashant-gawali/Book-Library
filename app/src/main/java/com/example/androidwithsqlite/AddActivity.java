package com.example.androidwithsqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText title, author, pages;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.title2);
        author = findViewById(R.id.author2);
        pages = findViewById(R.id.pages2);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText().toString())){
                    title.setError("Enter Book Title");
                }else if (TextUtils.isEmpty(author.getText().toString())){
                    author.setError("Enter Book Author");
                }else if (TextUtils.isEmpty(pages.getText().toString())){
                    pages.setError("Enter Book Pages");
                }else {
                    MyDatabaseHelper myDB=new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(title.getText().toString(),author.getText().toString(),Integer.valueOf(pages.getText().toString()));
                    title.setText(null);
                    author.setText(null);
                    pages.setText(null);
                    Intent intent=new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}