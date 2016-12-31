/*
  ------------------------------------------------------------------------------------------------------------------------------------------
  Project       : Code Report Generator
  File          : DisplayCodes.java
  Description   : This file is responsible for displaying the output of the query. i.e. the dates and the -codes/passwords-
  Author        : Konstantinos Monahopoulos
  ------------------------------------------------------------------------------------------------------------------------------------------
*/

package com.sphy.commander.codereportgenerator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayCodes extends AppCompatActivity {

    RelativeLayout rl;
    GridView grid;
    List<String> Infolist;
    int IdCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_codes);

        // Create Objects of methods
        rl = (RelativeLayout) findViewById(R.id.rl);
        grid = new GridView(DisplayCodes.this);
        Infolist = new ArrayList<String>();

        Intent intent = getIntent();


        /*Get Separated Dates using keys*/
        String StartDateStr = intent.getStringExtra("StartKey");
        String EndDateStr = intent.getStringExtra("EndKey");

        // Print the GRID
        Infolist.add("Date");
        Infolist.add("Code_1");
        Infolist.add("Code_2");

        DatabaseHandler db = new DatabaseHandler(this);

        db.FillSQL_db();

        String[] StartingRecord = db.getSQLString(StartDateStr);
        String[] EndingRecord   = db.getSQLString(EndDateStr);
        String[] Record;

        String StartID_Str = StartingRecord[0]; // needs string to int
        String EndID_Str = EndingRecord[0]; // needs string to int

        int StartID_Int = Integer.parseInt(StartID_Str);
        int EndID_Int = Integer.parseInt(EndID_Str);

        for (IdCounter = StartID_Int;IdCounter <= EndID_Int; IdCounter++){

            Record = db.getSQLRecord(IdCounter);
            Infolist.add(Record[1]);
            Infolist.add(Record[2]);
            Infolist.add(Record[3]);
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Infolist);

        grid.setNumColumns(3);
        grid.setGravity(Gravity.CENTER);
        grid.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        grid.setAdapter(adp);
        rl.addView(grid);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(getBaseContext(), Infolist.get(arg2),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
