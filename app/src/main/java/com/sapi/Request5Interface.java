package com.sapi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by nyulg on 2017. 02. 21..
 */

public interface Request5Interface {
    @POST("homokozo/sapi/add_market2.php")
    Call<ServerResponse> addMarket(@Body ServerRequest addMarket);
}
