package com.crocodic.coderepo.badge;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.crocodic.coderepo.R;
import com.crocodic.coderepo.helper.See;

public class BadgeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvViewCount;

    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
    }

    /**
     * Pengaturan toolbar android
     */
    private void initToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        final View notificaitons = menu.findItem(R.id.action_badge).getActionView();

        tvViewCount = (TextView) notificaitons.findViewById(R.id.tv_count);
        updateHotCount(count+=1);

        notificaitons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                See.logBtn("menu badge");
                updateHotCount(count+=1);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * update counter di badge
     * @param number yang akan ditampilkan di counter
     */
    public void updateHotCount(final int number) {
        See.log("update badge");
        count = number;
        if (count < 0) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (count == 0)
                    tvViewCount.setVisibility(View.GONE);
                else {
                    tvViewCount.setVisibility(View.VISIBLE);
                    if(count<100) {
                        tvViewCount.setText(String.valueOf(number));
                    } else {
                        tvViewCount.setText("9+");
                    }
                    // supportInvalidateOptionsMenu();
                }
            }
        });
    }
}
