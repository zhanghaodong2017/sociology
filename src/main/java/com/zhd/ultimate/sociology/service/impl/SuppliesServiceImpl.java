package com.zhd.ultimate.sociology.service.impl;

import com.zhd.ultimate.sociology.entity.Supplies;
import com.zhd.ultimate.sociology.mapping.SuppliesMapper;
import com.zhd.ultimate.sociology.service.SuppliesService;
import com.zhd.ultimate.sociology.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-28 17:14
 */
@Service
public class SuppliesServiceImpl implements SuppliesService {

    @Autowired
    private SuppliesMapper suppliesMapper;

    @Override
    public List<Supplies> queryAllSupplies() {
        return suppliesMapper.queryAllSupplies();
    }

    @Override
    public int add(@RequestParam("supplies") Supplies supplies) {
        supplies.setGuid(Utils.uuid());
        supplies.setCreateTime(new Date());
        supplies.setUpdateTime(supplies.getCreateTime());
        return suppliesMapper.insert(supplies);
    }

    @Override
    public int update(@RequestParam("supplies") Supplies supplies) {
        supplies.setUpdateTime(new Date());
        return suppliesMapper.updateByPrimaryKeySelective(supplies);
    }

    @Override
    public Supplies querySupplies(String guid) {
        return suppliesMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int delete(String guid) {
        return suppliesMapper.deleteByPrimaryKey(guid);
    }
}
