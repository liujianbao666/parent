package com.blueview.service.impl;

import com.blueview.dao.SupplierMapper;
import com.blueview.dao.SysDictTypeMapper;
import com.blueview.model.Supplier;
import com.blueview.model.SysDictType;
import com.blueview.service.SupplierService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    SysDictTypeMapper sysDictTypeMapper;

    @Override
    public List<Supplier> getSuppliersSelective(Supplier record) {
        return supplierMapper.getSuppliersSelective(record);
    }

    @Override
    public int insertSelective(Supplier record) {
        return supplierMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Supplier record) {
        return supplierMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Supplier selectByPrimaryKey(Integer id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Supplier> getSuppliersPageSelective(Integer start, Integer length, Supplier supplier) {
        PageHelper.offsetPage(start,length);
        List<Supplier> supplierSelective = supplierMapper.getSuppliersSelective(supplier);
        for (Supplier suppliereach:supplierSelective  ) {
            SysDictType sysDictType = new SysDictType();
            sysDictType.setCode("supplier_type");
            sysDictType.setValue(suppliereach.getSupplierType());
            List<SysDictType> sysDictTypesSelective = sysDictTypeMapper.getSysDictTypesSelective(sysDictType);
            suppliereach.setSupplierType(sysDictTypesSelective.get(0).getName());
        }
        PageInfo<Supplier> supplierPageInfo = new PageInfo<Supplier>(supplierSelective);
        return supplierPageInfo;
    }
}
