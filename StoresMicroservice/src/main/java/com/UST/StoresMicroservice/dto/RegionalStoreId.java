package com.UST.StoresMicroservice.dto;

import java.util.List;

public class RegionalStoreId {
    private  String region;
    private List<Long> storeId;

    public List<Long> getStoreId() {
        return storeId;
    }

    public void setStoreId(List<Long> storeId) {
        this.storeId = storeId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public RegionalStoreId(String region, List<Long> storeId) {
        this.region = region;
        this.storeId = storeId;
    }
}
