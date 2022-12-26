package com.quizapp.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class xem_kq extends AppCompatActivity {
    private List<QuestionsList> questionsLists = new ArrayList<>();
    private RelativeLayout option1Layout, option2Layout, option3Layout, option4Layout;
    private TextView option1TV, option2TV, option3TV, option4TV;
    private ImageView option1Icon, option2Icon, option3Icon, option4Icon;
    private AppCompatButton next,back;
    private TextView currentQuestion;

    private  TextView questionTV;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_kq);

        questionsLists = (List<QuestionsList>) getIntent().getSerializableExtra("quetions");
        next = findViewById(R.id.NextBtn);
        back = findViewById(R.id.BackBtn);

        currentQuestion = findViewById(R.id.currentQuestionTV);

        option1Layout = findViewById(R.id.option1Layout);
        option2Layout = findViewById(R.id.option2Layout);
        option3Layout = findViewById(R.id.option3Layout);
        option4Layout = findViewById(R.id.option4Layout);

        option1TV = findViewById(R.id.option1TV);
        option2TV = findViewById(R.id.option2TV);
        option3TV = findViewById(R.id.option3TV);
        option4TV = findViewById(R.id.option4TV);

        option1Icon = findViewById(R.id.option1Icon);
        option2Icon = findViewById(R.id.option2Icon);
        option3Icon = findViewById(R.id.option3Icon);
        option4Icon = findViewById(R.id.option4Icon);

        questionTV = findViewById(R.id.questionTV);

        if(i==0){
            Check(i);
            back.setClickable(false);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i==10){

                    finishQuiz();
                }
                Check(i);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   i--;
                if(i==-1){

                    finishQuiz();
                }

                   Check(i);

            }
        });











    }
    private void Check(int i)
    {
        resetOptions();
        currentQuestion.setText("Câu hỏi "+(i+1));
        questionTV.setText(questionsLists.get(i).getQuestion());
        option1TV.setText(questionsLists.get(i).getOption1());
        option2TV.setText(questionsLists.get(i).getOption2());
        option3TV.setText(questionsLists.get(i).getOption3());
        option4TV.setText(questionsLists.get(i).getOption4());
        int getUserSelectedOption = questionsLists.get(i).getUserSelectedAnswer(); // get user selected option
        int getQuestionAnswer = questionsLists.get(i).getAnswer();

        if(getUserSelectedOption == 1 && getUserSelectedOption==getQuestionAnswer){
            selectOption(option1Layout, option1Icon);
        }
        else if(getUserSelectedOption == 2  && getUserSelectedOption==getQuestionAnswer){
            selectOption(option2Layout, option2Icon);
        }
        else if(getUserSelectedOption == 3  && getUserSelectedOption==getQuestionAnswer){
            selectOption(option3Layout, option3Icon);
        }
        else if( getUserSelectedOption == 4 && getUserSelectedOption==getQuestionAnswer){
            selectOption(option4Layout, option4Icon);
        }

         if(getUserSelectedOption == 1 && getUserSelectedOption!=getQuestionAnswer){
            UserselectOption(option1Layout, option1Icon);
        }
        else if(getUserSelectedOption == 2 && getUserSelectedOption!=getQuestionAnswer ){
            UserselectOption(option2Layout, option2Icon);
        }
        else if(getUserSelectedOption == 3  && getUserSelectedOption!=getQuestionAnswer){
            UserselectOption(option3Layout, option3Icon);
        }
        else if( getUserSelectedOption == 4 && getUserSelectedOption!=getQuestionAnswer){
            UserselectOption(option4Layout, option4Icon);
        }

         if(getQuestionAnswer == 1 ){
            selectOption(option1Layout, option1Icon);
        }
        else if(getQuestionAnswer == 2 ){
            selectOption(option2Layout, option2Icon);
        }
        else if(getQuestionAnswer == 3 ){
            selectOption(option3Layout, option3Icon );
        }
        else if (getQuestionAnswer == 4 ){
            selectOption(option4Layout, option4Icon);
        }
    }
    private void resetOptions(){

        option1Layout.setBackgroundResource(R.drawable.round_back_white50_10);
        option2Layout.setBackgroundResource(R.drawable.round_back_white50_10);
        option3Layout.setBackgroundResource(R.drawable.round_back_white50_10);
        option4Layout.setBackgroundResource(R.drawable.round_back_white50_10);

        option1Icon.setImageResource(R.drawable.round_back_white50_100);
        option2Icon.setImageResource(R.drawable.round_back_white50_100);
        option3Icon.setImageResource(R.drawable.round_back_white50_100);
        option4Icon.setImageResource(R.drawable.round_back_white50_100);
    }
    private void selectOption(RelativeLayout selectedOptionLayout, ImageView selectedOptionIcon){

        // reset options to select new option


        selectedOptionIcon.setImageResource(R.drawable.check_icon);
        selectedOptionLayout.setBackgroundResource(R.drawable.round_back_selected_option);
    }
    private void UserselectOption(RelativeLayout selectedOptionLayout, ImageView selectedOptionIcon){

        // reset options to select new option


        selectedOptionIcon.setImageResource(R.drawable.ic_remove_24);
        selectedOptionLayout.setBackgroundResource(R.drawable.round_back_sl);
    }
    private void finishQuiz(){

        // creating intent to open QuizResult activity
        Intent intent = new Intent(xem_kq.this, ketqua.class);

        // creating bundle to pass quizQuestionsLists
        Bundle bundle = new Bundle();
        bundle.putSerializable("quetions", (Serializable) questionsLists);

        // add bundle to intent
        intent.putExtras(bundle);

        // start activity to open QuizResult activity
        startActivity(intent);

        // destroy current activity
        finish();
    }

}