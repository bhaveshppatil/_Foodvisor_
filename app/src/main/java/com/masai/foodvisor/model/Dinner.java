package com.masai.foodvisor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Dinner implements Serializable {
    @SerializedName("Dinner")
    private List<DinnerModel> dinnerModels;

    public List<DinnerModel> getDinnerModels() {
        return dinnerModels;
    }
}

