package com.prashant.androidcodingexercise;

import com.prashant.androidcodingexercise.utils.ConstantsUtil;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class APIAvailabilityTest {

    @Test
    public void testApiAvailability() throws Exception {
        URLConnection connection = new URL(ConstantsUtil.BASE_URL+ConstantsUtil.FACTS_JSON_KEY).openConnection();
        InputStream response = connection.getInputStream();

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                builder.append(line);
            }
        }

        assert builder.length() > 0;
    }
}
