package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.CQ.Card;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface CardDao {

    //获取卡表里卡的所有信息
    @Select("select id, fullname, nickname, skill1, skill2, skill3, skill4, skill5, favourate, dislike, career, " +
            "species, sex, faction, last_time, color, full_img, small_img, partner, description, " +
            "create_time, create_user_id, modified_time, modified_user_id " +
            "from card " +
            "where deletion_state_code = 0")
    ArrayList<Card> getAllCardInfo();

    //通过id获取卡的所有信息
    @Select("select id, fullname, nickname, skill1, skill2, skill3, skill4, skill5, favourate, dislike, career, " +
            "species, sex, faction, last_time, color, full_img, small_img, partner, description, " +
            "create_time, create_user_id, modified_time, modified_user_id " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardInfoById(@Param("id") Long Id);

    //根据昵称查询卡的id
    @Select("select id " +
            "from card " +
            "where deletion_state_code = 0 and nickname regexp #{nickname}")
    Card getCardIdByNickname(@Param("nickname") String nickname);

    //根据id查询卡的全称
    @Select("select id, fullname " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardFullnameById(@Param("id") Long id);

    //根据卡的id查询卡的所有昵称
    @Select("select id, nickname " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardNicknameById(@Param("id") Long id);

    //根据卡的id查询卡的好感信息
    @Select("select id, fullname, favourate, dislike " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardHobbyById(@Param("id") Long id);

    //根据卡的id查询卡的命运伙伴
    @Select("select id, fullname, partner " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardPartnerById(@Param("id") Long id);

    //根据卡的id查询卡的技能
    @Select("select id, fullname, skill1, skill2, skill3, skill4, skill5 " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardSkillById(@Param("id") Long id);

    //根据卡的id查询卡的大图和小图的存放位置
    @Select("select id, full_img, small_img " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardImgById(@Param("id") Long id);

    //根据卡的id查询卡的性别
    @Select("select id, sex " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardSexById(@Param("id") Long id);

    //根据卡的id查询卡的物种
    @Select("select id, species " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardSpeciesById(@Param("id") Long id);

    //根据卡的id查询卡的属性
    @Select("select id, color " +
            "from card " +
            "where id = #{id} and deletion_state_code = 0")
    Card getCardColorById(@Param("id") Long id);

    //根据卡的id修改卡的全称
    @Update("update card " +
            "set fullname = #{fullname} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardFullname(@Param("id") Long id, @Param("fullname") String fullname);

    //根据卡的id修改卡的昵称
    @Update("update card " +
            "set nickname = #{nickname} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardNickname(@Param("id") Long id, @Param("nickname") String nickname);

    //根据卡的id扩展卡的昵称
    @Update("update card " +
            "set nickname = concat(nickname, #{append}) " +
            "where id = #{id} and deletion_state_code = 0")
    void appendCardNickname(@Param("id") Long id, @Param("append") String append);

    //根据卡的id查询卡的大招
    @Update("update card " +
            "set skill1 = #{skill1} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill1(@Param("id") Long id, @Param("skill1") String skill1);

    //根据卡的id查询卡的天赋技能
    @Update("update card " +
            "set skill2 = #{skill2} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill2(@Param("id") Long id, @Param("skill2") String skill2);

    //根据卡的id查询卡的队长技
    @Update("update card " +
            "set skill3 = #{skill3} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill3(@Param("id") Long id, @Param("skill3") String skill3);

    //根据卡的id查询卡的命运技
    @Update("update card " +
            "set skill4 = #{skill4} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill4(@Param("id") Long id, @Param("skill4") String skill4);

    //根据卡的id查询卡的第五个技能
    @Update("update card " +
            "set skill5 = #{skill5} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill5(@Param("id") Long id, @Param("skill5") String skill5);

    //根据卡的id查询卡的所有技能
    @Update("update card " +
            "set " +
            "skill1 = #{skill1}, " +
            "skill2 = #{skill2}, " +
            "skill3 = #{skill3}, " +
            "skill4 = #{skill4}, " +
            "skill5 = #{skill5} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSkill(@Param("id") Long id,
                         @Param("skill1") String skill1,
                         @Param("skill2") String skill2,
                         @Param("skill3") String skill3,
                         @Param("skill4") String skill4,
                         @Param("skill5") String skill5);

    //根据卡的id修改卡喜欢的物品
    @Update("update card " +
            "set favourate = #{favourate} " +
            "where deletion_state_code = 0 and id = #{id}")
    void updateCardFavourate(@Param("id") Long id, @Param("favourate") String favourate);

    //根据卡的id扩展卡喜欢的物品
    @Update("update card " +
            "set favourate = concat(favourate, #{append}) " +
            "where deletion_state_code = 0 and id = #{id}")
    void appendCardFavourate(@Param("id") Long id, @Param("append") String append);

    //根据卡的id修改卡不喜欢的物品
    @Update("update card " +
            "set dislike = #{dislike} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardDisslike(@Param("id") Long id, @Param("dislike") String dislike);

    //根据卡的id扩展不喜欢的物品
    @Update("update card " +
            "set dislike = concat(dislike, #{append}) " +
            "where id = #{id} and deletion_state_code = 0")
    void appendCardDislike(@Param("id") Long id, @Param("append") String append);

    //根据卡的id修改卡的物种
    @Update("update card " +
            "set species = #{species} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardspecies(@Param("id") Long id, @Param("species") String species);

    //根据卡的id修改卡的职业
    @Update("update card " +
            "set career = #{career} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardCareer(@Param("id") Long id, @Param("career") String career);

    //根据卡的id修改卡的性别
    @Update("update card " +
            "set sex = #{sex} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardSex(@Param("id") Long id, @Param("sex") String sex);

    //根据卡的id修改卡的阵营
    @Update("update card " +
            "set faction = #{faction} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateCardFaction(@Param("id") Long id, @Param("faction") String faction);

    //根据卡的id修改卡上一次复刻的时间
    @Update("update card " +
            "set last_time = #{lastTime} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateLastTime(@Param("id") Long id, @Param("lastTime") String lastTime);

    //根据卡的id修改卡的属性
    @Update("update card " +
            "set color = #{color} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateColor(@Param("id") Long id, @Param("color") String color);

    //根据卡的id修改卡的原图的位置
    @Update("update card " +
            "set full_img = #{imgLocation} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateFullImg(@Param("id") Long id, @Param("imgLocation") String fullImgLocation);

    //根据卡的id修改卡的小图的位置
    @Update("update card " +
            "set small_img = #{imgLocation} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateSmallImg(@Param("id") Long id, @Param("imgLocation") String smallImgLocation);

    //根据卡的id修改卡的大图和小图的位置
    @Update("update card " +
            "set full_img = #{fullImgLocation}, " +
            "small_img = #{smallImgLocation} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateAllImg(@Param("id") Long id,
                      @Param("fullImgLocation") String fullImgLocation,
                      @Param("smallImgLocation") String smallImgLocation);

    //根据卡的id修改卡的命运伙伴
    @Update("update card " +
            "set partner = #{partner} " +
            "where id = #{id} and deletion_state_code = 0")
    void updatePartner(@Param("id") Long id, @Param("partner") String partner);

    //根据卡的id修改上一次操作的信息
    @Update("update card " +
            "set description = #{desc} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateDescription(@Param("id") Long id,
                           @Param("desc") String desc);

    //根据卡的id删除卡
    @Update("update card " +
            "set deletion_state_code = 1 " +
            "where id = #{id} and deletion_state_code = 0")
    void deleteCard(@Param("id") Long id);

    //根据卡的id更新修改者的信息
    @Update("update card " +
            "set modified_time = #{time} ," +
            "modified_user_id = #{userId} " +
            "where id = #{id} and deletion_state_code = 0")
    void updateModifierInfo(@Param("id") Long id,
                            @Param("time") String modifiedTime,
                            @Param("userId") String modifiedUserId);

    //新增一张新卡，并添加创建者信息
    @Insert("insert into card" +
            "(fullname, nickname, favourate, dislike, create_time, create_user_id, modified_time, modified_user_id, deletion_state_code) value " +
            "(#{name}, #{nickname}, '', '', #{createTime}, #{createUserId}, #{createTime}, #{createUserId}, 0)")
    void createNewCard(@Param("name") String fullName,
                       @Param("nickname") String nickname,
                       @Param("createTime") String createTime,
                       @Param("createUserId") String createUserId);

    //逆向查询喜欢某个物品的卡的全称
    @Select("select fullname " +
            "from card " +
            "where deletion_state_code = 0 and favourate regexp #{item}")
    ArrayList<Card> getCardByFavourateItemName(@Param("item") String item);

    @Select("select fullname " +
            "from card " +
            "where dislike regexp #{item}")
    ArrayList<Card> getCardByDislikeItemName(@Param("item") String item);

    @Select("select fullname " +
            "from card " +
            "where faction = #{faction}")
    ArrayList<Card> getCardByFaction(@Param("faction") String faction);
}
