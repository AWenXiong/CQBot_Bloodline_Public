package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SzjcardinfoDao {

    //    @Select("select id as Id, id as Code, fullname as Name, " +
//            "nickname as NickName, career as Occupation, " +
//            "color as Color, sex as Sex, faction as Camp, " +
//            "description as Description, " +
//            "create_time as CreateOn, create_user_id as CreateUserId, create_user_id as CreateBy, " +
//            "modified_time as ModifiedOn, modified_user_id as ModifiedUserId, modified_user_id as ModifiedBy " +
//            "from card " +
//            "where deletion_state_code = 0")
    @Select("select * from szjcardinfo")
    ArrayList<Szjcardinfo> getAllCards();
}
