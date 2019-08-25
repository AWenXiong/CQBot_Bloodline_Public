package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemycardDao;
import com.cq.httpapi.demo.dao.SZJdao.SzjenemyinfoDao;
import com.cq.httpapi.demo.dao.SZJdao.SzjenemylevelDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateEnemyInfoRequestLevel;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateEnemyInfoRequestLevelCard;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.CreateEnemyInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.GetAllEnemyInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.GetAllEnemyInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJEnemyInfoServiceImpl implements SZJEnemyInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjenemyinfoDao szjenemyinfoDao;
    @Resource
    private SzjenemylevelDao szjenemylevelDao;
    @Resource
    private SzjenemycardDao szjenemycardDao;

    @Override
    public GetEnemyInfoResponseData getEnemyInfo(GetEnemyInfoRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Szjenemyinfo data = szjenemyinfoDao.getUsable();
        if (data == null) {
            throw new SZJException(SZJErrorCode.GET_ENEMY_INFO_FAILURE);
        }

        try {
            GetEnemyInfoResponseData res = new GetEnemyInfoResponseData(data);
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public GetAllEnemyInfoResponse getAllEnemyInfo() {
        GetAllEnemyInfoResponse response = new GetAllEnemyInfoResponse();
        try {
            ArrayList<Szjenemyinfo> enemyInfos = szjenemyinfoDao.getAll();
            ArrayList<GetAllEnemyInfoResponseData> data = new ArrayList<>();
            for (Szjenemyinfo enemyInfo : enemyInfos) {
                GetAllEnemyInfoResponseData tmpData = new GetAllEnemyInfoResponseData();
                tmpData.setEnemyInfo(enemyInfo);
                data.add(tmpData);
            }
            response.setData(data);
        } catch (Exception e) {
            throw new SZJException();
        }
        return response;
    }

    @Override
    @Transactional
    public boolean EnableEnemyInfo(Long enemyInfoId) {
        try {
            ArrayList<Szjenemyinfo> allInfos = szjenemyinfoDao.getAll();
            for (Szjenemyinfo info : allInfos) {
                if (info.getEnabled() == 1) {
                    szjenemyinfoDao.updateEnabled(info.getId(), 0);
                    szjenemyinfoDao.updateModifyInfo(info.getId(), TimeKit.getFormalTime(), "admin", "admin");
                }
            }
            szjenemyinfoDao.updateEnabled(enemyInfoId, 1);
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    @Override
    @Transactional
    public CreateEnemyInfoResponse createEnemyInfo(CreateEnemyInfoRequest request) {
        CreateEnemyInfoResponse response = new CreateEnemyInfoResponse();
        try {
            szjenemyinfoDao.insertSzjenemyinfo();
            long newInfoId = szjenemyinfoDao.getMaxId();
            szjenemyinfoDao.updateCreateInfo(newInfoId, TimeKit.getFormalTime(), "admin", "admin");
            szjenemyinfoDao.updateEnabled(newInfoId, 0);
            if (updatable(request.getName())) {
                szjenemyinfoDao.updateName(newInfoId, request.getName());
            }
            if (updatable(request.getCode())) {
                szjenemyinfoDao.updateCode(newInfoId, request.getCode());
            }

            // 插入Level信息
            ArrayList<CreateEnemyInfoRequestLevel> levels = request.getLevel();
            for (CreateEnemyInfoRequestLevel level : levels) {

                szjenemylevelDao.insertSzjenemylevel();
                long newLevelId = szjenemylevelDao.getMaxId();
                szjenemylevelDao.updateCreateInfo(newLevelId, TimeKit.getFormalTime(), "admin", "admin");
                szjenemylevelDao.updateEnemyInfoId(newLevelId, newInfoId);
                if (updatable(level.getLevel())) {
                    szjenemylevelDao.updateLevel(newLevelId, level.getLevel());
                }
                if (updatable(level.getFightingCapacity())) {
                    szjenemylevelDao.updateFightingCapacity(newLevelId, level.getFightingCapacity());
                }
                if (updatable(level.getAttackDefense())) {
                    szjenemylevelDao.updateAttackDefense(newLevelId, level.getAttackDefense());
                }
                if (updatable(level.getMaxNum())) {
                    szjenemylevelDao.updateMaxNum(newLevelId, level.getMaxNum());
                }
                if (updatable(level.getAdditionOption())) {
                    szjenemylevelDao.updateAdditionOption(newLevelId, level.getAdditionOption());
                }
                if (updatable(level.getAdditionType())) {
                    szjenemylevelDao.updateAdditionType(newLevelId, level.getAdditionType());
                }
                if (updatable(level.getAdditionAmmount())) {
                    szjenemylevelDao.updateAdditionAmmount(newLevelId, level.getAdditionAmmount());
                }

                // 插入Card信息
                ArrayList<CreateEnemyInfoRequestLevelCard> cards = level.getCards();
                for (CreateEnemyInfoRequestLevelCard card : cards) {
                    szjenemycardDao.insertSzjenemycard();
                    long newCardId = szjenemycardDao.getMaxId();
                    szjenemycardDao.updateEnemyInfoId(newCardId, newInfoId);
                    szjenemycardDao.updateLevel(newCardId, level.getLevel());
                    szjenemycardDao.updateCreateInfo(newCardId, TimeKit.getFormalTime(), "admin", "admin");
                    if (updatable(card.getCardInfoId())) {
                        szjenemycardDao.updateCardInfoId(newCardId, card.getCardInfoId());
                    }
                    if (updatable(card.getIsLeader())) {
                        szjenemycardDao.updateIsLeader(newCardId, card.getIsLeader());
                    }
                }
            }
            response.setId(newInfoId);
        } catch (Exception e) {
            throw new SZJException();
        }
        return response;
    }

    private boolean updatable(Object o) {
        if (o != null) {
            return true;
        }
        return false;
    }
}
