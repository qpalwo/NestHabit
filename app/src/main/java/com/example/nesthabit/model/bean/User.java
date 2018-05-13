package com.example.nesthabit.model.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyx on 18-5-6.
 */

public class User extends DataSupport {
    private int id;
    private String name;
    private int[] clockId;
    private int[] nestId;

}
