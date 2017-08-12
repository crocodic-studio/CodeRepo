package com.crocodic.coderepo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtHalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHalo = (TextView) findViewById(R.id.txt_halo);
        txtHalo.setText("Hello world 2");
    }
}
