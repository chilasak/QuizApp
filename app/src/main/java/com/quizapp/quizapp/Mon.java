package com.quizapp.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Mon extends AppCompatActivity {
    private String selectedTopicName = "";
    private LinearLayout mon1,mon2,mon3,mon4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);

        mon1 = findViewById(R.id.mon1);
        mon2 = findViewById(R.id.mon2);
        mon3 = findViewById(R.id.mon3);
        mon4 = findViewById(R.id.mon4);

        final Button startquiz = findViewById(R.id.startQuiz);

        mon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTopic("FOSS", view);
            }
        });

        mon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTopic("thietkeweb", view);
            }
        });

        mon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTopic("questions", view);
            }
        });

        mon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTopic("triethoc", view);
            }
        });

        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTopicName.isEmpty()) {
                    Toast.makeText(Mon.this, "Bạn chưa chọn môn !", Toast.LENGTH_SHORT).show();
                } else{
                    // Create an Object of Intent to open quiz questions screen
                    final Intent intent = new Intent(Mon.this, MainActivity.class);

                    //put user entered name and selected topic name to intent for use in next activity
                    intent.putExtra("selectedTopic", selectedTopicName);

                    // call startActivity to open next activity along with data(userName, selectedTopicName)
                    startActivity(intent);

                    finish();
                }
            }
        });
    }
    private void chooseTopic(String topicName, View topicLayout) {


        selectedTopicName = topicName;


        deSelectAllLayouts();


        topicLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);
    }

    private void deSelectAllLayouts() {

        mon1.setBackgroundResource(R.drawable.round_back_white_10);
        mon2.setBackgroundResource(R.drawable.round_back_white_10);
        mon3.setBackgroundResource(R.drawable.round_back_white_10);
        mon4.setBackgroundResource(R.drawable.round_back_white_10);

    }
}


