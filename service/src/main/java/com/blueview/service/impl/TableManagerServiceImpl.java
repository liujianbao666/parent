package com.blueview.service.impl;

import com.blueview.dao.TableManageMapper;
import com.blueview.model.TableManage;
import com.blueview.service.TableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TableManagerServiceImpl implements TableManagerService {
    @Autowired
    TableManageMapper tableManageMapper;
    @Override
    public int insertSelective(TableManage record) {
        return tableManageMapper.insertSelective(record);
    }

    @Override
    public List<TableManage> getTableManagerSelective(TableManage record){
        return tableManageMapper.getTableManagerSelective(record);
    }
}
