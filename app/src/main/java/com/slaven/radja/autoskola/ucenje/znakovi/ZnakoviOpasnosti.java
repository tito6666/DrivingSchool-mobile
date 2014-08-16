package com.slaven.radja.autoskola.ucenje.znakovi;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slaven.radja.autoskola.R;

import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviOpasnosti;



import java.util.List;


/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviOpasnosti extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.znakovi_izricitih_naredbi);
        GridView prohibitorySigns = (GridView) findViewById(R.id.gv_prohibitory_signs);
        DbHelperZnakoviOpasnosti dbHelper = new DbHelperZnakoviOpasnosti(this);
        List<Znak> signs = dbHelper.getAllSigns();
        SignsAdapter adapter = new SignsAdapter(this, signs);
        prohibitorySigns.setAdapter(adapter);
    }
}