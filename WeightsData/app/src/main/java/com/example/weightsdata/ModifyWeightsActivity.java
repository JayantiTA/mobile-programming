package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyWeightsActivity extends AppCompatActivity {
    private WeightModel weightsModel;
    private EditText etdate, etmax, etmin;
    private DatabaseHelperWeight databaseHelperWeight;

    @SuppressLint({"SetTextI18n"})
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_weights);

        weightsModel = getIntent().getParcelableExtra("weights");
        databaseHelperWeight = new DatabaseHelperWeight(this);

        etdate = findViewById(R.id.etdate);
        etmax = findViewById(R.id.etmax);
        etmin = findViewById(R.id.etmin);
        Button btndelete = findViewById(R.id.btn_delete);
        Button btnupdate = findViewById(R.id.btn_update);
        Button btncancel = findViewById(R.id.btn_cancel);

        etdate.setText(weightsModel.getDate());
        etmax.setText(Double.toString(weightsModel.getMax()));
        etmin.setText(Double.toString(weightsModel.getMin()));

        btnupdate.setOnClickListener(v -> {
            double max = Double.parseDouble(etmax.getText().toString());
            double min = Double.parseDouble(etmin.getText().toString());
            databaseHelperWeight.updateWeights(weightsModel.getId(), etdate.getText().toString(), max, min);
            Toast.makeText(ModifyWeightsActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent12 = new Intent(ModifyWeightsActivity.this,MainActivity.class);
            intent12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent12);
        });

        btndelete.setOnClickListener(v -> {
            databaseHelperWeight.deleteUSer(weightsModel.getId());
            Toast.makeText(ModifyWeightsActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(ModifyWeightsActivity.this,MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });

        btncancel.setOnClickListener(v -> {
            Intent intent13 = new Intent(ModifyWeightsActivity.this,MainActivity.class);
            intent13.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent13);
        });

    }
}
