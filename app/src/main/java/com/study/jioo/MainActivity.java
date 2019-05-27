package com.study.jioo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    int N =0;
    double mCount=0;
    double zero=0;
    JiooInterface mJiooInterface;
    CalInterface mCalInterface;

    //멤버변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



            mJiooInterface = new JiooInterface() {
                @Override
                public void click(String firstNum, String secondNum) {
 ////곱셈


                int firstPostion = firstNum.length() -
                        (firstNum.lastIndexOf(".")==-1
                        ? firstNum.length() : firstNum.lastIndexOf("."));

                int secondPostion = secondNum.length()
                        - (secondNum.lastIndexOf(".")==-1
                        ? secondNum.length() : secondNum.lastIndexOf("."));

                Spinner operator = findViewById(R.id.operator);
                operator.getAdapter().getItem(2);
//                String a =operator.getPrompt().toString();
                double result = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);

                String pattern = "#.";
                if(firstPostion+secondPostion >0) {
                    for (int i = 0; i < firstPostion + secondPostion; i++) {
                        pattern = pattern + "#";
                    }
                    ((TextView) findViewById(R.id.result)).setText(new DecimalFormat(pattern).format(result));
                }else{
                    ((TextView) findViewById(R.id.result)).setText(new DecimalFormat("#").format(result));

                }

//                  mCount = firstNum * secondNum;
//                    String num_1 = Double.toString(firstNum);
//                    String num_2 = Double.toString(secondNum);
//
//
//                        if (num_1.contains(".") && num_2.contains(".")) {
//                            N = num_1.length() - num_1.indexOf(".")
//
//                                    + num_2.length() - num_2.indexOf(".");
//                            DecimalFormat form = new DecimalFormat("#.#*N");
//                            String mCount1 = form.format( mCount );
//                            mCount=Double.valueOf(mCount1);
//                            mCount+=1;

//                        }



                }





        };

        Button button = findViewById(R.id.click);
        final EditText edit1=findViewById(R.id.first_num);
        final EditText edit2=findViewById(R.id.second_num);
        TextView one = findViewById(R.id.one);
        TextView two = findViewById(R.id.two);
        TextView three = findViewById(R.id.three);
        TextView four = findViewById(R.id.four);
        TextView five = findViewById(R.id.five);
        TextView six = findViewById(R.id.six);
        TextView seven = findViewById(R.id.seven);
        TextView eight = findViewById(R.id.eight);
        TextView nine = findViewById(R.id.nine);

        TextView dot = findViewById(R.id.dot);

        mCalInterface = new CalInterface() {
            @Override
            public void click(int btn) {
                EditText first =findViewById(R.id.first_num);
                EditText second =findViewById(R.id.second_num);


                if(first.hasFocus()) {
                    first.setText(first.getText().toString() + btn + "");
                    first.setSelection(first.getText().length());
                } else if(second.hasFocus()){
                    second.setText(second.getText().toString() + btn + "");
                    second.setSelection(second.getText().length());

                }

//                String avc ="";
            }
        };

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(((TextView)v).getText().toString());
                mCalInterface.click(num);
                //num를 받아서 btn로 전달
            }
        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 작성한 코드

                if(TextUtils.isEmpty(edit1.getText().toString())||
                        TextUtils.isEmpty((edit2.getText().toString()))){//빈값이 넘어올때의 처리

                    Toast.makeText(getApplicationContext(), "값을 넣어주세요", Toast.LENGTH_SHORT).show();
                } else {
                    mJiooInterface.click(edit1.getText().toString(), edit2.getText().toString());
                }



            }
        });


    }




        interface  JiooInterface{
         void click(String firstNum, String secondNum);
        }

        interface CalInterface{
           void click(int btn);

        }

}
