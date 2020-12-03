package com.beyond.zjxt.modular.road.entity;

public class    RoadSectionDTO {
    private  int roadSectionId;
    private int highwayId;
    private String roadName;

    public int getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(int roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public int getHighwayId() {
        return highwayId;
    }

    public void setHighwayId(int highwayId) {
        this.highwayId = highwayId;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }
}
