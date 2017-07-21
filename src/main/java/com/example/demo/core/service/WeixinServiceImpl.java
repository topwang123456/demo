package com.example.demo.core.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BootResult;

@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

    @Override
    public BootResult queryChatsList() {
        try {
            String str = FileUtils.readFileToString(new File("/home/ap/demo/chats"));
            JSONObject ob = JSON.parseObject(str);
            str = ob.getString("data");
            JSONArray array = JSON.parseArray(str);
            return BootResult.of(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BootResult.OK();
//        JSONArray array = new JSONArray();
//        JSONObject ob = new JSONObject();
//        ob.put("id", 1);
//        ob.put("title", "Justsy Labs 工作群");
//        ob.put("text", "嘉兴嘉赛: 明天开始放假一个月");
//        ob.put("lastTime", "15:09");
//        ob.put("imgUrl", "/static/img/jiasai.jpg");
//        ob.put("noticenum", "");
//        array.add(ob);
//        JSONObject ob1 = new JSONObject();
//        ob.put("id", 2);
//        ob.put("title", "胡主席");
//        ob.put("text", "小王同志我看好你");
//        ob.put("lastTime", "6月24号");
//        ob.put("imgUrl", "/static/img/hujintao.png");
//        ob.put("noticenum", "1");
//        array.add(ob);
//        JSONObject ob2 = new JSONObject();
//        ob.put("id", 2);
//        ob.put("title", "胡主席");
//        ob.put("text", "小王同志我看好你");
//        ob.put("lastTime", "6月24号");
//        ob.put("imgUrl", "/static/img/hujintao.png");
//        ob.put("noticenum", "1");
//        array.add(ob);
//        return BootResult.of(array);
    }

    @Override
    public BootResult queryDialog(String chatId) {
        try {
            String str = FileUtils.readFileToString(new File("/home/ap/demo/dialog"));
            JSONObject ob = JSON.parseObject(str);
            str = ob.getString("data");
            JSONArray array = JSON.parseArray(str);
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getIntValue("id") == Integer.parseInt(chatId)) {
                    return BootResult.of(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BootResult.OK();
    }

    @Override
    public BootResult queryDialogList(String chatId) {
        try {
            String str = FileUtils.readFileToString(new File("/home/ap/demo/dialogList"));
            JSONObject ob = JSON.parseObject(str);
            str = ob.getString("data");
            JSONArray array = JSON.parseArray(str);
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getIntValue("id") == Integer.parseInt(chatId)) {
                    return BootResult.of(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BootResult.OK();
    }
}
