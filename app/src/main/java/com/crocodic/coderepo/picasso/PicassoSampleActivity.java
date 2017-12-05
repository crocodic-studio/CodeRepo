package com.crocodic.coderepo.picasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crocodic.coderepo.R;
import com.squareup.picasso.Picasso;

public class PicassoSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_sample);

       /* Picasso.with(ctx)
                .load(load)
                .placeholder(R.drawable.placeholder_loading_image)
                .into(target);*/
    }
}
