package com.example.calculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private EditText editNum1, editNum2;
    private TextView textRes;
    private Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Kalkulator");
        }

        editNum1 = findViewById(R.id.number1);
        editNum2 = findViewById(R.id.number2);
        textRes = findViewById(R.id.txtViewHasil);

        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(operation);

        btnKurang = findViewById(R.id.btnKurang);
        btnKurang.setOnClickListener(operation);

        btnKali = findViewById(R.id.btnKali);
        btnKali.setOnClickListener(operation);

        btnBagi = findViewById(R.id.btnBagi);
        btnBagi.setOnClickListener(operation);
    }

    private final View.OnClickListener operation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            float num1, num2, res = 0;
            Button bt;

            num1 = Float.parseFloat(editNum1.getText().toString());
            num2 = Float.parseFloat(editNum2.getText().toString());

            if (v.getId() == R.id.btnTambah) {
                res = num1 + num2;
            } else if (v.getId() == R.id.btnKurang) {
                res = num1 - num2;
            } else if (v.getId() == R.id.btnKali) {
                res = num1 * num2;
            } else if (v.getId() == R.id.btnBagi) {
                res = num1 / num2;
            }

            bt = findViewById(v.getId());
            textRes.setText(num1 + " " + bt.getText() + " " + num2 +  " = " + res);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}