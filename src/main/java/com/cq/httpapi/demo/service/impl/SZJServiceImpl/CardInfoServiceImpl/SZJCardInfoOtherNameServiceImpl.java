package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoothernameDao;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.SZJCardInfoOtherNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class SZJCardInfoOtherNameServiceImpl implements SZJCardInfoOtherNameService {

    @Resource
    private SzjcardinfoothernameDao szjcardinfoothernameDao;

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
    public ArrayList<Szjcardinfoothername> getAllCardNickname() {
        ArrayList<Szjcardinfoothername> datas = szjcardinfoothernameDao.getAllNickname();
        ArrayList<Szjcardinfoothername> res = new ArrayList<>();
        for (Szjcardinfoothername data : datas) {
            String nicknamess = data.getName();
            String[] nicknames = nicknamess.split("/");
            for (String nickname : nicknames) {
                if (!nickname.isEmpty()) {
                    Szjcardinfoothername newData = (Szjcardinfoothername) ObjectKit.deepClone(data);
                    newData.setName(nickname);
                    res.add(newData);
                }
            }
        }
        return res;
    }
}
