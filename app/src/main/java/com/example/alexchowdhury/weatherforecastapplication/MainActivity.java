package com.example.alexchowdhury.weatherforecastapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView temp;
    public TextView conditions;
    public TextView low;
    public TextView high;
    public TextView date;
    public EditText editText;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = (TextView)findViewById(R.id.textView);
        conditions = (TextView)findViewById(R.id.textView2);
        low = (TextView)findViewById(R.id.textView3);
        high = (TextView)findViewById(R.id.textView4);
        date = (TextView)findViewById(R.id.textView5);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherUpdate weatherupdate = new WeatherUpdate(MainActivity.this);
                weatherupdate.execute(editText.getText().toString());
            }
        });

    }
}
