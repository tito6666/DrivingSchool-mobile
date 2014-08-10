package com.slaven.radja.autoskola.ucenje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.ucenje.raskrizje.Raskrizje;
import com.slaven.radja.autoskola.ucenje.znakovi.PrometniZnakovi;

/**
 * Created by Computer on 05/08/2014.
 */
public class UcenjeLista extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    String[] titles;
    String[] classes;


    public String lv_class_names[]= {BrzinaKretanja.class.getName(), PrometniZnakovi.class.getName(), PrometniSemafor.class.getName(), Raskrizje.class.getName(),ZaustavljanjeiParkiranje.class.getName(),KruzniTokRotor.class.getName()};
int[] images ={R.drawable.brzina__icon,R.drawable.stop_icon, R.drawable.semafor_icon, R.drawable.crossroad_icon,R.drawable.parking_icon,R.drawable.kruznitok_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucenje_lista);

        Resources res = getResources();
        titles = res.getStringArray(R.array.titles);

        list =(ListView) findViewById(R.id.listView);
        UcenjeAdapter adapter = new UcenjeAdapter(this,titles,images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


        Intent i = null;
        try {
            i = new Intent(this, Class.forName(lv_class_names[position]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(i);



    }
}

class UcenjeAdapter extends ArrayAdapter<String>
    {
        Context context;
        int[] images;
        String[] titles;
    UcenjeAdapter(Context c,String [] titles,int images[])
    {
        super(c,R.layout.ucenje_lista,R.id.textView,titles);
        this.images=images;
        this.context=c;
        this.titles = titles;

    }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.red,parent,false);

            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
            TextView myText = (TextView) row.findViewById(R.id.textView);

            myImage.setImageResource(images[position]);
            myText.setText(titles[position]);

            return row;
        }
    }

