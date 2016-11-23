package com.sapi;
import com.sapi.ServerRequest;
import com.sapi.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nyulg on 2016. 11. 12..
 */

public interface RequestInterface {

  @POST("homokozo/sapi/index.php")
  Call<ServerResponse> operation(@Body ServerRequest request);

    }
