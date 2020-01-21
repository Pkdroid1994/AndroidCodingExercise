package com.prashant.androidcodingexercise.data;


import androidx.annotation.Nullable;

import com.prashant.androidcodingexercise.utils.Status;


public class ApiResponse {
    public final Status status;

    @Nullable
    public final AllFactsResponseModel data;

    @Nullable
    public final Throwable error;

    @Nullable
    public final Integer messageId;

    public ApiResponse(Status status, @Nullable AllFactsResponseModel data, @Nullable Throwable error, Integer messageId) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.messageId = messageId;
    }
}
