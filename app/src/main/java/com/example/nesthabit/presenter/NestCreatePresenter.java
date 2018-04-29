package com.example.nesthabit.presenter;

import com.example.nesthabit.activity.NestCreateView;
import com.example.nesthabit.base.BasePresent;
import com.example.nesthabit.model.NestHelper;
import com.example.nesthabit.model.bean.Nest;

public class NestCreatePresenter extends BasePresent<NestCreateView> {
    public void createNest(Nest nest){
        NestHelper nestHelper = new NestHelper();
        nestHelper.createNestOnNet(nest);
    }
}
