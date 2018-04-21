package com.example.nesthabit.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.model.bean.Message;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;

public class NestHelper {

    public void createNest(String name, String desc, int members_limit, long start_time,
                           int challenge_days, String cover_image, int open, long create_time,
                           String creator, String ownwe, int member_amount){
        Nest nest = new Nest();
        nest.setChallengeDays(challenge_days);
        nest.setCoverImage(cover_image);
        nest.setCreatedTime(create_time);
        nest.setCreator(AVUser.getCurrentUser());
        nest.setDesc(desc);
        nest.setMemberAmount(member_amount);
        nest.setMembersLimit(members_limit);
        nest.setName(name);
        nest.setOpen(open);
        nest.setOwner(AVUser.getCurrentUser());
        nest.setStartTime(start_time);
    }

    public void deleteNest(Nest nest){
        try {
            nest.delete();
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Nest nest, AVUser user){
        nest.add(Nest.MEMBERS, user);
    }

    public void addSignMsg(Nest nest, Message message){
        nest.add(Nest.SIGNMSGS, message);
    }

    public void addCommuMsg(Nest nest, Message message){
        nest.add(Nest.COMMUMSGS, message);
    }


    public void updata(){

    }
}
