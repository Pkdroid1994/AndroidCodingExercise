package com.prashant.androidcodingexercise.rest;



import com.prashant.androidcodingexercise.data.AllFactsResponseModel;
import com.prashant.androidcodingexercise.utils.ConstantsUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(ConstantsUtil.FACTS_JSON_KEY)
    Observable<AllFactsResponseModel> getFactsList();
}
