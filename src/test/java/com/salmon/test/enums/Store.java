package com.salmon.test.enums;

public enum Store {

    admin("0", "0", "admin"),
    vuse_uk_en_gb("2", "2", "vuseuk"),
    vuse_fr_fr_fr("6", "6", "vusefr"),
    vuse_de_de_de("7", "7", "vusede"),
    vuse_dk_da_dk("8", "8", "vusedk"),
    vype_nl_nl_nl("9", "9", "vypenl"),
    vype_dk_en_gb("11", "8","vusedk"),
    vype_nl_en_gb("12", "9", "vypenl"),
    vuse_mx_es_es("17", "15", "vusemx"),
    vype_ie_en_ie("21", "17", "vypeie"),
    vuse_it_it_it("17", "15", "vuseit"),
    vuse_co_es_es("28", "24", "vuseco"),
    glo_kr_ko_kr("4", "4", "kr"),
    glo_it_it_it("10", "10", "it"),
    glo_de_de_de("18", "16", "glode"),
    glo_kz_ru_kz("23", "19", "kz"),
    glo_intl_en_gb("26", "22", "intl"),
    glo_pl_pl_pl("30", "25", "pl"),
    glo_jp_ja_jp("25", "21", "glojp"),
    lyft_at_de_de("5", "5", "lyftat"),
    lyft_se_sv_se("19", "13", "lyftse"),
    lyft_se_en_se("20", "13", "lyftse"),
    lyft_dk_da_dk("28", "24", "lyftdk"),
    lyft_dk_en_dk("29", "24", "lyftdk"),
    velo_intl_en_gb("22", "18", "velointl"),
    velo_ng_en_gb("27", "23", "velong"),
    epok_de_de_de("3", "3", "velode"),
    vuse_intl_en_gb("16", "14","vuseintl"),
    vuse_za_en_za("31", "26", "vuseza"),
    velo_eu_de_de("32","27", "velode"),
    velo_eu_be_en("35", "28", "velobe"),
    velo_eu_be_nl("33", "28", "velobe"),
    velo_eu_be_fr("34", "28", "velobe"),
    velo_eu_pl_pl("37","30", "velopl"),
    velo_za_en_za("41","26","veloza");



    private String storeId;
    private String websiteID;
    private String locale;
    private Store(String storeId, String websiteID, String locale) {
        this.storeId=storeId;
        this.websiteID=websiteID;
        this.locale=locale;
    }

    public String getWebsiteID(){
        return websiteID;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getLocale() {
        return locale;
    }
}
