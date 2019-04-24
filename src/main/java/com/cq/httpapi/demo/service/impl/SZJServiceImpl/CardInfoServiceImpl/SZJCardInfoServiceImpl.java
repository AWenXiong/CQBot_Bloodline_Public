package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoDao;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.SZJCardInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJCardInfoServiceImpl implements SZJCardInfoService {

    @Resource
    private SzjcardinfoDao szjcardinfoDao;

    /**
     * 获取全部卡牌信息
     *
     * @return
     */
    @Override
    public ArrayList<Szjcardinfo> getAllCards() {
        // 处理双属性的情况
        ArrayList<Szjcardinfo> datas = szjcardinfoDao.getAllCards();
        for (Szjcardinfo data : datas) {
            String color = data.getColor();
            if (color != null && color.length() > 1) {
                Szjcardinfo newData = (Szjcardinfo) ObjectKit.deepClone(data);
                String color1 = color.substring(0, 1);
                String color2 = color.substring(1);
                newData.setColor(color1);
                newData.setColor2(color2);
                datas.remove(data);
                datas.add(newData);
            }
        }
        return datas;
    }
}
