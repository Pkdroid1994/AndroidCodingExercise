package com.prashant.androidcodingexercise;


import com.prashant.androidcodingexercise.data.AllFactsResponseModel;
import com.prashant.androidcodingexercise.rest.ApiInterface;
import com.prashant.androidcodingexercise.utils.ConnectivityUtil;
import com.prashant.androidcodingexercise.view.FactsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class FactsRepositoryTest {

    @Mock
    private ConnectivityUtil connectivityUtil;

    @Mock
    private FactsRepository factsRepository;

    @Mock
    private ApiInterface apiInterface;

    @Before
    public void setUpRepository() {
        MockitoAnnotations.initMocks(this);
        factsRepository = Mockito.spy(new FactsRepository(connectivityUtil));
    }

    @Test
    public void testWithDataPresent() {

        Mockito.when(apiInterface.getFactsList()).thenReturn(Observable.just(new AllFactsResponseModel()));

        TestObserver<AllFactsResponseModel> allFactsResponseModelTestObserver = new TestObserver<>();

        Observable<AllFactsResponseModel> result = apiInterface.getFactsList();
        result.subscribe(allFactsResponseModelTestObserver);
        allFactsResponseModelTestObserver.assertComplete();


    }

    @Test
    public void testWithErrorState() {
        Exception exception = new Exception();
        Mockito.when(apiInterface.getFactsList()).thenReturn(Observable.error(exception));

        TestObserver<AllFactsResponseModel> allFactsResponseModelTestObserver = new TestObserver<>();

        Observable<AllFactsResponseModel> result = apiInterface.getFactsList();
        result.subscribe(allFactsResponseModelTestObserver);
        allFactsResponseModelTestObserver.assertError(exception);
    }

}
