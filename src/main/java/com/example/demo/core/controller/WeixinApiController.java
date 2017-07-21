package com.example.demo.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.BootResult;
import com.example.demo.core.service.WeixinService;

@RestController
@RequestMapping("/wexinApi")
public class WeixinApiController {
    @Autowired
    private WeixinService weixinService;
    
    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public BootResult queryChatsList(){
        return weixinService.queryChatsList();
    }
    
    @RequestMapping(value = "/dialog/{chatId}", method = RequestMethod.GET)
    public BootResult queryDialog(@PathVariable String chatId){
        return weixinService.queryDialog(chatId);
    }
    
    @RequestMapping(value = "/dialogList/{chatId}", method = RequestMethod.GET)
    public BootResult queryDialogList(@PathVariable String chatId){
        return weixinService.queryDialogList(chatId);
    }
}
