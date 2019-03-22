package com.limra.jaipurilohar.search;

import java.util.List;

public class StateCityModel {

    private String stateName;
    private List<String> cities;

    public StateCityModel(String stateName, List<String> cities) {
        this.stateName = stateName;
        this.cities = cities;
    }

    public String getStateName() {
        return stateName;
    }

    public List<String> getCities() {
        return cities;
    }
}
