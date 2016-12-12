package com.sapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nyulg on 2016. 12. 12..
 */

public interface Request4Interface {
    @GET("homokozo/sapi/naptar_buli.php")
    Call<JSONResponse> getJSON();
}
