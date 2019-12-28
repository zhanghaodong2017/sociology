package com.zhd.ultimate.sociology.service;

import com.zhd.ultimate.sociology.entity.Supplies;

import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-28 17:12
 */
public interface SuppliesService {

    List<Supplies> queryAllSupplies();

    int add(Supplies supplies);

    int update(Supplies supplies);

    Supplies querySupplies(String guid);

    int delete(String guid);
}
