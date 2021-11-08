package com.example.coffee;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private Button them;
    private static final int MyRequestcode=10;
    // private UserViewmodel userViewmodel;
    private List<User> userList;
    // private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvUser = findViewById(R.id.rcv_user);

        them=findViewById(R.id.btnadd);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(Gravity.CENTER);

            }
        });
        userAdapter = new UserAdapter(new Clickitem() {
            @Override
            public void onClickitemtable(User user) {
                onClicltothuchien( user);
            }

            @Override
            public void clickdelete(User user) {
                clickondelete(user);
            }

            @Override
            public void clickupdate(User user) {
                onclickupdate(user);
            }
        });
        userList = new ArrayList<>();
        userAdapter.setData(userList);
        load();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvUser.setLayoutManager(gridLayoutManager);
        rcvUser.setAdapter(userAdapter); //code them
       /* btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickontable();
            }
        });*/



    }
    private void onClicltothuchien(User user)
    {
        Intent intent = new Intent(this,thuchien.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }





    /* private void clickontable() {
         String strName= edtban.getText().toString().trim();

         if(TextUtils.isEmpty(strName)){
             return;
         }
         User user = new User(R.drawable.ban,"Bàn số " +strName);
         if(isUserExit(user))
         {
             Toast toast =Toast.makeText(this,"Kiểm tra lại thông tin bàn vừa nhập",Toast.LENGTH_LONG);
             toast.show();
             return;
         }
         Userdatabase.getInstance(this).userDao().insert(user);
         Toast toast =Toast.makeText(this,"Ban Đã thêm bàn thành công",Toast.LENGTH_LONG);
         toast.show();
         edtban.setText("");
         load();
         anbanphim();
     }*/
    private void load(){
        userList=Userdatabase.getInstance(this).userDao().getListUser();
        userAdapter.setData(userList);
    }
    private boolean isUserExit(User user)
    {
        List<User> list =Userdatabase.getInstance(this).userDao().checktable(user.getName());
        return list !=null&& !list.isEmpty();
    }
    private void clickondelete( final User user){
        new AlertDialog.Builder(this)
                .setTitle("Bạn đang chọn xóa bàn")
                .setMessage("Bạn có muốn xóa không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // thực hiện xóa bàn
                        Userdatabase.getInstance(MainActivity.this).userDao().delete(user);
                        Toast toast=Toast.makeText(MainActivity.this,"Bạn đã xóa bàn thành công",Toast.LENGTH_LONG);
                        toast.show();;
                        load();

                    }
                })
                .setNegativeButton("Không", null)
                .show();

    }
    //ẩn bàn phím
    public void anbanphim()
    { try {
        InputMethodManager inputMethodManager=(InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }catch (NullPointerException ex)
    {
        ex.printStackTrace();
    }
    }
    // Mở dialog
    private void open(int gravity)
    {
        final Dialog dialog =new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.themban);
        Window window =dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowastribute = window.getAttributes();
        windowastribute.gravity =gravity;
        window.setAttributes(windowastribute);
        if(Gravity.CENTER==gravity)
        {
            dialog.setCancelable(false);
        }
// thêm bàn
        EditText edt_themban=dialog.findViewById(R.id.edt_themban);
        Button btnhuybo =dialog.findViewById(R.id.btnhuybo);
        Button btndongy = dialog.findViewById(R.id.btntdongy);

        btnhuybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });dialog.show();
        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName= edt_themban.getText().toString().trim();

                if(TextUtils.isEmpty(strName)){
                    return;
                }
                User user = new User(R.drawable.ban,"Bàn số " +strName);
                if(isUserExit(user))
                {
                    Toast toast =Toast.makeText(MainActivity.this,"Bàn Đã Tồn Tại",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Userdatabase.getInstance(MainActivity.this).userDao().insert(user);
                Toast toast =Toast.makeText(MainActivity.this,"Ban Đã thêm bàn thành công",Toast.LENGTH_LONG);
                toast.show();
                load();
                anbanphim();
                dialog.dismiss();
            }



        });


    }
    // mở dialog update
   /*private void onclickupdate(int gravity){final Dialog dialog =new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_update);
        Window window =dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowastribute = window.getAttributes();
        windowastribute.gravity =gravity;
        window.setAttributes(windowastribute);
        if(Gravity.CENTER==gravity)
        {
            dialog.setCancelable(false);
        }
// update
        EditText edt_capnhat=dialog.findViewById(R.id.edt_capnhat);
        Button btnhuy =dialog.findViewById(R.id.btnhuy);
        Button btncapnhat = dialog.findViewById(R.id.btntcapnhat);

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });dialog.show();
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onClick(View v) {
                String strName= edt_capnhat.getText().toString().trim();

                if(TextUtils.isEmpty(strName)){
                    return;
                }
               // User user = new User(R.drawable.ban,"Bàn số " +strName);
                mUser.setName(strName);
             /*   if(isUserExit(mUser))
                {
                    Toast toast =Toast.makeText(MainActivity.this,"Kiểm tra lại thông tin bàn vừa nhập",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }*/
              /*  Userdatabase.getInstance(MainActivity.this).userDao().update(mUser);
                Toast toast =Toast.makeText(MainActivity.this,"Ban Đã thêm bàn thành công",Toast.LENGTH_LONG);
                Intent intent =new Intent();

                finish();
                toast.show();
                load();
                anbanphim();
                dialog.dismiss();
            }



        });

    }*/
    //update
    private void onclickupdate(User user){
        Intent intent = new Intent(this,update.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key1",user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MyRequestcode);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            load();
        }
    }
}

