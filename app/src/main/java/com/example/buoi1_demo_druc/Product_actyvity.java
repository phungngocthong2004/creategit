
package com.example.buoi1_demo_druc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buoi1_demo_druc.Adapter.cat_Adapter;
import com.example.buoi1_demo_druc.Adapter.product_Adapter;
import com.example.buoi1_demo_druc.DTO.CatDTO;
import com.example.buoi1_demo_druc.DTO.Product_DTO;
import com.example.buoi1_demo_druc.Dao.ProductDao;
import com.example.buoi1_demo_druc.Dao.catDao;

import java.util.ArrayList;

public class Product_actyvity extends AppCompatActivity {

    EditText edtname,edtGia;
    Button btnthem ,bttnsua,btnxoa;
    ListView lvProduct;
    product_Adapter adapter;
    Spinner sppro;
    ArrayList<CatDTO>listCat=new ArrayList<>();
    ProductDao productdao;
    catDao dao;

    cat_Adapter adaptercat;
    ArrayList<Product_DTO>list=new ArrayList<>();
    Product_DTO ojeProductDto;
    int idcat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_actyvity);
//
         edtname=findViewById(R.id.ed_nameee);
         edtGia=findViewById(R.id.ed_price);
         sppro=findViewById(R.id.spcpro);
          btnthem=findViewById(R.id.btnthemPr);
          lvProduct=findViewById(R.id.lvProduct);
          bttnsua=findViewById(R.id.btnsuaPr);
          btnxoa=findViewById(R.id.btnxoaPr);




    //set du lieu len lv rpoduc
     productdao=new ProductDao(this);
     list=productdao.getAll();
       adapter=new product_Adapter(this,R.layout.dong_pro,list);
       lvProduct.setAdapter(adapter);


//       //spinner
//        dao=new catDao(this);
//        listCat=dao.getAll();
//        adaptercat=new cat_Adapter(this,listCat);
//        sppro.setAdapter();
//        adaptercat.notifyDataSetChanged();


        //khi kich  vao spiner lay hiu lieu
        sppro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idcat=listCat.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



       btnthem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name=edtname.getText().toString().trim();
               int price= Integer.parseInt(edtGia.getText().toString().trim());
               ProductDao productDao=new ProductDao(Product_actyvity.this);
               Product_DTO product=new Product_DTO();
               product.setName(name);
               product.setPricae(price);
                product.setIdcat(idcat);
               long id= productDao.AddRow(product);
               if (id>0){
                   Toast.makeText(Product_actyvity.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                   list.clear();
                   list.addAll(productdao.getAll());
                   adapter.notifyDataSetChanged();
               }else{
                   Toast.makeText(Product_actyvity.this, "them that bai", Toast.LENGTH_SHORT).show();
               }

           }
       });

       lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

               ojeProductDto=list.get(i);
               edtname.setText(ojeProductDto.getName());
               edtGia.setText(ojeProductDto.getPricae()+"");
               sppro.setId(idcat);
               return true;
           }
       });
       bttnsua.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder=new AlertDialog.Builder(Product_actyvity.this);
               builder.setTitle("update");
               builder.setMessage("ban co muon update k");
               builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       ojeProductDto.setName(edtname.getText().toString().trim());
                       ojeProductDto.setPricae(Integer.parseInt(edtGia.getText().toString().trim()));
                       ojeProductDto.setIdcat(sppro.getId());

                       int kq=productdao.UpdateRow(ojeProductDto);
                       if (kq>0){
                           Toast.makeText(Product_actyvity.this, "update thanh cong", Toast.LENGTH_SHORT).show();
                           list.clear();
                           list.addAll(productdao.getAll());
                           adapter.notifyDataSetChanged();
                       }else{
                           Toast.makeText(Product_actyvity.this, "update that bai", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
               builder.setNegativeButton("huy",null);
               builder.show();
           }
       });


            btnxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Product_actyvity.this);
                    builder.setTitle("xoa");
                    builder.setMessage("ban co muon xoa khong ");
                    builder.setPositiveButton("xoa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int kq=productdao.deleteRow(ojeProductDto);
                            if (kq>0){
                                Toast.makeText(Product_actyvity.this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(productdao.getAll());
                                adapter.notifyDataSetChanged();
                                ojeProductDto=null;
                                edtname.setText(" ");
                                edtGia.setText(" ");


                            }else{
                                Toast.makeText(Product_actyvity.this, "xoa that bai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                  builder.setNegativeButton("huy",null);
                  builder.show();
                }
            });
    }
}