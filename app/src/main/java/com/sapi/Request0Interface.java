package com.sapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nyulg on 2016. 12. 09..
 */

public interface Request0Interface {
    @GET("homokozo/sapi/telefonkonyv.php")
    Call<JSONResponse> getJSON();
}
