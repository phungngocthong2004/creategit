package com.example.buoi1_demo_druc.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.buoi1_demo_druc.DTO.Product_DTO;
import com.example.buoi1_demo_druc.Dbhepper.MyDbHepper;

import java.util.ArrayList;

public class ProductDao {
    MyDbHepper myDbHepper;
    SQLiteDatabase db;
    public  ProductDao (Context context){
        myDbHepper=new MyDbHepper(context);
        db=myDbHepper.getWritableDatabase();

    }

    public long AddRow(Product_DTO ojectProduct){
        ContentValues values=new ContentValues();
        values.put("name",ojectProduct.getName());
        values.put("price",ojectProduct.getPricae());
        values.put("id_cat",ojectProduct.getIdcat());
        return  db.insert("tb_product",null,values);
    }
    public int UpdateRow(Product_DTO ojectProduct){
        ContentValues values=new ContentValues();
        values.put("name",ojectProduct.getName());
        values.put("price",ojectProduct.getPricae());
        values.put("id_cat",ojectProduct.getIdcat());
        String[] dk=new String[]{String.valueOf(ojectProduct.getId())};
        return db.update("tb_product",values,"id=?",dk);
    }
    public int deleteRow(Product_DTO ojectproduct){
        String[] dk=new String[]{String.valueOf(ojectproduct.getId())};
        return db.delete("tb_product","id=?",dk);
    }
    public ArrayList<Product_DTO>getAll(){
        ArrayList<Product_DTO>list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT *FROM tb_product",null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new Product_DTO(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3)));
            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzz","getAll:khong lay dc du lieu");
        }
        return list;
    }

}
