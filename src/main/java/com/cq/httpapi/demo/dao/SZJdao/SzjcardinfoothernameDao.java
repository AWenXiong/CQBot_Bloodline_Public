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

    @Select("select * from szjcardinfoothername")
    ArrayList<Szjcardinfoothername> getAllNickname();
}
