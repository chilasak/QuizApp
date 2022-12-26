package com.quizapp.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
                startActivity(new Intent(ketqua.this, MainActivity.class));
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