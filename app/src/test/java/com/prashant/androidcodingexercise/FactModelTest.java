package com.prashant.androidcodingexercise;

import com.prashant.androidcodingexercise.data.FactModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FactModelTest {

    private final String title = "Fact Title";
    private final String description = "Fact Description";
    private final String imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";


    @Mock
    private FactModel factModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(factModel.getDescription()).thenReturn(description);
        Mockito.when(factModel.getImageHref()).thenReturn(imageHref);
    }

    @Test
    public void test_title() {
        Mockito.when(factModel.getTitle()).thenReturn(title);
        Assert.assertEquals(title, factModel.getTitle());
    }

    @Test
    public void test_description() {
        Mockito.when(factModel.getDescription()).thenReturn(description);
        Assert.assertEquals(description, factModel.getDescription());
    }

    @Test
    public void test_image_url() {
        Mockito.when(factModel.getImageHref()).thenReturn(imageHref);
        Assert.assertEquals(imageHref, factModel.getImageHref());
    }
}
