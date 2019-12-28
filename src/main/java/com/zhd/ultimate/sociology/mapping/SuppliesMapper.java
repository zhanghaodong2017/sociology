package com.zhd.ultimate.sociology.mapping;

import com.zhd.ultimate.sociology.entity.Supplies;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliesMapper {
    int deleteByPrimaryKey(String guid);

    int insert(Supplies record);

    int insertSelective(Supplies supplies);

    Supplies selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(Supplies supplies);

    int updateByPrimaryKey(Supplies supplies);

    List<Supplies> queryAllSupplies();
}