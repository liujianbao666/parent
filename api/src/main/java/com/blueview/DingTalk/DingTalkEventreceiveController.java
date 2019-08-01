package com.blueview.DingTalk;

import com.alibaba.fastjson.JSONObject;
import com.blueview.dingTalk.DingTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DingTalkEventreceiveController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/7/26
 * @Version V1.0
 * 注册钉钉事件回调
 **/
@RequestMapping("/api")
@RestController
public class DingTalkEventreceiveController {
    @Autowired
    DingTalkService dingTalkService;

    @RequestMapping("/eventreceive")
    public JSONObject eventreceive(JSONObject event) {
        JSONObject resultJson = new JSONObject();
        if("check_url".equals(event.get("EventType"))){
            System.out.println("收到钉钉发送的测试回调URL，并返回success");
            resultJson.put("msg_signature","111108bb8e6dbce3c9671d6fdb69d15066227608");
            resultJson.put("timeStamp",System.currentTimeMillis());
            resultJson.put("nonce","123456");
            resultJson.put("encrypt","1ojQf0NSvw2WPvW7LijxS8UvISr8pdDP+rXpPbcLGOmIBNbWetRg7IP0vdhVgkVwSoZBJeQwY2zhROsJq/HJ+q6tp1qhl9L1+ccC9ZjKs1wV5bmA9NoAWQiZ+7MpzQVq+j74rJQljdVyBdI/dGOvsnBSCxCVW0ISWX0vn9lYTuuHSoaxwCGylH9xRhYHL9bRDskBc7bO0FseHQQasdfghjkl");
        }else if("user_add_org".equals(event.get("EventType"))){//各种事务处理

        }
        return resultJson;
    }


}
