package com.zainpradana.aplikasimenucafe.view.pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.database.DataHelper;

import java.text.NumberFormat;
import java.util.Locale;

import static com.zainpradana.aplikasimenucafe.view.pesanan.DaftarPesanan.dp;

public class LihatPesanan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView tvKodePesanan, tvTanggalPesan, tvJamPesan, tvNomorMeja, tvNamaMenu, tvHargaMenu, tvQty, tvTotal;
    Integer kodePesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pesanan);

        tvKodePesanan = findViewById(R.id.tv_kode_pesanan);
        tvTanggalPesan = findViewById(R.id.tv_tanggal_pesan);
        tvJamPesan = findViewById(R.id.tv_jam_pesan);
        tvNomorMeja = findViewById(R.id.tv_nomor_meja);
        tvNamaMenu = findViewById(R.id.tv_nama_menu);
        tvHargaMenu = findViewById(R.id.tv_harga_menu);
        tvQty = findViewById(R.id.tv_qty);
        tvTotal = findViewById(R.id.tv_total);

        dbHelper = new DataHelper(this);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pesanan WHERE kd_pesanan ='" + getIntent().getStringExtra("kd_pesanan") + "'", null);
        cursor.moveToFirst();


        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            kodePesanan = cursor.getInt(0);

            tvKodePesanan.setText(cursor.getString(0));
            tvTanggalPesan.setText(cursor.getString(1));
            tvJamPesan.setText(cursor.getString(2));
            tvNomorMeja.setText(cursor.getString(3));
            tvNamaMenu.setText(cursor.getString(4));

            double harga = Double.parseDouble(cursor.getString(5));
            tvHargaMenu.setText(formatRupiah.format(harga));

            int qty = cursor.getInt(6);
            tvQty.setText(cursor.getString(6));

            double total = harga * qty;
            tvTotal.setText(formatRupiah.format(total));
        }
    }

    public void keDaftarMenu(View view) {
        onBackPressed();
    }

    public void selesaiDanHapus(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM pesanan WHERE kd_pesanan = '"+ kodePesanan + "'");
        dp.RefreshList();
    }
}