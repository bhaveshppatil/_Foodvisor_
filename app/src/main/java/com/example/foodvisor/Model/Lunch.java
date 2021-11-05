package com.example.foodvisor.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Lunch implements Serializable {

    @SerializedName("Lunch")
    private List<LunchModel> lunchModels;

    public List<LunchModel> getLunchModels() {
        return lunchModels;
    }
}
