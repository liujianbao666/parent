package com.blueview.service;

import com.blueview.model.Mould;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MouldService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Mould> getMouldsSelective(Mould record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Mould record);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据id更新公司属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Mould record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param mould
     * @return
     */
    PageInfo<Mould> getPageMouldsSelective(Integer start, Integer length, Mould mould);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    Mould selectByPrimaryKey(Integer id);
}
