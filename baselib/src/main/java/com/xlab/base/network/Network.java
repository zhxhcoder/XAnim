package com.xlab.base.network;

import com.xlab.base.utils.CommonUtils;

/**
 * Created by zhxh on 2018/11/1
 */
public class Network {

    //端口号
    public static final int PORT = 8991;
    private static final String API_URL = "https://api.niuguwang.com";
    private static final String TR_URL = "https://str.niuguwang.com";

    public static void processPackage(final DataPackage dataPackage) throws Exception {

        int requestID = dataPackage.getRequestID();

        StringBuffer url = new StringBuffer();

        switch (requestID) {
            case RequestCommand.COMMAND_BULLBAO_SCORE_RECORD:
                url.append(API_URL);
                url.append("/task/v01/score/Records");
                break;
            case RequestCommand.COMMAND_APP_PUSH_CONFIG:
                url.append(TR_URL);
                url.append("/api/appconfig.ashx");
                break;
            default:
                break;
        }

        if (CommonUtils.isNull(url.toString().trim()))
            throw new Exception("error request 0x"
                    + Integer.toHexString(requestID));

        dataPackage.setRequestUrl(url.toString());

        OkHttpTools okHttpTools = new OkHttpTools();

        if ("GET".equals(dataPackage.getRequestMethod())) {
            okHttpTools.httpGet(url.toString(), dataPackage);
        } else {
            okHttpTools.httpPost(url.toString(), dataPackage);
        }
    }

}
