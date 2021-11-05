package com.example.foodvisor.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Breakfast implements Serializable {

    @SerializedName("Breakfast")
    private List<BreakfastModel> breakfast;

    public List<BreakfastModel> getBreakfast() {
        return breakfast;
    }
}
