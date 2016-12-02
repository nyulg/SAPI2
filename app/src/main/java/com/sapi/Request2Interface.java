package com.sapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nyulg on 2016. 11. 30..
 */

public interface Request2Interface {
    @GET("homokozo/sapi/piacter.php")
    Call<JSONResponse> getJSON();
}
