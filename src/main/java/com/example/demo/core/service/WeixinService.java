package com.example.demo.core.service;

import com.example.demo.common.BootResult;

public interface WeixinService {

    BootResult queryChatsList();

    BootResult queryDialog(String chatId);

    BootResult queryDialogList(String chatId);

}
