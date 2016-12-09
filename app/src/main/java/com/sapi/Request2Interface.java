package com.sapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by nyulg on 2016. 11. 30..
 */

public interface Request2Interface {
    @GET("homokozo/sapi/piacter2.php")
    Call<JSONResponse> getJSON();
}
