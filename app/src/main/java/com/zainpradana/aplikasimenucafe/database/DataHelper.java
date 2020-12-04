package com.zainpradana.aplikasimenucafe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbcafe.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE menu (kd_menu integer NOT NULL PRIMARY KEY, jenis text NOT NULL, nama text NOT NULL, penjelasan text NOT NULL, harga integer NOT NULL)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1001, 'Minuman', 'Kopi Hitam', 'Kopi Hitam dengan dibuat dengan teknik espressio, dimana biji kopi yang digunakan yaitu berasal dari Aceh Gayo jenis Arabika, disajikan dengan gula terpisah', 10000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1002, 'Minuman', 'Cappucino', 'Paduan kopi dengan buih susu kental serta menggunakan sirup karamel, dimana biji kopi yang digunakan berasal dari Gunung Puntang Jawa Barat jenis robusta', 20000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1003, 'Minuman', 'Sparkling Tea', 'Minuman Teh yang menggunakan daun teh dari pegunungan daerah Garut dengan tambahan sari melati asli dan gula merah alami', 15000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1004, 'Makanan', 'Batagor', 'Baso dan Tahu goreng dengan sajian bumbu kacang dan kecap khas Bandung', 25000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1005, 'Makanan', 'Cireng', 'Makanan ringan berupa tepung kanji goreng isi bahan dasar tempe fermentasi yang disebut oncom, disajikan dengan bumbu kacang pedas', 10000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1006, 'Makanan', 'Nasi Goreng', 'Nasi Goreng Ayam disajikan dengan telur mata sapi disertai satai ayam', 50000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1007, 'Dessert', 'Cheese Cake', 'Kue Tart 1 slice dengan bahan utama cream cheese dengan topping buah-buahan asli seperti anggur, jeruk , kiwi', 40000)");
        db.execSQL("INSERT INTO menu (kd_menu, jenis, nama, penjelasan, harga) VALUES (1008, 'Dessert', 'Black Salad', 'Potongan buah-buah segar yang terdiri dari Pepaya, Jambu, Melon, dan Mangga disajikan dengan bumbu rujak kacang pedas dan gula merah', 30000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
