package com.example.nesthabit.fragment;

import com.example.nesthabit.base.BaseView;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;

public interface NestView extends BaseView {
    void setNestRecyclerData(List<Nest> list);
}
