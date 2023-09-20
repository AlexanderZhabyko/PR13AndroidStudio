package com.example.pr13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText dateText, timeText;
    ImageButton date, time;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        date = findViewById(R.id.dateBtn);
        time = findViewById(R.id.timeBtn);
        btn = findViewById(R.id.button);
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.ic_question_mark);

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int yearNow = Calendar.getInstance().get(Calendar.YEAR);
                int monthNow = Calendar.getInstance().get(Calendar.MONTH);
                int dayNow = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateText.setText(""+year+"-"+(month)+"-"+dayOfMonth);
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.DatePickerTheme, dateSetListener, yearNow, monthNow, dayNow);
                datePickerDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean format24 = true;
                int hoursNow = Calendar.getInstance().getTime().getHours();
                int minNow = Calendar.getInstance().getTime().getMinutes();

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeText.setText(hourOfDay+":" + minute);
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, timeSetListener, hoursNow, minNow, format24);
                timePickerDialog.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlarmDialogTheme);
                builder.setTitle("Подтверждение записи к доктору").setMessage("Вы подтверждаете запись к лебедеву С.С.?")
                        .setPositiveButton("Подтвержаю", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "Подтверждено", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("НЕ подтверждаю", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setView(image).create();
                builder.show();
            }
        });
    }

}