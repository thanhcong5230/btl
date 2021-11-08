package com.example.coffee;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thuchien extends AppCompatActivity {
    private TextView textView;
    private Button btnhoadon, btnthucdon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuchien);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null)
        {
            return;
        }
        User user= (User) bundle.get("key");

        textView =findViewById( R.id.tv_thuchien);
        textView.setText(user.getName());
        btnhoadon=findViewById(R.id.btnhoadon);
        btnhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thuchien.this,hoadon.class);
                startActivity(intent);
            }
        });
        btnthucdon=findViewById(R.id.btnthucdo);
        btnthucdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thuchien.this, thucdon.class);
                startActivity(intent);
            }
        });

    }

}