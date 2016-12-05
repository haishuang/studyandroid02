package com.hais.activity.okhttputils.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by Huang hai-sen on 2016/4/26.
 */
public abstract class BitmapCallback extends Callback<Bitmap>
{
    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
