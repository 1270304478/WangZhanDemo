package com.bwei.wangzhandemo.presenter;
import com.bwei.wangzhandemo.bean.GuanBean;
import com.bwei.wangzhandemo.model.MainModelImpl;
import com.bwei.wangzhandemo.model.ModelCallBack;
import com.bwei.wangzhandemo.view.IMainView;

public class MainPresenter {


    private IMainView iView ;
    private MainModelImpl mainModel ;
    public MainPresenter(IMainView view){
        this.iView = view;
        this.mainModel = new MainModelImpl();


    }


    public void get(){

        mainModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(GuanBean bean) {

                if(iView != null){
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if(iView != null){
                    iView.onFailure(e);
                }
            }
        });
    }


}
