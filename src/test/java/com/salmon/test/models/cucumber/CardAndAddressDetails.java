package com.salmon.test.models.cucumber;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

@Getter
@Setter
public class CardAndAddressDetails extends PageObject {
    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    private String phoneNumberData;
    private String addressline1;
    private String city;
    private String postcode;
    private String cardNumber;
    private String cardMonth;
    private String cardYear;
    private String cardCVV;

    public CardAndAddressDetails (String addressline1, String city, String postcode, String cardNumber, String cardMonth, String cardYear, String cardCVV){
        this.addressline1=addressline1;
        this.city=city;
        this.postcode=postcode;
        this.cardNumber=cardNumber;
        this.cardMonth=cardMonth;
        this.cardYear=cardYear;
        this.cardCVV=cardCVV;
    }

    public void enterSubscriptionCardDetails(By cardNumberInput, By cardMonthInput, By cardYearInput, By cardCVVInput){
        getWebDriver().switchTo().frame(0);
        if(UrlBuilder.getLocale().contains("vuseit")){
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(2);
        }
        waitClearAndEnterText(cardNumberInput, getCardNumber());
        getWebDriver().switchTo().defaultContent();
        waitClearAndEnterText(cardMonthInput, getCardMonth());
        waitClearAndEnterText(cardYearInput, getCardYear());
        getWebDriver().switchTo().frame(1);
        if(UrlBuilder.getLocale().contains("vuseit")){
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(3);
        }
        waitClearAndEnterText(cardCVVInput, getCardCVV());
        getWebDriver().switchTo().defaultContent();
    }

    public void enterAddressDetails(By firstName, By lastName, By telephone, By address, By city, By postcode){
        waitClearAndEnterText(firstName, getFirstNameData());
        waitClearAndEnterText(lastName, getLastNameData());
        generateCorrectPhoneFormat();
        waitClearAndEnterText(telephone, getPhoneNumberData());
        waitClearAndEnterText(address, getAddressline1());
        waitClearAndEnterText(city, getCity());
        waitClearAndEnterText(postcode, getPostcode());
    }

    public void generateCorrectPhoneFormat(){
        switch (UrlBuilder.getLocale()){
            case "vuseco":
                this.phoneNumberData="6"+random(9,NUMERIC);
                break;
            case "vusefr":
                this.phoneNumberData="+336789021345";
                break;
            default:
                this.phoneNumberData="01"+random(10,NUMERIC);
        }
    }
}
