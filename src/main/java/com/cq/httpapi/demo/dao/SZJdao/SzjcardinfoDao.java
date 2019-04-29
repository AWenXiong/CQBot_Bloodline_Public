package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SzjcardinfoDao {

    @Select("select * from szjcardinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjcardinfo> getAllCards();
}
