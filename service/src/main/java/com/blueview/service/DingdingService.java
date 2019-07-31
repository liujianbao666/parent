package com.blueview.service;

import com.dingtalk.api.response.OapiSmartworkHrmEmployeeListResponse;

import java.util.List;

public interface DingdingService {
    /**
     * 获取tocken
     * @return
     */
    String Gettoken();

    /**
     * 分页查询企业在职员工userid列表。
     * @param status_list
     * @param offset
     * @param size
     * @return
     */
    List<String> queryonjob(String status_list, int offset, int size);

    /**
     * 注册业务事件回调接口
     * @return
     */
    String registerCallBack();

    /**
     * 根据员工userid，批量访问员工花名册字段信息。
     * @param userid_list
     * @param field_filter_list
     * @return
     */
    OapiSmartworkHrmEmployeeListResponse getEmployeeInfo(String userid_list, String field_filter_list);
}
