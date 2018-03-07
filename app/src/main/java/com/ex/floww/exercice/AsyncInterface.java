package com.ex.floww.exercice;



import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Floww on 07/03/2018.
 */

public interface AsyncInterface {
    @GET("facts.json")
    Call<JsonObject> getJson();
}
