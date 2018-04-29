package com.example.nesthabit.presenter;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.base.BasePresent;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.fragment.NestView;
import com.example.nesthabit.model.NestHelper;
import com.example.nesthabit.model.UserHelper;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;

public class NestFraPresenter extends BasePresent<NestView> {

    public void setData(){
        UserHelper userHelper = new UserHelper();
        userHelper.getNest(AVUser.getCurrentUser(), new CallBack<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                NestHelper nestHelper = new NestHelper();
                nestHelper.getNests(data, new CallBack<List<Nest>>() {
                    @Override
                    public void onSuccess(List<Nest> data) {
                        if (isViewAttached()){
                            getView().setNestRecyclerData(data);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
