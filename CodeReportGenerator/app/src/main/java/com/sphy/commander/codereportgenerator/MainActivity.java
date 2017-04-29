/*
  ------------------------------------------------------------------------------------------------------------------------------------------
  Project       : Code Report Generator
  File          : MainActivity.java
  Description   : This is the main activity of the program.
  Author        : Konstantinos Monachopoulos
  ------------------------------------------------------------------------------------------------------------------------------------------
*/

package com.sphy.commander.codereportgenerator;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private Button startPickDate;
    private Button endPickDate;
    private Button TodayPickedDate;
    private Button ExitButton;
    private Button CalcButton;
    private Calendar startDate;
    private Calendar endDate;
    static final int DATE_DIALOG_ID = 0;
    private TextView activeDateDisplay;
    private Calendar activeDate;
    private ImageView IconClick;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDateDisplay = (TextView) findViewById(R.id.StartDateTXT);
        endDateDisplay = (TextView) findViewById(R.id.EndDateTXT);

        startPickDate = (Button) findViewById(R.id.StartDateButton);
        endPickDate = (Button) findViewById(R.id.ToDateButton);
        TodayPickedDate = (Button) findViewById(R.id.TodayButton);
        ExitButton = (Button) findViewById(R.id.ExitButton);
        CalcButton = (Button) findViewById(R.id.CalculateButton);
        IconClick = (ImageView) findViewById(R.id.imageView);


        /* add a click listener to the ICON button   */
        IconClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        /* add a click listener to the START button   */
        startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* get the end date */
                startDate = Calendar.getInstance();
                Toast.makeText(getApplicationContext(), "Select Starting Date", Toast.LENGTH_SHORT)
                        .show();
                showDateDialog(startDateDisplay, startDate);
            }
        });

        /* add a click listener to the END button */
        endPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /* get the end date */
                endDate = Calendar.getInstance();
                Toast.makeText(getApplicationContext(), "Select End Date", Toast.LENGTH_SHORT)
                        .show();
                showDateDialog(endDateDisplay, endDate);
            }
        });

        /* add a click listener to the TODAY button   */
        TodayPickedDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startDate = Calendar.getInstance();
                endDate = Calendar.getInstance();
                updateDisplay(startDateDisplay, startDate);
                updateDisplay(endDateDisplay, endDate);
            }
        });

        /* add a click listener to the EXIT button   */
        ExitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });

        /* add a click listener to the CLACULATE button   */
        CalcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (startDateDisplay.getText().toString().equals("Starting Date")
                        || endDateDisplay.getText().toString().equals("Ending Date")) {

                    Toast.makeText(getApplicationContext(), "Set the dates correctly", Toast.LENGTH_SHORT)
                            .show();


                } else if (startDate.after(endDate)) {

                    Toast.makeText(getApplicationContext(), "Last Date is earlier from the First date", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    Intent intent = new Intent(MainActivity.this, DisplayCodes.class);

                    intent.putExtra("StartKey", startDateDisplay.getText().toString());
                    intent.putExtra("EndKey", endDateDisplay.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    /*************
     * Define Calendar API Functions
     ***********/

    /* Define Date Listener function */
    private OnDateSetListener dateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void updateDisplay(TextView dateDisplay, Calendar date) {

        /* These if statements fix the format date a fix length, needed by DisplayCodes */
        int Day = date.get(Calendar.DAY_OF_MONTH); // return int
        int Month = date.get(Calendar.MONTH) + 1; // return int

        if (Day < 10 && Month > 9) {
            dateDisplay.setText(
                    new StringBuilder()
                            .append(0).append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                            .append(date.get(Calendar.MONTH) + 1).append("-")       // Month is 0 based so add 1
                            .append(date.get(Calendar.YEAR)).append(""));

        } else if (Day > 9 && Month < 10) {
            dateDisplay.setText(
                    new StringBuilder()
                            .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                            .append(0).append(date.get(Calendar.MONTH) + 1).append("-")       // Month is 0 based so add 1
                            .append(date.get(Calendar.YEAR)).append(""));

        } else if (Day < 10 && Month < 10) {
            dateDisplay.setText(
                    new StringBuilder()
                            .append(0).append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                            .append(0).append(date.get(Calendar.MONTH) + 1).append("-")       // Month is 0 based so add 1
                            .append(date.get(Calendar.YEAR)).append(""));

        } else {
            dateDisplay.setText(
                    new StringBuilder()
                            .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                            .append(date.get(Calendar.MONTH) + 1).append("-")       // Month is 0 based so add 1
                            .append(date.get(Calendar.YEAR)).append(""));

        }
    }

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }
}