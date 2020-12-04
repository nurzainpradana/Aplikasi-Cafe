package com.zainpradana.aplikasimenucafe.view.halamanutama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.view.menu.DaftarMenu;
import com.zainpradana.aplikasimenucafe.view.pesanan.DaftarPesanan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void keDaftarMenu(View view) {
        Intent i = new Intent(MainActivity.this, DaftarMenu.class);
        startActivity(i);
    }

    public void keDaftarPesanan(View view) {
        Intent i = new Intent(MainActivity.this, DaftarPesanan.class);
        startActivity(i);
    }
}