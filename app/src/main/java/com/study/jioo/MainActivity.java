package com.study.jioo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //멤버변수
    JiooInterface mJiooInterface;
    CalInterface mCalInterface;
    View.OnClickListener mKeyPadClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mJiooInterface = new JiooInterface() {
            @Override
            public void click(String firstNum, String secondNum) {
                ////곱셈
                int firstPosition = getDotPosition(firstNum);
                int secondPosition = getDotPosition(secondNum);

                Spinner operator = findViewById(R.id.operator);
                operator.getAdapter().getItem(2);
//                String a =operator.getPrompt().toString();
                double result = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);

                String pattern = "#.";
                if (firstPosition + secondPosition > 0) {
                    for (int i = 0; i < firstPosition + secondPosition; i++) {
                        pattern = pattern + "#";
                    }
                    ((TextView) findViewById(R.id.result)).setText(new DecimalFormat(pattern).format(result));
                } else {
                    ((TextView) findViewById(R.id.result)).setText(new DecimalFormat("#").format(result));

                }
            }
        };

        final EditText firstNum = findViewById(R.id.first_num);
        final EditText secondNum = findViewById(R.id.second_num);

        mCalInterface = new CalInterface() {
            @Override
            public void click(String btn) {
                if (firstNum.hasFocus()) {
                    firstNum.setText(getText(firstNum)+ btn + "");
                    firstNum.setSelection(getText(firstNum).length());
                } else if (secondNum.hasFocus()) {
                    secondNum.setText(getText(secondNum)+ btn + "");
                    secondNum.setSelection(getText(secondNum).length());
                }
            }
        };

        mKeyPadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getText((TextView) v).equals("")) {
                    mCalInterface.click(((TextView) v).getText().toString());
                }
            }
        };

//        one.setOnClickListener(mKeyPadClickListener);
        // 키패드 클릭리스너
        LinearLayout keyPad = findViewById(R.id.keypad_layout);
        for (int i = 0; i < keyPad.getChildCount(); i++) {
            for (int j = 0; j < ((LinearLayout) keyPad.getChildAt(i)).getChildCount(); j++) {
                TextView view = (TextView) ((LinearLayout) keyPad.getChildAt(i)).getChildAt(j);
                if (!getText(view).equals("")) {
                    view.setOnClickListener(mKeyPadClickListener);
                }
            }
        }

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 작성한 코드
                if (getText(firstNum).equals("") || getText(secondNum).equals("")) {//빈값이 넘어올때의 처리
                    Toast.makeText(getApplicationContext(), "값을 넣어주세요", Toast.LENGTH_SHORT).show();
                } else {
                    mJiooInterface.click(getText(firstNum), getText(secondNum));
                }
            }
        });
    }

    int getDotPosition(String num){
     return    num.length() -
                (num.lastIndexOf(".") == -1
                        ? num.length() : num.lastIndexOf("."));
    }

    String getText(TextView view) {
        if (view.getText() != null) {
            return view.getText().toString();
        }
        return "";
    }

    interface JiooInterface {
        void click(String firstNum, String secondNum);
    }

    interface CalInterface {
        void click(String btn);
    }

}
