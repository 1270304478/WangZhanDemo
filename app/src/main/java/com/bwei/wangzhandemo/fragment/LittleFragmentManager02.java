package com.bwei.wangzhandemo.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.adpater.GuanApdater;
import com.bwei.wangzhandemo.bean.GuanBean;
import com.bwei.wangzhandemo.presenter.MainPresenter;
import com.bwei.wangzhandemo.view.IMainView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/16 11:22
 */
public class LittleFragmentManager02 extends Fragment implements IMainView {
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private MainPresenter presenter;
    private GuanApdater adapter;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.littlefragmentmanager_buju02, null);
        unbinder = ButterKnife.bind(this, view);
        presenter = new MainPresenter(this);
        presenter.get();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MainPresenter(this);
        presenter.get();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycleview.setLayoutManager(manager);
        adapter = new GuanApdater(getActivity());
        recycleview.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(GuanBean bean) {
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
