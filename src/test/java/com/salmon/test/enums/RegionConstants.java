package com.salmon.test.enums;

public enum RegionConstants {

    REGION1("US","AL","Alabama"),
    REGION85("DE","HAM","Hamburg"),
    REGION189("FR","8","Ardennes"),
    REGION592("MX","JAL","Jalisco"),
    REGION593("IT","BI","Biella"),
    REGION706("CO","","Boyacá"),
    REGION774("KZ","Kostanay","Kostanay"),
    REGION819("PL","MZ","Mazowieckie"),
    REGION829("ZA","EC","Eastern Cape");

    private String country_id;
    private String country_code;
    private String region_name;

    private RegionConstants(String country_id, String country_code,String region_name ) {
        this.country_id=country_id;
        this.country_code=country_code;
        this.region_name=region_name;
    }


    public String getCountryID(){
        return country_id;
    }

    public String getCountryCode(){ return country_code;}

    public String getRegionName() {
        return region_name;
    }
}
