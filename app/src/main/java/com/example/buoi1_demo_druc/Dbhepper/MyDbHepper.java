package com.example.buoi1_demo_druc.Dbhepper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHepper extends SQLiteOpenHelper {

    static  String DbName="QuanLy_SanPham";
    static  int version=1;
   public  MyDbHepper(Context context){
       super(context,DbName,null,version);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String on_create_tb_cat="CREATE TABLE tb_cat (id   INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT    NOT NULL);";
        sqLiteDatabase.execSQL(on_create_tb_cat);

        String sql_insert="INSERT INTO tb_cat(name) values('tivi'),('tulanh'),('dien thoai'),('xe may')";
        sqLiteDatabase.execSQL(sql_insert);


        String sql_create_tb_product = "CREATE TABLE tb_product ( id     INTEGER PRIMARY KEY AUTOINCREMENT, name   TEXT    NOT NULL, price  INTEGER NOT NULL, id_cat INTEGER REFERENCES tb_cat (id) NOT NULL);";
        sqLiteDatabase.execSQL(sql_create_tb_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
