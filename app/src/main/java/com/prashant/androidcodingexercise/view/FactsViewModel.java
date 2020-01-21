package com.prashant.androidcodingexercise.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.prashant.androidcodingexercise.data.ApiResponse;


public class FactsViewModel extends ViewModel {
    private MutableLiveData<Boolean> loadDataVariable = new MutableLiveData<>();
    private FactsRepository factsRepository;

    public FactsViewModel(FactsRepository factsRepository) {
        this.factsRepository = factsRepository;
        refreshData();
    }

    private LiveData<ApiResponse> apiResponseLiveData = Transformations.switchMap(loadDataVariable, id ->
            factsRepository.getAllFacts());

    public LiveData<ApiResponse> getFactsApiResponse() {
        return apiResponseLiveData;
    }


    //Refresh Data using pull to refresh
    public void refreshData() {
        loadDataVariable.setValue(true);
    }


}
