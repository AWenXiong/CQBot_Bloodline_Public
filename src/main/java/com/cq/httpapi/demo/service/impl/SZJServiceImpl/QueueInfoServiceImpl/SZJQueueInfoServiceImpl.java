package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjqueueinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.CreateQueueInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.CreateQueueInfoRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.CreateQueueInfoRequestDataCard;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.CreateQueueInfoResponse;
import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueCardService;
import com.cq.httpapi.demo.service.SZJService.SZJQueueInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJQueueLevelService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJQueueInfoServiceImpl implements SZJQueueInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjqueueinfoDao szjqueueinfoDao;
    @Resource
    private SZJQueueLevelService szjQueueLevelService;
    @Resource
    private SZJQueueCardService szjQueueCardService;

    @Override
    @Transactional
    public CreateQueueInfoResponse createQueueInfo(CreateQueueInfoRequest request) throws SZJException {
        String openId = request.getOpenid();
        CreateQueueInfoResponse response = new CreateQueueInfoResponse();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        ArrayList<CreateQueueInfoRequestData> levels = request.getLevel();
        if (levels == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();

            // 插入新的数据到 SZJQueueInfo 己方阵容配置表
            Long newQueueInfoId = this.insertQueueInfo(userId, groupId);
            response.setId(newQueueInfoId);

            for (CreateQueueInfoRequestData level : levels) {
                Double realLevel = level.getLevel();
                Long spellId = level.getUserSpellInfo();
                // 插入新的数据到 SZJQueueLevel 己方阵容关信息
                Long newLevelId = szjQueueLevelService.insertSzjqueuelevel(userId, newQueueInfoId, realLevel, spellId);

                ArrayList<CreateQueueInfoRequestDataCard> cards = level.getCards();
                if (cards == null) {
                    throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
                }

                for (CreateQueueInfoRequestDataCard card : cards) {
                    Long cardId = card.getCardInfoId();
                    Double position = card.getPosition();
                    // 插入新的数据到 SZJQueueCard 己方阵容卡信息表
                    Long newQueueCardId = szjQueueCardService.insertSzjqueuecard(userId, newQueueInfoId, realLevel, cardId, position);
                }
            }
            return response;
        } catch (SZJException e) {
            throw e;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public Long insertQueueInfo(Long userId, Long groupId) {
        try {
            // 设置当前用户之前该卡组的配队 Enabled = 0
            szjqueueinfoDao.updateEnabledByGroupId(groupId);
            // 插入新的数据到 SZJQueueInfo 己方阵容配置表
            szjqueueinfoDao.insertSzjqueueinfo(userId, groupId);
            // 更新相关信息
            Long newQueueInfoId = szjqueueinfoDao.getLastInsert(groupId);
            szjqueueinfoDao.updateCreateInfo(newQueueInfoId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjqueueinfoDao.updateDescription(newQueueInfoId, "新增新的自动配队");
            return newQueueInfoId;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public Szjqueueinfo getQueueInfo(Long userId, Long groupId) {
        if (userId == null || groupId == null) {
            return null;
        }
        try {
            Szjqueueinfo res = szjqueueinfoDao.getQueueInfo(userId, groupId);
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean deleteQueueInfo(Long userId, Long groupId) {
        try {
            Szjqueueinfo queueInfo = szjqueueinfoDao.getQueueInfo(userId, groupId);
            if (queueInfo != null) {
                szjqueueinfoDao.updateEnabled(queueInfo.getId(), 0);
                szjqueueinfoDao.updateDescription(queueInfo.getId(), "数据迁移-删除原有配队信息");
                szjqueueinfoDao.updateModifyInfo(queueInfo.getId(), TimeKit.getFormalTime(), "admin", "admin");
                szjqueueinfoDao.deleteById(queueInfo.getId());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
