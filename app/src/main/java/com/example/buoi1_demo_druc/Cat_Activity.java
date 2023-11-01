package com.example.buoi1_demo_druc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buoi1_demo_druc.Adapter.cat_Adapter;
import com.example.buoi1_demo_druc.DTO.CatDTO;
import com.example.buoi1_demo_druc.Dao.catDao;

import java.util.ArrayList;

public class Cat_Activity extends AppCompatActivity {
        TextView tv_Name;
        EditText ed_tensanpham;
        Button btntheem,btnsua,btnxoa;
        ListView lvdanhsach;
        catDao dao;
        ArrayList<CatDTO>list_danhsach;
        cat_Adapter adapter;
        RecyclerView rc_cat;
        CatDTO objCatDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        tv_Name=findViewById(R.id.tv_name);
        ed_tensanpham=findViewById(R.id.ed_name);
        btntheem=findViewById(R.id.btnadd);

        rc_cat=findViewById(R.id.rc_cat);
//        lvdanhsach=findViewById(R.id.lvdanh



        dao=new catDao(this);
        list_danhsach=dao.getAll();
         adapter=new cat_Adapter(this,list_danhsach);
         rc_cat.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_cat.setLayoutManager(linearLayoutManager);
        rc_cat.setAdapter(adapter);



        btntheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= ed_tensanpham.getText().toString().trim();
                catDao dao1=new catDao(Cat_Activity.this);
                CatDTO cat=new CatDTO();
                cat.setName(name);
                long id= dao1.AddNew(cat);
                if (id>0){

                    Toast.makeText(Cat_Activity.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                    list_danhsach.clear();
                    list_danhsach.addAll(dao.getAll());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(Cat_Activity.this, "them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        lvdanhsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                objCatDTO=list_danhsach.get(i);
//                ed_tensanpham.setText(objCatDTO.getName());
//                return true;
//            }
//        });
//        btnsua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(Cat_Activity.this);
//                builder.setTitle("cap nhat");
//                builder.setMessage("ban co muon cap nhat khong");
//                builder.setPositiveButton("cap nhat", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                      objCatDTO.setName(ed_tensanpham.getText().toString().trim());
//                      int kq=dao.updateRow(objCatDTO);
//                        if (kq>0){
//                            Toast.makeText(Cat_Activity.this, "update thanh cong", Toast.LENGTH_SHORT).show();
//                            list_danhsach.clear();
//                            list_danhsach.addAll(dao.getAll());
//                            adapter.notifyDataSetChanged();
//                        }else{
//                            Toast.makeText(Cat_Activity.this, "update thât bai", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                });
//                builder.setNegativeButton("huy",null);
//                builder.show();
//
//            }
//        });
//        btnxoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            int kq=dao.deleteRow(objCatDTO);
//                if (kq>0){
//                    Toast.makeText(Cat_Activity.this, "update thanh cong", Toast.LENGTH_SHORT).show();
//                    list_danhsach.clear();
//                    list_danhsach.addAll(dao.getAll());
//                    adapter.notifyDataSetChanged();
//                    objCatDTO=null;
//                    tv_Name.setText("");
//
//                }else{
//                    Toast.makeText(Cat_Activity.this, "update thât bai", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

}