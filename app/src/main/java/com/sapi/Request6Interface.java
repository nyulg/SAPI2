package com.sapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nyulg on 2017. 02. 22..
 */

public interface Request6Interface {
    @POST("homokozo/sapi/add_market2.php")
    Call<JSONResponse> addMarket(@Body JSONResponse addMarket);
}
