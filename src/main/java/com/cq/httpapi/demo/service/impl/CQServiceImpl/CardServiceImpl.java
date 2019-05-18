package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.CardDao;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.exception.CQException.AppendCardInfoException;
import com.cq.httpapi.demo.exception.CQException.CreateNewCardException;
import com.cq.httpapi.demo.exception.CQException.DeleteCardException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class CardServiceImpl implements CardService {

    @Resource
    private CardDao cardDao;

    @Override
    public boolean updateCardCareer(String nickname, String career) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardCareer(id, career);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Card getSkill(String nickname) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return null;
            } else {
                Card card = cardDao.getCardSkillById(id);
                return card;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Card getPartner(String nickname) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return null;
            } else {
                Card card = cardDao.getCardPartnerById(id);
                return card;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existCard(String nickname) {
        try {
            Long id = this.getId(nickname);
            if (id == null || (id.intValue() == -1)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardNickname(String nickname, String newNickname) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardNickname(id, "/");
                cardDao.appendCardNickname(id, newNickname);
                cardDao.appendCardNickname(id, "/");
                cardDao.updateDescription(id, "修改别名");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Card getFaction(String nickname) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return null;
            } else {
                Card card = cardDao.getCardInfoById(id);
                Card res = new Card();
                if (card.getFaction() != null) {
                    res.setFullname(card.getFullname());
                    res.setFaction(card.getFaction());
                    return res;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Card> getCardByFaction(String faction) {
        try {
            ArrayList<Card> res = cardDao.getCardByFaction(faction);
            return res;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardPartner(String nickname, String partner) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updatePartner(id, partner);
                cardDao.updateDescription(id, "更新命运伙伴");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long getId(String nickname) {
        try {
            nickname = "/" + nickname + "/";
            Card card = cardDao.getCardIdByNickname(nickname);
            if (card != null) {
                return card.getId();
            } else {
                return new Long(-1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Card getAllInfo(String nickname) {
        try {
            Card card = cardDao.getCardInfoById(this.getId(nickname));
            return card;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Card getHobby(String nickname) {
        try {
            Card card = cardDao.getCardHobbyById(this.getId(nickname));
            return card;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean createNewCard(String fullName, String createUserId)
            throws CreateNewCardException {
        try {
            cardDao.createNewCard(fullName, "/" + fullName + "/", TimeKit.getFormalTime(), createUserId);
            return true;
        } catch (CreateNewCardException e) {
            throw e;
        }
    }

    @Override
    public boolean deleteCard(String nickname, String modifiedUserId)
            throws DeleteCardException {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "删除记录");
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.deleteCard(id);
            return true;
        } catch (DeleteCardException e) {
            throw e;
        }
    }

    @Override
    public boolean appendFavourite(String nickname, String append, String modifiedUserId)
            throws AppendCardInfoException {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "追加喜欢的物品");
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.appendCardFavourate(id, append);
            return true;
        } catch (AppendCardInfoException e) {
            return false;
        }
    }

    @Override
    public boolean appendDislike(String nickname, String append, String modifiedUserId)
            throws AppendCardInfoException {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "新增不喜欢的物品，新增项目为：" + append);
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.appendCardDislike(id, append);
            return true;
        } catch (AppendCardInfoException e) {
            throw e;
        }
    }

    @Override
    public boolean updateFullName(String nickname, String fullName, String modifiedUserId) {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "修改卡的全称为：" + fullName);
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.updateCardFullname(id, fullName);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateNickName(String nickname, String newNickname, String modifiedUserId) {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "修改卡的昵称为：" + newNickname);
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.updateCardNickname(id, newNickname);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean appendNickName(String nickname, String append, String modifiedUserId)
            throws AppendCardInfoException {
        try {
            Long id = this.getId(nickname);
            cardDao.updateDescription(id, "添加卡的昵称，添加项目为：" + append);
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), modifiedUserId);
            cardDao.appendCardNickname(id, append + "/");
            return true;
        } catch (AppendCardInfoException e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSkill1(String nickname, String skill1) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardSkill1(id, skill1);
                cardDao.updateDescription(id, "更新必杀技");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSkill2(String nickname, String skill2) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardSkill2(id, skill2);
                cardDao.updateDescription(id, "更新天赋技");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSkill3(String nickname, String skill3) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardSkill3(id, skill3);
                cardDao.updateDescription(id, "更新队长技");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSkill4(String nickname, String skill4) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardSkill4(id, skill4);
                cardDao.updateDescription(id, "更新命运技");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardFaction(String nickname, String faction) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardFaction(id, faction);
                cardDao.updateDescription(id, "更新阵营");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardColor(String nickname, String color) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateColor(id, color);
                cardDao.updateDescription(id, "更新属性");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSex(String nickname, String sex) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardSex(id, sex);
                cardDao.updateDescription(id, "更新性别");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSpecies(String nickname, String species) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardspecies(id, species);
                cardDao.updateDescription(id, "更新物种");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardFavourate(String nickname, String favourate) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardFavourate(id, favourate);
                cardDao.updateDescription(id, "更新喜欢的物品");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardDislike(String nickname, String dislike) {
        try {
            Long id = this.getId(nickname);
            if (id == null) {
                return false;
            } else {
                cardDao.updateCardDisslike(id, dislike);
                cardDao.updateDescription(id, "更新不喜欢的物品");
                cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateCardSkill(String nickname, String skill1, String skill2, String skill3, String skill4) {
        try {
            Long id = this.getId(nickname);
            cardDao.updateCardSkill1(id, skill1);
            cardDao.updateCardSkill2(id, skill2);
            cardDao.updateCardSkill3(id, skill3);
            cardDao.updateCardSkill4(id, skill4);
            cardDao.updateDescription(id, "更新所有技能");
            cardDao.updateModifierInfo(id, TimeKit.getFormalTime(), "多莉贝露");
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Card> getCardByFavouriteItem(String item) {
        try {
            return cardDao.getCardByFavourateItemName(item);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Card> getCardByDislikeItem(String item) {
        try {
            return cardDao.getCardByDislikeItemName(item);
        } catch (Exception e) {
            throw e;
        }
    }
}
