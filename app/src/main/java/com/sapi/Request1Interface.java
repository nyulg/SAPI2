package com.sapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nyulg on 2016. 12. 09..
 */

public interface Request1Interface {
    @GET("homokozo/sapi/telefonkonyv_TO.php")
    Call<JSONResponse> getJSON();
}
