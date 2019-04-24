package com.cq.httpapi.demo.service.impl.SZJServiceImpl.BackEndDataServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjitemssexDao;
import com.cq.httpapi.demo.entity.SZJ.Szjitemssex;
import com.cq.httpapi.demo.service.SZJItemsSexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SZJItemsSexServiceImpl implements SZJItemsSexService {

    @Resource
    private SzjitemssexDao szjitemssexDao;

    @Override
    public long insert(Szjitemssex szjitemssex) {
        szjitemssexDao.insertSzjitemssex(szjitemssex.getItemCode(), szjitemssex.getItemName(), szjitemssex.getItemValue());
        long maxId = szjitemssexDao.getMaxId();
        return maxId;
    }

    @Override
    public long delete(Szjitemssex szjitemssex) {
        szjitemssexDao.deleteById(szjitemssex.getId());
        return szjitemssex.getId();
    }

    @Override
    public long update(Szjitemssex szjitemssex) {
        return 0;
    }

    @Override
    public Szjitemssex getById(long id) {
        return szjitemssexDao.getById(id);
    }
}
