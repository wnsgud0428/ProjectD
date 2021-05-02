package com.example.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBarInMenu;
    EditText commentInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBarInMenu = findViewById(R.id.ratingBarInMenu);
        commentInput = findViewById(R.id.commentInput);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });
    }

    public void returnToMain(){
        String comment = commentInput.getText().toString();
        float rating = ratingBarInMenu.getRating();
        Intent newIntent = new Intent();
        newIntent.putExtra("rating", rating);
        newIntent.putExtra("comment", comment);

        setResult(RESULT_OK, newIntent); //이전 activity(메인화면) 으로 intent 전달

        finish();

    }
}