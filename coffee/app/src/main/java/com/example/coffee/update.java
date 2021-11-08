package com.example.coffee;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class update extends AppCompatActivity {
    private Button btncapnhat, btnhuybo;
    private EditText edt_capnnhat;
    private User muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edt_capnnhat=findViewById(R.id.edt_capnhat);
        btncapnhat=findViewById(R.id.btntcapnhat);
        btnhuybo=findViewById(R.id.btnhuy);
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String name =edt_capnnhat.getText().toString().trim();


        if(TextUtils.isEmpty(name)){
            return;
        }
        muser.setName(name);
        Userdatabase.getInstance(this).userDao().update(muser);
        Intent i =new Intent();
        setResult(RESULT_OK,i);
        finish();

    }
}