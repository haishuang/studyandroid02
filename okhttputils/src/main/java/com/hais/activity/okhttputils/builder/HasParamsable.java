package com.hais.activity.okhttputils.builder;

import java.util.Map;

/**
 * Created by Huang hai-sen on 2016/4/26.
 */
public interface HasParamsable
{
    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

}
