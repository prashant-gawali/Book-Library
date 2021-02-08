package com.example.androidwithsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText book_title, book_author, book_pages, book_id;
    Button updateButton, deleteButton;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        book_title = findViewById(R.id.title2);
        book_author = findViewById(R.id.author2);
        book_pages = findViewById(R.id.pages2);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);


        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(book_title.getText().toString())) {
                    book_title.setError("Book Title is Empty");
                } else if (TextUtils.isEmpty(book_author.getText().toString())) {
                    book_author.setError("Book Author is Empty");
                } else if (TextUtils.isEmpty(book_pages.getText().toString())) {
                    book_pages.setError("Pages is Empty");
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                    myDB.updateData(id, book_title.getText().toString(), book_author.getText().toString(), book_pages.getText().toString());
                }
                book_title.setText("");
                book_author.setText("");
                book_pages.setText("");
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {

//            Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

//            Setting Intent Data
            book_title.setText(title);
            book_author.setText(author);
            book_pages.setText(pages);


        } else {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }

    }

    void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete" + title + "?");
        alertDialog.setMessage("Are u want to Delete " + title + " ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MyDatabaseHelper mtDB = new MyDatabaseHelper(UpdateActivity.this);
                mtDB.deleteOneRow(id);
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        alertDialog.create().show();

    }
}