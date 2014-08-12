package com.slaven.radja.autoskola.ispiti;

/**
 * Created by Computer on 06/08/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.DisplayHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends Activity {

    List<Question> quesList;
    int score = 0; //komentar
    int qid = 0;
    Question currentQ, confirmedQ;
    TextView txtQuestion, tvVrijeme;
    RadioButton rda, rdb, rdc, answer;
    Button butNext,butPrev,butConfirm;
    Intent serviceIntent;
    ImageView questionImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);
        DbHelper db = new DbHelper(this);

        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        confirmedQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.bNext);
        butPrev = (Button) findViewById(R.id.bPrev);
        butConfirm = (Button) findViewById(R.id.bConfirm);
        tvVrijeme = (TextView) findViewById(R.id.satPrikaz);
        serviceIntent = new Intent(QuizActivity.this, ResultActivity.class);
        questionImage = (ImageView) findViewById(R.id.iv_question_image);

        setQuestionView();


        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {

                tvVrijeme.setText("Vrijeme: " + TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                tvVrijeme.setTextColor(Color.BLACK);


            }

            public void onFinish() {
                Bundle b = new Bundle();
                b.putInt("score", score);
                startActivity(serviceIntent);
                finish();
            }
        }.start();

        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

                if(confirmedQ.getANSWER().equals(answer.getText())){
                    if(qid <= 1){
                        if (currentQ.getANSWER().equals(answer.getText())){
                            confirmedQ.setANSWER((String)answer.getText());
                            score++;
                            Bundle b = new Bundle();
                            b.putInt("score", score);
                            serviceIntent.putExtras(b);
                        }
                        else{

                            Bundle b = new Bundle();
                            b.putInt("score", score);
                            serviceIntent.putExtras(b);

                        }
                        }
                       else{
                        Bundle b = new Bundle();
                        b.putInt("score", score);
                        serviceIntent.putExtras(b);
                    }
                    }
                else  {
                   if (currentQ.getANSWER().equals(answer.getText())){
                     confirmedQ.setANSWER((String)answer.getText());
                    score++;
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    serviceIntent.putExtras(b);
                   }
                   else{

                       Bundle b = new Bundle();
                       b.putInt("score", score);
                       serviceIntent.putExtras(b);

                   }
                }


            }

        });

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qid < 5) {
                    currentQ = quesList.get(qid);
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    serviceIntent.putExtras(b);
                    setQuestionView();


                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }

        });
        butPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qid <= 1) {
                    Toast.makeText(getApplicationContext(), "Prvo pitanje", Toast.LENGTH_LONG).show();

                } else{
                    qid = qid-2;
                    currentQ = quesList.get(qid);
                    setQuestionView();


                }
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        if (currentQ.hasImage()) {
            questionImage.setImageResource(currentQ.getImg_ID());
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }
        qid++;
    }

}