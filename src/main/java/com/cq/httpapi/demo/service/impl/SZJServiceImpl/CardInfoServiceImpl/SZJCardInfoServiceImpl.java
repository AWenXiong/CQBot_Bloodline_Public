package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetAllCardsInfoException;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.SZJService.SZJCardInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJCardInfoServiceImpl implements SZJCardInfoService {

    @Resource
    private SzjcardinfoDao szjcardinfoDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    /**
     * 获取全部卡牌信息
     *
     * @return
     */
    @Override
    public ArrayList<GetAllCardsInfoResponseData> getAllCards(GetAllCardsInfoRequest request) throws GetAllCardsInfoException {

        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty()) {
            if (!szjUserInfoService.existOpenId(openId)) {
                throw new GetAllCardsInfoException(1, "登录码不存在");
            }
        }

        ArrayList<Szjcardinfo> datas = szjcardinfoDao.getAllCards();
        if (datas == null) {
            throw new GetAllCardsInfoException(2, "获取卡牌信息失败");
        }

        // 处理双属性的情况
        try {
            ArrayList<GetAllCardsInfoResponseData> res = new ArrayList<>();
            for (Szjcardinfo data : datas) {
                String color = data.getColor();
                if (color != null && color.length() > 1) {
                    Szjcardinfo newData = (Szjcardinfo) ObjectKit.deepClone(data);
                    String color1 = color.substring(0, 1);
                    String color2 = color.substring(1);
                    newData.setColor(color1);
                    newData.setColor2(color2);
                    res.add(new GetAllCardsInfoResponseData(newData));
                } else {
                    res.add(new GetAllCardsInfoResponseData(data));
                }
            }
            return res;
        } catch (Exception e) {
            throw new GetAllCardsInfoException(9, e.getMessage());
        }
    }
}
