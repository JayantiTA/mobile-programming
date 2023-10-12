package com.example.aplikasipenjualan;

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
import androidx.navigation.ui.AppBarConfiguration;
import com.example.aplikasipenjualan.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText namaPelanggan, namaBarang, jumlahBarang, hargaBarang, uangBayar;
    private TextView tvPelanggan, tvNamaBarang, tvJumlahBarang, tvHarga, tvTotal, tvUangBayar,
            tvBonus, tvKeterangan, tvKembalian, tvTitle;
    Button resetButton, exitButton, processButton;

    private NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Aplikasi Penjualan");
        }

        namaPelanggan = findViewById(R.id.etNamaPelanggan);
        namaBarang = findViewById(R.id.etNamaBarang);
        jumlahBarang = findViewById(R.id.etJumlahBarang);
        hargaBarang = findViewById(R.id.etHarga);
        uangBayar = findViewById(R.id.etJumlahUang);
        processButton = findViewById(R.id.btnProses);
        tvPelanggan = findViewById(R.id.tvNamaPembeli);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvJumlahBarang = findViewById(R.id.tvJumlahBarang);
        tvHarga = findViewById(R.id.tvHarga);
        tvUangBayar = findViewById(R.id.tvUangBayar);
        tvTotal = findViewById(R.id.tvTotal);
        tvKeterangan = findViewById(R.id.tvKeterangan);
        tvBonus = findViewById(R.id.tvBonus);
        tvKembalian = findViewById(R.id.tvKembalian);
        tvTitle = findViewById(R.id.tvTitle);
        resetButton = findViewById(R.id.btnReset);
        exitButton = findViewById(R.id.btnExit);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void processPayment() {
        String validatedInput = validateInput();
        if (validatedInput.isEmpty()) {
            double jmlBarang = Double.parseDouble(jumlahBarang.getText().toString());
            double harga = Double.parseDouble(hargaBarang.getText().toString());
            double bayar = Double.parseDouble(uangBayar.getText().toString());

            double totalBelanja = jmlBarang * harga;
            double kembalian = bayar - totalBelanja;

            if (kembalian < 0) {
                tvTitle.setTextColor(Color.RED);
                tvTitle.setText("Uang pembayaran kurang dari harga total!");
                return;
            }

            tvTitle.setTextColor(Color.BLACK);
            tvTitle.setText("Nota Pembayaran");

            tvPelanggan.setText("Nama Pembeli: " + namaPelanggan.getText().toString());
            tvNamaBarang.setText("Nama Barang: " + namaBarang.getText().toString());
            tvJumlahBarang.setText("Jumlah Barang: " + ((int) jmlBarang));
            tvHarga.setText("Harga Barang: Rp" + format.format(harga));
            tvUangBayar.setText("Uang Bayar: Rp" + format.format(bayar));
            tvTotal.setText("Total Belanja: Rp" + format.format(totalBelanja));
            tvKembalian.setText("Kembalian: Rp" + format.format(kembalian));

            String bonus = getBonus(totalBelanja);
            if (bonus.isEmpty()) {
                tvBonus.setVisibility(View.GONE);
            } else {
                tvBonus.setVisibility(View.VISIBLE);
                tvBonus.setText("Bonus: " + bonus);
            }

            tvKeterangan.setText("Keterangan: Menunggu kembalian");
        } else {
            tvTitle.setTextColor(Color.RED);
            tvTitle.setText(validatedInput);
        }
    }

    private String validateInput() {
        String namaPelangganStr = namaPelanggan.getText().toString();
        String namaBarangStr = namaBarang.getText().toString();
        String jmlBarangStr = jumlahBarang.getText().toString();
        String hargaStr = hargaBarang.getText().toString();
        String uangBayarStr = uangBayar.getText().toString();

        if (namaPelangganStr.isEmpty()) {
            return "Nama pembeli tidak boleh kosong!";
        }

        if (namaBarangStr.isEmpty()) {
            return "Nama barang tidak boleh kosong!";
        }

        if (jmlBarangStr.isEmpty()) {
            return "Jumlah barang tidak boleh kosong!";
        }

        if (hargaStr.isEmpty()) {
            return "Harga barang tidak boleh kosong!";
        }

        if (uangBayarStr.isEmpty()) {
            return "Belanjaan ini wajib dibayar!";
        }

        return "";
    }

    @SuppressLint("SetTextI18n")
    private String getBonus(double totalBelanja) {
        if (totalBelanja >= 5000000) {
            return "Hard Disk 1TB";
        }

        if (totalBelanja >= 2000000) {
            return "Flash Disk 64GB";
        }

        if (totalBelanja >= 1000000) {
            return "Wired Mouse + Mouse Pad";
        }

        return "";
    }

    @SuppressLint("SetTextI18n")
    private void resetData() {
        namaBarang.setText("");
        namaPelanggan.setText("");
        jumlahBarang.setText("");
        hargaBarang.setText("");
        uangBayar.setText("");
        tvKeterangan.setText("");
        tvBonus.setText("");
        tvKembalian.setText("");
        tvHarga.setText("");
        tvTotal.setText("");
        tvUangBayar.setText("");
        tvJumlahBarang.setText("");
        tvPelanggan.setText("");
        tvNamaBarang.setText("");
        tvTitle.setText("");
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