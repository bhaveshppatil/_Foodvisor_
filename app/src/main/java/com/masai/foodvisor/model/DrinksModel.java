package com.masai.foodvisor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrinksModel implements Serializable {

    @SerializedName("Images")
    private String images;

    @SerializedName("ArticleName")
    private String articleName;

    @SerializedName("Calories")
    private String calories;

    @SerializedName("Minutes")
    private String minutes;

    @SerializedName("Preparation")
    private String preparation;

    @SerializedName("Ingredients")
    private String ingredients;

    public String getImages() {
        return images;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getCalories() {
        return calories;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getIngredients() {
        return ingredients;
    }
}