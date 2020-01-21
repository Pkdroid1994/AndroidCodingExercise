package com.prashant.androidcodingexercise;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.prashant.androidcodingexercise.data.ApiResponse;
import com.prashant.androidcodingexercise.view.FactsRepository;
import com.prashant.androidcodingexercise.view.FactsViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class FactsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FactsRepository factsRepository;

    private Observer observer;

    private FactsViewModel factsViewModel;

    private LiveData<ApiResponse> apiResponseLiveData=new MutableLiveData<>();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        factsViewModel=new FactsViewModel(factsRepository);
        factsViewModel.getFactsApiResponse().observeForever(observer);

    }

    @Test
    public void is_viewModel_has_observer()
    {
        Assert.assertTrue(factsViewModel.getFactsApiResponse().hasObservers());
    }

    @Test
    public void testNull() {
        when(factsRepository.getAllFacts()).thenReturn(null);
        Assert.assertNull(factsRepository.getAllFacts());
    }

    @Test
    public void test_when_not_null()
    {
        when(factsRepository.getAllFacts()).thenReturn(apiResponseLiveData);
        Assert.assertNotNull(apiResponseLiveData);

    }


}
