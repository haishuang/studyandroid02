package com.hais.activity.okhttputils.builder;


import com.hais.activity.okhttputils.OkHttpUtils;
import com.hais.activity.okhttputils.request.OtherRequest;
import com.hais.activity.okhttputils.request.RequestCall;

/**
 * Created by Huang hai-sen on 2016/4/26.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
