package com.salmon.test.models.api.customer;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerBuilderMultipleLocale {
    Customer customer;
    String password;
    String redirectUrl;

    public CustomerBuilderMultipleLocale newCustomer(Map<String, String> userMap) {
       return CustomerBuilderMultipleLocale.builder()
                                    .password("Pa55word")
                                    .redirectUrl("/abc/def")
                                    .customer(buildCustomer(userMap))
                                    .build();
    }

    private Customer buildCustomer(Map<String, String> userMap) {
        return Customer.builder().created_at(new Date().toString())
                .created_in(userMap.get("created_in"))
                .group_id(1)
                .confirmation("yes")
                .default_billing("yes")
                .default_shipping("yes")
                .email("battest1@mailinator.com")
                .dob("1986-12-04")
                .firstname("Auto")
                .lastname("Test")
                .prefix("Mr")
                .store_id(userMap.get("store_id"))
                .website_id(userMap.get("website_id"))
                .gender("1")
                .updated_at(new Date().toString())
                .addresses(buildAddresses(userMap))
                .custom_attributes(buildCustomerAttributes())
                .extension_attributes(buildExtensionAttributes())
                .build();
    }

    private List<Addresses> buildAddresses(Map<String, String> userMap) {
        List<Addresses> addressesList = new ArrayList<>();
        Addresses addresses = Addresses.builder().city(userMap.get("city"))
                .company("Wunderman Commerce")
                .default_billing("true")
                .default_shipping("true")
                .firstname("Auto")
                .lastname("Test")
                //.country_id("GB")
                .country_id(userMap.get("country_id"))
                .fax("1234567")
                .telephone(userMap.get("phone"))
                .postcode(UrlBuilder.getMessage("postalCode.key"))
                .prefix("Mr")
                .region_id(Integer.parseInt(userMap.get("region_id")))

                .region(buildRegion(userMap))
                .street(getStreetList(userMap))
                .build();
        addressesList.add(addresses);
        return addressesList;
    }

    private Region buildRegion(Map<String, String> userMap) {
        return Region.builder()
                .region_id(Integer.parseInt(userMap.get("region_id")))
                .region_code("AL")
                .region(userMap.get("region"))
                .build();
    }

    private List<String> getStreetList(Map<String, String> userMap) {
        return new ArrayList<>(Arrays.asList(userMap.get("street"), "Watford"));
    }

    private List<CustomAttributes> buildCustomerAttributes() {
        return Arrays.asList(CustomAttributes.builder().attribute_code("consent_email_marketing").value("1").build(),
                CustomAttributes.builder().attribute_code("consent_mobile").value("1").build(),
                CustomAttributes.builder().attribute_code("is_age_verified").value("1").build(),
                CustomAttributes.builder().attribute_code("consent_market_research").value("1").build());
    }

    private ExtensionAttributes buildExtensionAttributes() {
        return ExtensionAttributes.builder().is_subscribed("false").build();
    }
}
