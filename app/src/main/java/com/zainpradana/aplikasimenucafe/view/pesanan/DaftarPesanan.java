package com.zainpradana.aplikasimenucafe.view.pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.database.DataHelper;
import com.zainpradana.aplikasimenucafe.view.pesanan.LihatPesanan;


public class DaftarPesanan extends AppCompatActivity {
    public static DaftarPesanan dm;
    protected Cursor cursor;
    String[] daftarKodePesanan;
    ListView listViewDaftarPesanan;
    DataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);

        dm = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pesanan", null);
        daftarKodePesanan = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftarKodePesanan[cc] = cursor.getString(0);
        }

        listViewDaftarPesanan = findViewById(R.id.listview_daftar_pesanan);
        listViewDaftarPesanan.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarKodePesanan));
        listViewDaftarPesanan.setSelected(true);
        listViewDaftarPesanan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarKodePesanan[arg2];

                Intent goToLihatPesanan = new Intent(DaftarPesanan.this, LihatPesanan.class);
                goToLihatPesanan.putExtra("kd_pesanan", selection);
                startActivity(goToLihatPesanan);
            }
        });
        ((ArrayAdapter) listViewDaftarPesanan.getAdapter()).notifyDataSetInvalidated();
    }

    public void keHalamanUtama(View view) {
        onBackPressed();
    }
}