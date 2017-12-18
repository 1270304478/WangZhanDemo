package com.bwei.wangzhandemo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import com.bwei.wangzhandemo.activity.OneActivity;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
               startActivity(new Intent(getApplicationContext(), OneActivity.class));
               setResult(2);
                 finish();
               return false;
            }
        }).sendEmptyMessageDelayed(0,3000);
    }
}
