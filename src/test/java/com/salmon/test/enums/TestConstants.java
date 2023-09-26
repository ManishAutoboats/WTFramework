package com.salmon.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
* Define Constants.
* Create a new enum Class for defining new constants
*/


@AllArgsConstructor
public enum TestConstants {
    SALMON_TEST_FRAMEWORK("salmon test framework")
    ;

    @Getter
    String cssClass;
    private int intValue;
    private String stringValue;

    TestConstants(int value) {
        this.intValue = value;
    }
    TestConstants(String svalue) {
        this.stringValue = svalue;
    }
    public int getIntValue(){
        return intValue;
    }
    public String getStringValue(){
        return stringValue;
    }
}
