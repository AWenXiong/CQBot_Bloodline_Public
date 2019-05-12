package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.CQ.Tower;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface TowerDao {

    //获取Tower表里所有信息
    @Select("select id, question, answer, guild, usable, description, create_time, create_user_id, modified_time, " +
            "modified_user_id " +
            "from tower")
    Tower getAllTowerInfo();

    //查看某个公会是否存在某个问题
    @Select("select count(1) " +
            "from tower " +
            "where question = #{question} and guild = #{guild}")
    Integer existQuestion(@Param("question") String question,
                          @Param("guild") String guild);

    @Select("select count(1) " +
            "from tower " +
            "where question = #{question} and guild = 0")
    Integer existGlobalQuestion(@Param("question") String question);

    //通过问题和公会号获取记录id
    @Select("select id " +
            "from tower " +
            "where question = #{question} and guild = #{guild}")
    Tower getTowerIdByQuestionAndGuild(@Param("question") String question,
                                       @Param("guild") String guild);

    //通过记录id获取问题答案
    @Select("select answer " +
            "from tower " +
            "where id = #{id}")
    String getAnswerById(@Param("id") Long id);

    // 通过群号获取该群的所有问题
    @Select("select question " +
            "from tower " +
            "where guild = #{guild}")
    ArrayList<Tower> getQuestionList(@Param("guild") String guild);

    //通过记录id更新问题答案
    @Update("update tower " +
            "set " +
            "answer = #{answer} " +
            "where " +
            "id = #{id}")
    void updateAnswer(@Param("id") Long id,
                      @Param("answer") String answer);

    //新增问答记录
    @Insert("insert into tower" +
            "(question, answer, guild) value " +
            "(#{question}, #{answer}, #{guild})")
    void insertAnswer(@Param("question") String question,
                      @Param("answer") String answer,
                      @Param("guild") String guild);

    //通过记录id删除问答记录
    @Delete("delete from tower " +
            "where id = #{id}")
    void deleteTower(@Param("id") Long id);

    //修改公会可用性
    @Update("update tower " +
            "set " +
            "usable = 1 " +
            "where " +
            "guild = #{guild}")
    void updateUsable(@Param("guild") Long guild);

    //更新操作描述
    @Update("update tower " +
            "set " +
            "description = #{desc} " +
            "where " +
            "id = #{id}")
    void updateDescription(@Param("id") Long id,
                           @Param("desc") String desc);

    //更新创建者信息
    @Update("update tower " +
            "set " +
            "create_time = #{createTime}, " +
            "create_user_id = #{createUserId} " +
            "where id = #{id}")
    void updateCreaterInfo(@Param("id") Long id,
                           @Param("createTime") String createTime,
                           @Param("createUserId") String createUserId);

    //更新修改人信息
    @Update("update tower " +
            "set " +
            "modified_time = #{modifiedTime}, " +
            "modified_user_id = #{modifiedUserId} " +
            "where " +
            "id = #{id}")
    void updateModifierInfo(@Param("id") Long id,
                            @Param("modifiedTime") String modifiedTime,
                            @Param("modifiedUserId") String modifiedUserId);

    //查看问答是否可用
    @Select("select usable from tower " +
            "where " +
            "question = #{question} and " +
            "guild = #{guild}")
    Long isUsable(@Param("question") String question,
                  @Param("guild") String guild);
}
