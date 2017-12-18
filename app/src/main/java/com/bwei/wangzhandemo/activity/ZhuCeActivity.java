package com.bwei.wangzhandemo.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bwei.wangzhandemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class ZhuCeActivity extends Activity {
    @BindView(R.id.zhuce_fahiu)
    TextView zhuceFahiu;
    @BindView(R.id.zhuce_btn)
    Button zhuceBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.zhuce_fahiu, R.id.zhuce_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_fahiu:
                startActivity(new Intent(ZhuCeActivity.this,LoginActivity.class));
                break;
            case R.id.zhuce_btn:
                break;
        }
    }
}
