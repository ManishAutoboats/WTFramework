package com.salmon.test.enums;

public enum EyesCheckpoints {
    // NOTE: just include smoke pages in this enum

    COOKIE_BANNER("Cookie Banner", true),
    AGE_GATE_RESTRICTION("Age Gate Restriction", true),
    AGE_GATE("Age Gate", true),
    HOME_PAGE("Home Page", true),
    LOGIN_PAGE("Login Page", true),
    REGISTRATION_PAGE("Registration Page", true),
    REGISTRATION_DOB_PAGE("Registration DOB Page", true),
    ACCOUNT_PAGE("Account Page", true),
    PLP("PLP", true),
    PLPHover("PLP Product Hover Page", true),
    FlAVOR_SUBSCRIPTION_MODAL("Favor Subscription Modal Page", true),
    SEARCH_RESULT_PAGE("Search Result Page", true),
    PDP("PDP", true),
    MINI_CART("MiniCart", true),
    BASKET_PAGE("Basket Page", true),
    CHECKOUT_PAGE("Checkout Page", true),
    ORDER_CONFIRMATION_PAGE("Order Confirmation Page", true),

    ACCOUNT_ADDRESS_BOOK_PAGE("Account Address Book Page", true),
    ACCOUNT_ADD_A_NEW_ADDRESS_PAGE("Account Add A New Address Page", true),
    ACCOUNT_ORDER_HISTORY_PAGE("Account Order History Page", true),
    ACCOUNT_ORDER_DETAILS_PAGE("Account Order Details Page", true),
    ACCOUNT_HISTORIC_ORDERS_PAGE("Account Historic Orders Page", true),
    ACCOUNT_SAVED_CARD_PAGE("Account Saved Card Page", true),
    ACCOUNT_NEWSLETTER_PAGE("Account Newsletter Page", true),
    ACCOUNT_DEVICE_REGISTRATION_PAGE("Account Device Registration Page", true),
    ACCOUNT_REFER_A_FRIEND_PAGE("Account Refer a Friend Page", true),
    ACCOUNT_FAVORITES_PAGE("Account Favorites Page", true),
    ACCOUNT_GLO_DEVICE_PAGE("Account Glo Device Page", true);

    private final String name;
    private final boolean switchOn;

    EyesCheckpoints(String name, boolean switchOn) {
        this.name = name;
        this.switchOn = switchOn;
    }

    public String getName() {
        return name;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }
}
