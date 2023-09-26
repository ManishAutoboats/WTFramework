package com.salmon.test.models.api.customer;

import com.salmon.test.enums.Store;
import com.salmon.test.enums.RegionConstants;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.CodiceFiscaleGenerator;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.randomDateOfBirth;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerBuilder {
    Customer customer;
    String password;
    String redirectUrl;
    Integer regionID=1;
    private static String website_id=Store.valueOf(UrlBuilder.storeCode).getWebsiteID();
    private static String store_id=Store.valueOf(UrlBuilder.storeCode).getStoreId();
    private String email="auto_"+ RandomGenerator.randomEmailAddress(7);

    public CustomerBuilder newCustomer() {
        return CustomerBuilder.builder()
                .password(UrlBuilder.getMessage("loginValidPassword.key"))
                .redirectUrl("/abc/def")
                .customer(buildCustomer())
                .build();
    }

    private Customer buildCustomer() {
        return Customer.builder().created_at(new Date().toString())
                .created_in(new Date().toString())
                .confirmation("yes")
                .default_billing("yes")
                .default_shipping("yes")
                .email(email)
                .dob("1986-12-04")
                .firstname("AutoFirstName")
                .lastname("AutoLastName")
                .prefix("Mr")
                .store_id(store_id)
                .website_id(website_id)
                .gender("1")
                .group_id(1)
                .updated_at(new Date().toString())
                .addresses(buildAddresses())
                .custom_attributes(buildCustomerAttributes())
                .extension_attributes(buildExtensionAttributes())
                .build();
    }

    private List<Addresses> buildAddresses() {
        List<Addresses> addressesList = new ArrayList<>();
        Addresses addresses = Addresses.builder().city("London")
                .company("Wunderman Commerce")
                .default_billing("true")
                .default_shipping("true")
                .firstname("AutoFirstName")
                .lastname("AutoLastName")
                .country_id(Props.getCountryId())
                .fax("1234567")
                .telephone("0123456789")
                .postcode("WD17 1JJ")
                .prefix("Mr")
                .region_id(1)
                .street(getStreetList())
                .custom_attributes(buildAddressCustomerAttributes())
                .build();
        switch (UrlBuilder.storeCode) {
            case "vuse_co_es_es":
                addresses.setTelephone("+573447596217");
                addresses.setRegion_id(706);
                break;
            case "vuse_mx_es_es":
                addresses.setTelephone("+528065012269");
                addresses.setRegion_id(592);
                break;
            case "glo_it_it_it":
            case "vuse_it_it_it":
                addresses.setRegion_id(593);
                break;
            case "epok_de_de_de":
            case "velo_eu_de_de":
                addresses.setRegion_id(85);
                addresses.setStreet(Arrays.asList("Rüppurrer Str. 8f"));
                break;
            case "glo_de_de_de":
            case "vuse_de_de_de":
                addresses.setTelephone("+490123456789");
                addresses.setRegion_id(85);
                addresses.setStreet(Arrays.asList("Rüppurrer Str. 8f"));
                break;
            case "glo_pl_pl_pl":
                addresses.setRegion_id(819);
                addresses.setPostcode("22-444");
                addresses.setTelephone("+48223456889");
                break;
            case "vuse_fr_fr_fr":
                addresses.setRegion_id(189);
                addresses.setTelephone("+330123456789");
                break;
            case "glo_kz_ru_kz":
                addresses.setRegion_id(774);
                addresses.setTelephone("+71505693221");
                addresses.setPostcode("030000");
                addresses.setCity("Актобе");
                break;
            case "vuse_za_en_za":
            case "velo_za_en_za":
                addresses.setRegion_id(829);
                addresses.setTelephone("+27123456789");
                addresses.setStreet(Arrays.asList("Claredon Road", "Watford", "testSuburb"));
                break;
        }
        regionID=addresses.getRegion_id();
        addresses.setRegion(buildRegion());
        addressesList.add(addresses);
        return addressesList;
    }

    private Region buildRegion() {
        return Region.builder()
                .region_id(regionID)
                .region_code(RegionConstants.valueOf("REGION"+regionID.toString()).getCountryCode())
                .region(RegionConstants.valueOf("REGION"+regionID.toString()).getRegionName())
                .build();
    }

    private List<String> getStreetList() {
        if (UrlBuilder.getLocale().contains("pl")){
            return new ArrayList<>(Arrays.asList("Watford", "36 Claredon Road"));
        }
        else {
            return new ArrayList<>(Arrays.asList("Claredon Road", "Watford"));
        }
    }

    private List<CustomAttributes> buildCustomerAttributes() {
        List<CustomAttributes> customAttributesList = new ArrayList<>();
        customAttributesList=Arrays.asList(CustomAttributes.builder().attribute_code("consent_email_marketing").value("1").build(),
                CustomAttributes.builder().attribute_code("consent_mobile").value("1").build(),
                CustomAttributes.builder().attribute_code("is_age_verified").value("1").build(),
                CustomAttributes.builder().attribute_code("consent_market_research").value("1").build(),
                CustomAttributes.builder().attribute_code("creation_source").value("consumer").build(),
//                CustomAttributes.builder().attribute_code("consent_bat_agreement").value("1").build(),
//                CustomAttributes.builder().attribute_code("codice_fiscale")
//                        .value(new CodiceFiscaleGenerator(LocalDate.parse("1986-12-04"), true).getFiscalCode())
//                        .build(),
                CustomAttributes.builder().attribute_code("birth_city").value("Milan").build());
        List arrList = new ArrayList(customAttributesList);
        switch (UrlBuilder.storeCode) {
            case "glo_it_it_it":
            case "vuse_it_it_it":
                //LocalDate dob = randomDateOfBirth();
                CodiceFiscaleGenerator fcg = new CodiceFiscaleGenerator(LocalDate.parse("1986-12-04"), true);
                arrList.add(CustomAttributes.builder().attribute_code("codice_fiscale").value(fcg.getFiscalCode()).build());
                customAttributesList = arrList;
                break;
            case "glo_pl_pl_pl":
                arrList.add(CustomAttributes.builder().attribute_code("consent_bat_agreement").value("1").build());
                arrList.add(CustomAttributes.builder().attribute_code("is_approved").value("approved").build());
                customAttributesList = arrList;
                break;
            case "vuse_co_es_es":
                arrList.add(CustomAttributes.builder().attribute_code("citizen_card_number").value("12345").build());
                customAttributesList = arrList;
                break;
            case "vuse_mx_es_es":
                arrList.add(CustomAttributes.builder().attribute_code("referal_code").value("").build());
                arrList.add(CustomAttributes.builder().attribute_code("referee_name").value("").build());
                customAttributesList = arrList;
                break;
            case "glo_kz_ru_kz":
                arrList.add(CustomAttributes.builder().attribute_code("kz_iin").value(random(12,NUMERIC)).build());
                customAttributesList = arrList;
                break;
            case "vuse_za_en_za":
            case "velo_za_en_za":
                arrList.add(CustomAttributes.builder().attribute_code("document_value").value("8501015800088").build());
                customAttributesList = arrList;
                break;
        }
        return customAttributesList;
    }

    private List<CustomAttributes> buildAddressCustomerAttributes() {
        List<CustomAttributes> customAttributesList= new ArrayList<>();
        switch (UrlBuilder.storeCode) {
            case "vuse_mx_es_es":
                customAttributesList=Arrays.asList(CustomAttributes.builder().attribute_code("neo_ext").value("12321312").build(),
                        CustomAttributes.builder().attribute_code("colonia").value("sdfsdfs").build());
                break;
        }
        return customAttributesList;
    }

    private ExtensionAttributes buildExtensionAttributes() {
        return ExtensionAttributes.builder().is_subscribed("false")
                .build();
    }

    public void setEmail(final String updatedEmail)
    {
        email=updatedEmail;
    }

    public static void setWebsiteId(final String websiteCode)
    {
        website_id=Store.valueOf(websiteCode).getWebsiteID();
    }

    public static void setStoreId(final String websiteCode)
    {
        store_id=Store.valueOf(websiteCode).getStoreId();
    }
}
