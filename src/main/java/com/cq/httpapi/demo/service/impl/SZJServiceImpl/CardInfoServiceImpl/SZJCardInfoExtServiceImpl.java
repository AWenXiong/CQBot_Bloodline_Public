package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoextDao;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoext;
import com.cq.httpapi.demo.service.SZJCardInfoExtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJCardInfoExtServiceImpl implements SZJCardInfoExtService {

    @Resource
    SzjcardinfoextDao szjcardinfoextDao;

    @Override
    public ArrayList<Szjcardinfoext> getAllCardInfoExt() {
        return szjcardinfoextDao.getAll();
    }

}
