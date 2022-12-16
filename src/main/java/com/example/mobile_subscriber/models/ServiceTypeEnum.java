package com.example.mobile_subscriber.models;

public enum ServiceTypeEnum {
    MOBILE_PREPAID("MOBILE_PREPAID"),
    MOBILE_POSTPAID("MOBILE_POSTPAID");

    private final String serviceType;
    ServiceTypeEnum(String serviceType){
        this.serviceType=serviceType;
    }
}
