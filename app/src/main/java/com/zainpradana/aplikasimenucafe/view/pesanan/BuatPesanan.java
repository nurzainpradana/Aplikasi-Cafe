package com.zainpradana.aplikasimenucafe.view.pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.database.DataHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.zainpradana.aplikasimenucafe.view.pesanan.DaftarPesanan.dp;

public class BuatPesanan extends AppCompatActivity {
    DataHelper dbHelper;
    EditText etKodePesanan, etTanggalPesan, etJamPesan, etNomorMeja, etNamaMenu, etHargaMenu, etQty;
    String namaMenu;
    double harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        etKodePesanan = findViewById(R.id.et_kode_pesanan);
        etTanggalPesan = findViewById(R.id.et_tanggal_pesan);
        etJamPesan = findViewById(R.id.et_jam_pesan);
        etNomorMeja = findViewById(R.id.et_nomor_meja);
        etNamaMenu = findViewById(R.id.et_nama_menu);
        etHargaMenu = findViewById(R.id.et_harga_menu);
        etQty = findViewById(R.id.et_qty);

        dbHelper = new DataHelper(this);

        DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());

        namaMenu = getIntent().getStringExtra("nama_menu_key");
        harga = getIntent().getDoubleExtra("harga_menu_key", 0);

        if (namaMenu != null && harga != 0){
            etNamaMenu.setText(namaMenu);
            etHargaMenu.setText(String.valueOf(harga));
        }

        etJamPesan.setText(time);
        etTanggalPesan.setText(date);
    }

    public void simpan(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into pesanan(kd_pesanan, tanggal, jam, nomor_meja, nama_menu, harga, qty) values('" +
                etKodePesanan.getText().toString() + "','" +
                etTanggalPesan.getText().toString() + "','" +
                etJamPesan.getText().toString() + "','" +
                etNomorMeja.getText().toString() + "','" +
                etNamaMenu.getText().toString() + "','" +
                etHargaMenu.getText().toString() + "','" +
                etQty.getText().toString() + "');");
        Toast.makeText(getApplicationContext(), "Berhasil" ,
                Toast.LENGTH_LONG).show();
        dp.RefreshList();
        finish();
    }

    public void kembali(View view) {
        onBackPressed();
    }
}