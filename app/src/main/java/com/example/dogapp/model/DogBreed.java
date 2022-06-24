package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("bred_for")
    private String bredFor;
    @SerializedName("breed_group")
    private String breedGroup;
    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("origin")
    private String origin;
    @SerializedName("temperament")
    private String temperament;
    @SerializedName("height")
    private DogData height;
    @SerializedName("weight")
    private DogData weight;
    @SerializedName("url")
    private String url;

    public DogBreed(int id, String name, String bredFor, String breedGroup, String lifeSpan, String origin, String temperament, DogData height, DogData weight, String url) {
        this.id = id;
        this.name = name;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.temperament = temperament;
        this.height = height;
        this.weight = weight;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getHeight() {
        return height.metric;
    }

    public void setHeight(String height) {
        this.height.metric = height;
    }

    public String getWeight() {
        return weight.metric;
    }

    public void setWeight(String weight) {
        this.weight.metric = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class DogData {
        @SerializedName("imperial")
        private String imperial;
        @SerializedName("metric")
        private String metric;
    }
}
