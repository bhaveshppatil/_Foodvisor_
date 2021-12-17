package com.masai.foodvisor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Drinks implements Serializable {

    @SerializedName("Drinks")
    private List<DrinksModel> drinksModels;

    public List<DrinksModel> getDrinksModels() {
        return drinksModels;
    }
}
