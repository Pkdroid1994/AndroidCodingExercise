package com.prashant.androidcodingexercise.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.prashant.androidcodingexercise.view.FactsRepository;
import com.prashant.androidcodingexercise.view.FactsViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private FactsRepository factsRepository;

    public ViewModelFactory(FactsRepository factsRepository) {
        this.factsRepository = factsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (FactsViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new FactsViewModel(factsRepository);
        }

        return null;
    }
}
