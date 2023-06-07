package com.sunmi.printerhelper.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sunmi.printerhelper.R;
import com.sunmi.printerhelper.utils.SunmiPrintHelper;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button scan ,printing;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scan = findViewById(R.id.scanss);
        textView =findViewById(R.id.scan_result);

        scan.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setPrompt("");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
        });

        findViewById(R.id.btn_printss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SunmiPrintHelper.getInstance().printExample(getApplicationContext());
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String contents = intentResult.getContents();
            if (contents != null) {
                textView.setText(contents);
                // Save the scan result to a variable
                String scanResult = contents;
//                printing.setOnClickListener(v->{
//                    // Create an intent to navigate to another activity
//                    Intent intent = new Intent(MainActivity.this, PrintingActivity.class);
//                    // Pass the scan result as an extra to the intent
//                    intent.putExtra("scanResult", scanResult);
//                    startActivity(intent);
//                });
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
