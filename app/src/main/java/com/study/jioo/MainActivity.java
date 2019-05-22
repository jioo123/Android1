package com.study.jioo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int mCount=0;
    JiooInterface mJiooInterface;
    //멤버변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



            mJiooInterface = new JiooInterface() {
                @Override
                public void click(int firstNum, int secondNum) {
 ////곱셈
                  mCount = firstNum * secondNum;

                }



        };

        Button button = findViewById(R.id.click);
        final EditText edit1=findViewById(R.id.first_num);
        final EditText edit2=findViewById(R.id.second_num);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 작성한 코드



                TextView textView = findViewById(R.id.result);
                mJiooInterface.click(Integer.parseInt(edit1.getText().toString()),Integer.parseInt(edit2.getText().toString()));
                textView.setText(""+mCount);

            }
        });
    }


        interface  JiooInterface{
        void click(int firstNum, int secondNum);
}

}
