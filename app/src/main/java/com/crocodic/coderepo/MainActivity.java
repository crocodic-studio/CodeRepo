package com.crocodic.coderepo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.crocodic.coderepo.helper.See;

public class MainActivity extends AppCompatActivity {

    private TextView txtHalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHalo = (TextView) findViewById(R.id.txt_halo);
        txtHalo.setText("Hello world 3");

        See.log("hallo"); //// TODO: 10/11/17 NGE LOG BIASA
        See.log("user", "Zahra"); //// TODO: 10/11/17 NGE LOG DENGAN "KEY --> PESAN"
        See.logApi("login"); //// TODO: 10/11/17 NGE LOG API
        See.logBtn("daftar"); //// TODO: 10/11/17 NGE LOG TOMBOL

        See.toast(this, "Lengkapi data anda!"); //// TODO: 10/11/17 MEMBUAT TOAST

    }
}
