package com.example.hitungluas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editWidth, editHeight;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWidth = findViewById(R.id.editWidth);
        editHeight = findViewById(R.id.editHeight);
        Button calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateArea();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateArea() {
        String widthStr = editWidth.getText().toString();
        String heightStr = editHeight.getText().toString();

        if (!widthStr.isEmpty() && !heightStr.isEmpty()) {
            double width = Double.parseDouble(widthStr);
            double height = Double.parseDouble(heightStr);

            double area = width * height;

            resultTextView.setText("Area: " + area);
        } else {
            resultTextView.setText("Please enter both width and height.");
        }
    }
}
