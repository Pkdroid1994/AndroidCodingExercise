package com.prashant.androidcodingexercise.rest;



import com.prashant.androidcodingexercise.data.AllFactsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<AllFactsResponseModel> getFactsList();
}
