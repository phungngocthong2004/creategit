package com.example.buoi1_demo_druc.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.buoi1_demo_druc.DTO.CatDTO;
import com.example.buoi1_demo_druc.Dbhepper.MyDbHepper;

import java.util.ArrayList;

public class catDao {
    MyDbHepper dbHepper;
   SQLiteDatabase db;

   public catDao (Context context){
       dbHepper =new MyDbHepper(context);
       db=dbHepper.getWritableDatabase();
   }
   public  long AddNew(CatDTO ojbcat){
       ContentValues  values=new ContentValues();
       values.put("name",ojbcat.getName());
       return db.insert("tb_cat",null,values);
   }
   public int updateRow(CatDTO ojcat){
       ContentValues values=new ContentValues();
       values.put("name",ojcat.getName());
       String[] dk=new String[]{String.valueOf(ojcat.getId())};
       return db.update("tb_cat",values,"id=?",dk);
   }
   public  int deleteRow(CatDTO ojbcat){
       String[] dk=new String[]{String.valueOf(ojbcat.getId())};
       return db.delete("tb_cat","id=?",dk);
   }
   public ArrayList<CatDTO> getAll() {
       ArrayList<CatDTO>list=new ArrayList<>();
       Cursor c=db.rawQuery("SELECT * FROM tb_cat",null);

       if (c!=null&&c.getCount()>0){
           c.moveToFirst();
           do{
               list.add(new CatDTO(c.getInt(0),c.getString(1)));
           }while (c.moveToNext());
       }else {
           Log.d("zzzzzzzzzzzzz","getAll:không lấy Dược Dữ Liệu");
       }
       return  list;
    }
    public boolean removeToto(int id){
        db=dbHepper.getWritableDatabase();
        String[] dk=new String[]{String.valueOf(id)};
        int row=db.delete("tb_cat", "id=?",dk);

        return row!=-1;
    }
//    public ArrayList<CatDTO> getAll(){
//        ArrayList<CatDTO> list = new ArrayList<CatDTO>();
//        Cursor c = db.rawQuery("SELECT * FROM tb_cat",null);
//        if (c!= null && c.getCount() > 0){
//            c.moveToFirst();
//            do{
//                list.add(new CatDTO(c.getInt(0),c.getString(1)));
//            }while(c.moveToNext());
//        }else{
//            Log.d("zzzzzzzzzzz", "getAll: Không lấy được dữ liệu ");
//        }
//
//        return list;
//    }
}
