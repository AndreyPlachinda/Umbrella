package com.example.mvpexample.Model.POJO.Observation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConditionObservation {
    @SerializedName("response")
    @Expose
    private ResponseObservation response;

    public ResponseObservation getResponse() {
        return response;
    }

}