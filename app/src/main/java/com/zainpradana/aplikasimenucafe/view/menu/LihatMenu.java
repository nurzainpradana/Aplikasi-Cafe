package com.zainpradana.aplikasimenucafe.view.menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.database.DataHelper;

import java.text.NumberFormat;
import java.util.Locale;

import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_batagor;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_blacksalad;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_cappucino;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_chessecake;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_cireng;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_kopihitam;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_nasigoreng;
import static com.zainpradana.aplikasimenucafe.R.drawable.gambar_sparklingtea;
import static com.zainpradana.aplikasimenucafe.R.drawable.logo_cafe;

public class LihatMenu extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView tvNamaMenu, tvJenisMenu, tvHargaMenu, tvPenjelasanMenu;
    ImageView ivGambarMenu;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_menu);

        tvNamaMenu = findViewById(R.id.tv_nama_menu);
        tvJenisMenu = findViewById(R.id.tv_jenis_menu);
        tvHargaMenu = findViewById(R.id.tv_harga_menu);
        tvPenjelasanMenu = findViewById(R.id.tv_penjelasan_text);
        ivGambarMenu = findViewById(R.id.iv_gambar_menu);

        dbHelper = new DataHelper(this);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu WHERE kd_menu ='" + getIntent().getStringExtra("kd_menu") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvNamaMenu.setText(cursor.getString(2));
            tvJenisMenu.setText(cursor.getString(1));

            double harga = Double.parseDouble(cursor.getString(4));
            tvHargaMenu.setText(formatRupiah.format(harga));
            tvPenjelasanMenu.setText(cursor.getString(3));

            getGambarMenu(cursor.getInt(0));

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getGambarMenu(int kode_menu) {
        switch (kode_menu) {
            case 1001:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_kopihitam));
                break;

            case 1002:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_cappucino));
                break;

            case 1003:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_sparklingtea));
                break;

            case 1004:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_batagor));
                break;

            case 1005:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_cireng));
                break;

            case 1006:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_nasigoreng));
                break;

            case 1007:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_chessecake));
                break;

            case 1008:
                ivGambarMenu.setImageDrawable(getDrawable(gambar_blacksalad));
                break;

            default:
                ivGambarMenu.setImageDrawable(getDrawable(logo_cafe));
                break;
        }
    }


    public void keDaftarMenu(View view) {
        onBackPressed();
    }
}