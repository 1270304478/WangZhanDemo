package com.bwei.wangzhandemo.activity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bwei.wangzhandemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    @BindView(R.id.denglu_cha)
    TextView dengluCha;
    @BindView(R.id.denglu_zh)
    EditText dengluZh;
    @BindView(R.id.denglu_pwd)
    EditText dengluPwd;
    @BindView(R.id.denglu_btn)
    Button dengluBtn;
    @BindView(R.id.zhuce)
    TextView zhuce;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pref = LoginActivity.this.getSharedPreferences("text", MODE_PRIVATE);
        editor = pref.edit();
    }
    @OnClick({R.id.denglu_btn, R.id.zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_btn:
              String name = dengluZh.getText().toString();
                String password = dengluPwd.getText().toString();
                if (name.length() <= 0 && password.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                } else if (name.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (name != null && password != null) {
                    if ("13681462126".equals(name) && "123456".equals(password)) {
                        editor.putString("dengluCha", name);
                        editor.putString("dengluPwd", password);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, OneActivity.class));
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                 case R.id.zhuce:
                 startActivity(new Intent(LoginActivity.this,ZhuCeActivity.class));
                break;
        }
    }

  /* public String getTitles(){
        return "hello";
    }*/
}
