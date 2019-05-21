package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoothernameDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsOtherNameRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsOtherNameResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJCardInfoOtherNameService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class SZJCardInfoOtherNameServiceImpl implements SZJCardInfoOtherNameService {

    @Resource
    private SzjcardinfoothernameDao szjcardinfoothernameDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    /**
     * 根据卡牌别名获取该卡牌的所有别名
     *
     * @param nickname
     * @return
     */
    @Override
    public ArrayList<String> getCardNickname(String nickname) {
        nickname = "/" + nickname + "/";
        String nicknames = szjcardinfoothernameDao.getCardNickname(nickname);
        nickname.replaceAll("/", " ");
        nickname.trim();
        String[] tmp = nickname.split(" ");
        ArrayList<String> res = new ArrayList<>(Arrays.asList(tmp));
        return res;
    }

    /**
     * 获取全部卡牌别名
     *
     * @return
     */
    @Override
    public ArrayList<GetAllCardsOtherNameResponseData> getAllCardNickname(GetAllCardsOtherNameRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty()) {
            if (!szjUserInfoService.existOpenId(openId)) {
                throw new SZJException(SZJErrorCode.OPENID_ERROR);
            }
        }
        ArrayList<Szjcardinfoothername> datas = szjcardinfoothernameDao.getAllNickname();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_ALL_CARD_NICKNAME_FAILURE);
        }
        try {
            ArrayList<GetAllCardsOtherNameResponseData> res = new ArrayList<>();
            for (Szjcardinfoothername data : datas) {
                String nicknamess = data.getName();
                String[] nicknames = nicknamess.split("/");
                for (String nickname : nicknames) {
                    if (!nickname.isEmpty()) {
                        GetAllCardsOtherNameResponseData newData = new GetAllCardsOtherNameResponseData(data);
                        newData.setName(nickname);
                        res.add(newData);
                    }
                }
            }
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}
