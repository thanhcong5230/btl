package com.example.coffee;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

public class login extends AppCompatActivity {
    private static final  String TAG="orderCoffe";
    AccountAuthParams authParams ;
    AccountAuthService authService;
    Button btndangnhap, btndangki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btndangnhap= findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }
        });
        btndangki=findViewById(R.id.btndangki);
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String https;
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://id5.cloud.huawei.com/IDMW/portal/userRegister/regbyemail.html?service=https%3A%2F%2Foauth-login5.cloud.huawei.com%2Foauth2%2Fv2%2Flogin%3Faccess_type%3Doffline%26client_id%3D6099200%26display%3Dpage%26flowID%3D953c4fa4-76a1-4780-99e5-b23b34e4957e%26h%3D1636296494.5980%26lang%3Den-us%26redirect_uri%3Dhttps%253A%252F%252Fdeveloper.huawei.com%252Fconsumer%252Fen%252Flogin%252Fhtml%252FhandleLogin.html%26response_type%3Dcode%26scope%3Dopenid%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fcountry%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fbase.profile%26v%3Dc9e668037434b2ed8c6eac81ad82f39de8b034b1294d597efbbbfd65194e95ab&loginUrl=https%3A%2F%2Fid5.cloud.huawei.com%3A443%2FCAS%2Fportal%2FloginAuth.html&clientID=6099200&lang=en-us&display=page&device=null&loginChannel=89000060&reqClientType=89"));
                startActivity(i);

            }
        });
    }
    ActivityResultLauncher<Intent> dangnhap =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data =result.getData();
                    Task<AuthAccount> authAccountTask= AccountAuthManager.parseAuthResultFromIntent(data);
                    if(authAccountTask.isSuccessful()){
                        AuthAccount authAccount= authAccountTask.getResult();
                        Log.i(TAG,authAccount.getDisplayName()+" dang nhap thanh cong");
                        Log.i(TAG,"idtoken +{"+ authAccount.getIdToken()+" }");

                    }else {
                       Log.i(TAG,"khong dang nhap thanh cong: " + ((ApiException)authAccountTask.getException()).getStatusCode());
                    }

                }
            }
    );

    private void dangnhap() {
        authParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).
                setIdToken().setAccessToken().createParams();
        authService= AccountAuthManager.getService(login.this,authParams);
        dangnhap.launch(authService.getSignInIntent());

    }
}