package com.quizapp.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ketqua extends AppCompatActivity {
    private List<QuestionsList> questionsLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        final TextView scoreTV = findViewById(R.id.scoreTV);
        final TextView totalScoreTV = findViewById(R.id.totalScoreTV);
        final TextView correctTV = findViewById(R.id.correctTV);
        final TextView incorrectTV = findViewById(R.id.inCorrectTV);
        final AppCompatButton shareBtn = findViewById(R.id.XemBtn);
        final AppCompatButton reTakeBtn = findViewById(R.id.reTakeQuizBtn);
        final TextView name = findViewById(R.id.name);


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //  Toast.makeText(this, "" + uid, Toast.LENGTH_SHORT).show();
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference zone1Ref = zonesRef.child(uid);
        zone1Ref.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               final String u = snapshot.getValue(String.class);
                name.setText("chúc mưng "+u);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ketqua.this, "Failed to get data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });



        questionsLists = (List<QuestionsList>) getIntent().getSerializableExtra("quetions");

        totalScoreTV.setText("/"+questionsLists.size());
        scoreTV.setText(getCorrectAnswers() +"");
        correctTV.setText(getCorrectAnswers() + "");
        incorrectTV.setText(String.valueOf(questionsLists.size() - getCorrectAnswers()));

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ketqua.this,xem_kq.class);

                // creating bundle to pass quizQuestionsLists
                Bundle bundle = new Bundle();
                bundle.putSerializable("quetions", (Serializable) questionsLists);

                // add bundle to intent
                intent.putExtras(bundle);

                // start activity to open QuizResult activity
                startActivity(intent);
            }
        });

        reTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // re start quiz go to MainActivity
                startActivity(new Intent(ketqua.this, Mon.class));
                finish();
            }
        });

    }
    private int getCorrectAnswers(){

        int correctAnswer = 0;

        for(int i = 0; i < questionsLists.size(); i++){

            int getUserSelectedOption = questionsLists.get(i).getUserSelectedAnswer(); // get user selected option
            int getQuestionAnswer = questionsLists.get(i).getAnswer(); //  get correct answer for the question

            // check of user selected answer matches with correct answer
            if(getQuestionAnswer == getUserSelectedOption){
                correctAnswer++; //  increase correct answers
            }
        }

        return correctAnswer;
    }
}