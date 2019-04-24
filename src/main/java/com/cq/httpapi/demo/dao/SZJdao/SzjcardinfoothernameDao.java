package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SzjcardinfoothernameDao {

    @Select("select nickname " +
            "from szjcardinfo " +
            "where nickname regexp #{nickname} and deletion_state_code = 0")
    String getCardNickname(@Param("nickname") String nickname);

    //    @Select("select id as id, id as CardInfoId, nickname as Name," +
//            "description as Description, " +
//            "create_time as CreateOn, create_user_id as CreateUserId, create_user_id as CreateBy, " +
//            "modified_time as ModifiedOn, modified_user_id as ModifiedUserId, modified_user_id as ModifiedBy " +
//            "from card " +
//            "where deletion_state_code = 0")
    @Select("select * from ")
    ArrayList<Szjcardinfoothername> getAllNickname();
}
