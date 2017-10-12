package com.crocodic.coderepo.realem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crocodic.coderepo.R;
import com.crocodic.coderepo.realem.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RealemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fruit pisang = new Fruit(1, "Pisang", "Kuning", "Manis");
        Fruit apel = new Fruit(2, "Apel", "Merah", "Manis");
        Fruit mangga = new Fruit(3, "Mangga", "Hijau", "Kecut");

        List<Fruit> buah = new ArrayList<>();
        buah.add(pisang);
        buah.add(apel);

        //// TODO: 10/11/17 CREATE
        DB.with(this).saveObject(buah); //// TODO: 10/11/17 BANYAK DATA

        DB.with(this).saveFruit(mangga); //// TODO: 10/11/17 SATU DATA
        DB.with(this).saveObject(mangga); //// TODO: 12/11/17 SATU DATA

        Fruit apelHijau = new Fruit();

        //// TODO: 10/11/17 READ
        apelHijau = DB.with(this).getFruit(2); //// TODO: 10/11/17 SATU DATA

        List<Fruit> fruits = new ArrayList<>();

        fruits = DB.with(this).getFruits(); //// TODO: 10/11/17 BANYAK DATA
        fruits = DB.with(this).findAll(Fruit.class); //// TODO: 121/11/17 BANYAK DATA

        //// TODO: 10/11/17 UPDATE

        apelHijau.setWarna("Hijau");
        DB.with(this).updateFruit(apelHijau); //// TODO: 10/11/17 UPDATE TIDAK LANGSUNG SATU DATA

        DB.with(this).updateFruit(2, "Hijau"); //// TODO: 10/11/17 UPDATE LANGSUNG SATU DATA

        fruits.remove(0);
        DB.with(this).updateObject(fruits); //// TODO: 10/11/17 UPDATE BANYAK

        //// TODO: 10/11/17
        DB.with(this).deleteFruit(1); //// TODO: 10/11/17 HAPUS SATU DATA BY ID
        DB.with(this).deleteFruits(); //// TODO: 10/11/17 HAPUS SEMUA DATA
    }

}
