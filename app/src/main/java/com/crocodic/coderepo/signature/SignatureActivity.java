package com.crocodic.coderepo.signature;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crocodic.coderepo.R;
import com.crocodic.signaturepad.SignaturePad;

public class SignatureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView signatureResult;
    private SignaturePad signaturePad;
    private Button btnClear;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signatureResult = findViewById(R.id.signature);
        signaturePad = findViewById(R.id.signature_pad);
        btnClear = findViewById(R.id.btn_clear);
        btnDone = findViewById(R.id.btn_done);

        btnClear.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                Bitmap bmpTtd = signaturePad.getTransparentSignatureBitmap();
                signatureResult.setImageBitmap(bmpTtd);
                signaturePad.clear();
                break;
            case R.id.btn_clear:
                signaturePad.clear();
                break;
            default:
                break;
        }
    }
}
