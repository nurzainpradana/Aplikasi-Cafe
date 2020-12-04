package com.zainpradana.aplikasimenucafe.view.menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.zainpradana.aplikasimenucafe.R;
import com.zainpradana.aplikasimenucafe.database.DataHelper;

public class DaftarMenu extends AppCompatActivity {
    public static DaftarMenu dm;
    protected Cursor cursor;
    String[] daftarMenu, daftarKodeMenu, daftarJenisMenu;
    ListView listViewDaftarMenu;
    DataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);

        dm = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu", null);
        daftarMenu = new String[cursor.getCount()];
        daftarKodeMenu = new String[cursor.getCount()];
        daftarJenisMenu = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftarMenu[cc] = cursor.getString(2);
            daftarKodeMenu[cc] = cursor.getString(0);
            daftarJenisMenu[cc] = cursor.getString(1);
        }

        listViewDaftarMenu = findViewById(R.id.listview_daftar_menu);
        listViewDaftarMenu.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarMenu));
        listViewDaftarMenu.setSelected(true);
        listViewDaftarMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarKodeMenu[arg2];

                Intent goToLihatMenu = new Intent(DaftarMenu.this, LihatMenu.class);
                goToLihatMenu.putExtra("kd_menu", selection);
                startActivity(goToLihatMenu);
            }
        });
        ((ArrayAdapter) listViewDaftarMenu.getAdapter()).notifyDataSetInvalidated();
    }

    public void keHalamanUtama(View view) {
        onBackPressed();
    }
}