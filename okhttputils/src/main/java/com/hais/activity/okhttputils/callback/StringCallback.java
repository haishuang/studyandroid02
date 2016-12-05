package com.hais.activity.okhttputils.callback;


import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Huang hai-sen on 2016/4/26.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }

}
