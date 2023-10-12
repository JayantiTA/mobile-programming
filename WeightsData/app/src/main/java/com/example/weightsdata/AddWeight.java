package com.example.weightsdata;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AddWeight extends AppCompatActivity {
    private EditText etdate, etmax, etmin;

    private DatabaseHelperWeight databaseHelperWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        databaseHelperWeight = new DatabaseHelperWeight(this);

        etdate = (EditText) findViewById(R.id.et_date);
        etmax = (EditText) findViewById(R.id.et_max);
        etmin = (EditText) findViewById(R.id.et_min);
        Button btnStore = (Button) findViewById(R.id.btn_store);
        Button btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnStore.setOnClickListener(v -> {
            String date = etdate.getText().toString();
            if (TextUtils.isEmpty(date)){
                etdate.setError("Enter Date");
                etdate.requestFocus();
                return;
            }

            double max = Double.parseDouble(etmax.getText().toString());
            double min = Double.parseDouble(etmin.getText().toString());

            databaseHelperWeight.addWeightsDetail(date, max, min);

            etdate.setText("");
            etmax.setText("");
            etmin.setText("");

            Toast.makeText(AddWeight.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddWeight.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        btnCancel.setOnClickListener(v -> {
            Intent intent13 = new Intent(AddWeight.this,MainActivity.class);
            intent13.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent13);
        });
    }
}
