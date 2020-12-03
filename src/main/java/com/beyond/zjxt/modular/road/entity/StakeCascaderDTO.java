package com.beyond.zjxt.modular.road.entity;

public class StakeCascaderDTO {
    private  int roadSectionId;
    private int stakeId;
    private String stakeName;
    private  String stakeLocation;

    public int getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(int roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public int getStakeId() {
        return stakeId;
    }

    public void setStakeId(int stakeId) {
        this.stakeId = stakeId;
    }

    public String getStakeName() {
        return stakeName;
    }

    public void setStakeName(String stakeName) {
        this.stakeName = stakeName;
    }

    public String getStakeLocation() {
        return stakeLocation;
    }

    public void setStakeLocation(String stakeLocation) {
        this.stakeLocation = stakeLocation;
    }
}
