package com.ap.advisor.networking.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdvisorApi {

    @GET("name/-KqqTCoEdPNtFH4vLkH5.json")
    Call<JsonObject> getName();

}
