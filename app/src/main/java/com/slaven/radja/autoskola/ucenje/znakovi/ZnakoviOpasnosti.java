package com.slaven.radja.autoskola.ucenje.znakovi;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slaven.radja.autoskola.R;

import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviOpasnosti;



import java.util.List;


/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviOpasnosti extends Activity {

    List<Znak> signList;

    int sid = 0;
    Znak currentS;
    TextView txtZnakovi;
    Button butNext;
    ImageView signImage;
    Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.znakovi);
        DbHelperZnakoviOpasnosti db = new DbHelperZnakoviOpasnosti(this);

        signList = db.getAllSigns();
        currentS = signList.get(sid);
        txtZnakovi = (TextView) findViewById(R.id.tvZnak);
        butNext = (Button) findViewById(R.id.bNextZnakovi);
        signImage = (ImageView) findViewById(R.id.iv_znak_image);
        serviceIntent = new Intent(ZnakoviOpasnosti.this,PrometniZnakovi.class);
        setSignView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sid < 5){

              currentS = signList.get(sid);
                    setSignView();

                }else{
                    Toast.makeText(getApplicationContext(), "Nema više znakova", Toast.LENGTH_LONG).show();
                    startActivity(serviceIntent);
                    finish();
                    }
            }
        });

    }

     private void setSignView() {
        txtZnakovi.setText(currentS.getName());
        signImage.setImageResource(currentS.getId_img());

         sid++;
    }
}