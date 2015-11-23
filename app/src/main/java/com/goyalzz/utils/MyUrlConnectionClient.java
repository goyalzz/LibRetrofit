package com.goyalzz.utils;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

public final class MyUrlConnectionClient extends UrlConnectionClient {

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = super.openConnection(request);
        connection.setConnectTimeout(60* 1000);
        connection.setReadTimeout(60 * 1000);
        return connection;
    }
}