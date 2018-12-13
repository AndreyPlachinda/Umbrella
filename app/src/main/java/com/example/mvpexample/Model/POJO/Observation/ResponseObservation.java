package com.example.mvpexample.Model.POJO.Observation;

import com.google.gson.annotations.SerializedName;

public class ResponseObservation {
    @SerializedName("ob")
    private ObservationData observationData;

    public ObservationData getObservationData() {
        return observationData;
    }
}
