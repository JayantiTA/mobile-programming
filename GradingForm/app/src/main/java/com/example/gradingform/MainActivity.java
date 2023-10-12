package com.example.gradingform;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gradingform.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private EditText namaMahasiswa, nrpMahasiswa, kodeMatkul, namaMatkul, nilaiTugas,
            nilaiKuis, nilaiETS, nilaiEAS, nilaiFP;
    private TextView tvNamaMahasiswa, tvNRPMahasiswa, tvMataKuliah, tvNilaiAngka, tvNilaiHuruf, tvTitle;
    Button resetButton, exitButton, processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        namaMahasiswa = findViewById(R.id.etNama);
        nrpMahasiswa = findViewById(R.id.etNRP);
        kodeMatkul = findViewById(R.id.etKodeMK);
        namaMatkul = findViewById(R.id.etNamaMK);
        nilaiTugas = findViewById(R.id.etNilaiTugas);
        nilaiKuis = findViewById(R.id.etNilaiKuis);
        nilaiETS = findViewById(R.id.etNilaiETS);
        nilaiEAS = findViewById(R.id.etNilaiEAS);
        nilaiFP = findViewById(R.id.etNilaiFP);

        tvTitle = findViewById(R.id.tvTitle);
        tvNamaMahasiswa = findViewById(R.id.tvNamaMahasiswa);
        tvNRPMahasiswa = findViewById(R.id.tvNRPMahasiswa);
        tvMataKuliah = findViewById(R.id.tvMataKuliah);
        tvNilaiAngka = findViewById(R.id.tvNilaiAngka);
        tvNilaiHuruf = findViewById(R.id.tvNilaiHuruf);

        processButton = findViewById(R.id.btnProses);
        resetButton = findViewById(R.id.btnReset);
        exitButton = findViewById(R.id.btnExit);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processGrading();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetData();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void processGrading() {
        String validatedInput = validateInput();
        if (!validatedInput.isEmpty()) {
            tvTitle.setTextColor(Color.RED);
            tvTitle.setText(validatedInput);
            return;
        }

        double nilaiTugas = Double.parseDouble(this.nilaiTugas.getText().toString());
        double nilaiKuis = Double.parseDouble(this.nilaiKuis.getText().toString());
        double nilaiETS = Double.parseDouble(this.nilaiETS.getText().toString());
        double nilaiEAS = Double.parseDouble(this.nilaiEAS.getText().toString());
        double nilaiFP = Double.parseDouble(this.nilaiFP.getText().toString());

        double nilai = (nilaiTugas * 0.15) + (nilaiKuis * 0.1) + (nilaiETS * 0.25) + (nilaiEAS * 0.25) + (nilaiFP * 0.25);
        String nilaiHuruf = convertGrade(nilai);

        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setText("Rekap Nilai");

        tvNamaMahasiswa.setText("Nama Mahasiswa: " + namaMahasiswa.getText().toString());
        tvNRPMahasiswa.setText("NRP Mahasiswa: " + nrpMahasiswa.getText().toString());
        tvMataKuliah.setText("Mata Kuliah: [" + kodeMatkul.getText().toString() + "] " + namaMatkul.getText().toString());
        tvNilaiAngka.setText("Nilai Angka: " + nilai);
        tvNilaiHuruf.setText("Nilai Huruf: " + nilaiHuruf);
    }

    private String convertGrade(double nilai) {
        if (nilai >= 86) {
            return "A";
        } else if (nilai >= 76) {
            return "AB";
        } else if (nilai >= 66) {
            return "B";
        } else if (nilai >= 61) {
            return "BC";
        } else if (nilai >= 56) {
            return "C";
        } else if (nilai >= 41) {
            return "D";
        }

        return "E";
    }

    @SuppressLint("SetTextI18n")
    private void resetData() {
        namaMahasiswa.setText("");
        nrpMahasiswa.setText("");
        namaMatkul.setText("");
        kodeMatkul.setText("");
        nilaiTugas.setText("");
        nilaiETS.setText("");
        nilaiEAS.setText("");
        nilaiKuis.setText("");
        nilaiFP.setText("");
        tvTitle.setText("");
        tvNRPMahasiswa.setText("");
        tvNamaMahasiswa.setText("");
        tvNilaiHuruf.setText("");
        tvNilaiAngka.setText("");
        tvMataKuliah.setText("");
    }

    private String validateInput() {
        String namaMhs = namaMahasiswa.getText().toString();
        String nrpMhs = nrpMahasiswa.getText().toString();
        String kodeMK = kodeMatkul.getText().toString();
        String namaMK = namaMatkul.getText().toString();
        String nTugas = nilaiTugas.getText().toString();
        String nEAS = nilaiEAS.getText().toString();
        String nKuis = nilaiKuis.getText().toString();
        String nFP = nilaiFP.getText().toString();
        String nETS = nilaiETS.getText().toString();

        if (namaMhs.isEmpty()) {
            return "Nama mahasiswa tidak boleh kosong!";
        }

        if (nrpMhs.isEmpty()) {
            return "NRP mahasiswa tidak boleh kosong!";
        }

        if (kodeMK.isEmpty()) {
            return "Kode MK tidak boleh kosong!";
        }

        if (namaMK.isEmpty()) {
            return "Nama MK tidak boleh kosong!";
        }

        if (nTugas.isEmpty() || nEAS.isEmpty() || nKuis.isEmpty() || nFP.isEmpty() || nETS.isEmpty()) {
            return "Semua nilai harus terisi!";
        }

        return "";
    }


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