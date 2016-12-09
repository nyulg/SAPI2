package com.sapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nyulg on 2016. 12. 07..
 */

public interface Request3Interface {
    @GET("homokozo/sapi/piacter0.php")
    Call<JSONResponse> getJSON();
}
