package com.prashant.androidcodingexercise.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.prashant.androidcodingexercise.R;
import com.prashant.androidcodingexercise.data.AllFactsResponseModel;
import com.prashant.androidcodingexercise.data.ApiResponse;
import com.prashant.androidcodingexercise.rest.ApiClient;
import com.prashant.androidcodingexercise.rest.ApiInterface;
import com.prashant.androidcodingexercise.utils.ConnectivityUtil;
import com.prashant.androidcodingexercise.utils.Status;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FactsRepository {
    private ConnectivityUtil connectivityUtil;

    public FactsRepository(ConnectivityUtil connectivityUtil) {
        this.connectivityUtil = connectivityUtil;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    private MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
    private ApiInterface apiService;


    public LiveData<ApiResponse> getAllFacts() {
        if (connectivityUtil.isNetworkAvailable()) {
            apiResponse.setValue(new ApiResponse(Status.LOADING, null, null, null));
            apiService.getFactsList().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AllFactsResponseModel>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AllFactsResponseModel allFactsResponseModel) {
                    apiResponse.setValue(new ApiResponse(Status.SUCCESS, allFactsResponseModel, null, null));
                }

                @Override
                public void onError(Throwable e) {
                    apiResponse.setValue(new ApiResponse(Status.ERROR, null, e, R.string.something_wrong));
                }

                @Override
                public void onComplete() {
                    apiResponse.setValue(new ApiResponse(Status.COMPLETED, null, null, null));
                }
            });
        } else
            apiResponse.setValue(new ApiResponse(Status.ERROR, null, null, R.string.no_internet));
        return apiResponse;

    }
}
