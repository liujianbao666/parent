package com.blueview.dingTalk;

import com.blueview.DingTalkConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiSmartworkHrmEmployeeListRequest;
import com.dingtalk.api.request.OapiSmartworkHrmEmployeeQueryonjobRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiSmartworkHrmEmployeeListResponse;
import com.dingtalk.api.response.OapiSmartworkHrmEmployeeQueryonjobResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DingTalkServiceImpl implements DingTalkService {

    @Override
    public String Gettoken() {
        String accessToken = null;
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(DingTalkConstant.appkey);
        request.setAppsecret(DingTalkConstant.appsecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
            accessToken =  response.getAccessToken();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public List<String> queryonjob(String status_list, int offset, int size) {

        List<String> result = new ArrayList<String>();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/queryonjob");
        OapiSmartworkHrmEmployeeQueryonjobRequest req = new OapiSmartworkHrmEmployeeQueryonjobRequest();
        req.setStatusList("2,3,5,-1");
        req.setOffset(0L);
        req.setSize(500L);
        OapiSmartworkHrmEmployeeQueryonjobResponse response = null;
        try {
            response = client.execute(req ,Gettoken());
            result =  response.getResult().getDataList();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String registerCallBack() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
        request.setUrl("http://test001.vaiwan.com/eventreceive");
        request.setAesKey("1234567890123456789012345678901234567890123");
        request.setToken("123456");
        request.setCallBackTag(Arrays.asList("user_add_org", "user_modify_org", "user_leave_org"));
        OapiCallBackRegisterCallBackResponse response = null;
        try {
            response = client.execute(request,Gettoken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return response.getErrmsg();
    }

    @Override
    public OapiSmartworkHrmEmployeeListResponse getEmployeeInfo(String userid_list,String field_filter_list){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list");
        OapiSmartworkHrmEmployeeListRequest req = new OapiSmartworkHrmEmployeeListRequest();
        req.setUseridList("123, 234");
        req.setFieldFilterList("sys00-name,sys00-email");
        OapiSmartworkHrmEmployeeListResponse rsp = null;
        try {
            rsp = client.execute(req, Gettoken());
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return rsp;

    }
}
