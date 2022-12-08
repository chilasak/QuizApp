package com.quizapp.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class InstructionsDialog extends Dialog {

    private int instructionPoints = 0;

    public InstructionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instrutions_dialog_layout);

        final AppCompatButton continueBtn = findViewById(R.id.continueBtn);
        final TextView instructionsTV = findViewById(R.id.instructionsTV);

        setInstructionPoint(instructionsTV, "1. Bạn có thời gian 5 phút để trả lời câu hỏi. ");
        setInstructionPoint(instructionsTV, "2. Bạn sẽ nhận 1 điểm khi trả lời đúng .");

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setInstructionPoint(TextView instructionsTV, String instructionPoint){

        if(instructionPoints == 0){
            instructionsTV.setText(instructionPoint);
        }
        else{
            instructionsTV.setText(instructionsTV.getText()+"\n\n"+instructionPoint);
        }

        instructionPoints++;
    }
}
