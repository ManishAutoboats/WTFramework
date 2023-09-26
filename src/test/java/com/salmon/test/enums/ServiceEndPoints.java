package com.salmon.test.enums;

public enum ServiceEndPoints {

    POSTS("/posts"),
    GET_POSTS_BY_ID("/posts/{id}"),
    COMMENTS("/comments"),
    GET_COMMENT_BY_ID("/comments/{id}"),
    USER_BY_EMAILID("user/{emailId}"),
    GUEST_NEWS_LETTER("/V1/newsletter/guest-details/{guestId}"),
    GET_BEARER_TOKEN("/V1/integration/admin/token"),
    GET_CUSTOMER_AUTH_BEARER_TOKEN("/V1/integration/customer/token"),
    SUBSCRIBER_NEWS_LETTER("/V1/newslettersubscriber/{subscriberId}"),
    CART_GUEST_NEWS_LETTER("/V1/guest-carts/{cartId}/newsletter-subscribe"),
    CART_SUBSCRIBER_NEWS_LETTER("/V1/carts/mine/newsletter-subscribe"),
    CUSTOMER_ACCOUNT_CREATE("/V1/customers"),
    CUSTOMER_ACCOUNT("/V1/customers/{customerId}"),
    CUSTOMER_ME("/V1/customers/me"),
    GET_SALES_ORDER("/V1/orders/{orderId}"),
    CREATE_SALES_ORDER("/V1/orders"),
    CREATE_SHIPMENT("/V1/order/{orderId}/ship"),
    GET_TRACKING_INFO_FROM_SHIPMENT("/V1/shipment/{shipId}"),
    UPDATE_TRACKING_URL("/V1/shipmenttracking/new"),
    STOCK_STATUS("/V1/inventory/source-items"),
    PRODUCTS("/V1/products"),
//    GET_MEDIA("/V1/media"),
//    GET_MEDIA_CATALOG("/V1/media/catalog/product/{productId}"),
    GET_MEDIA("/V1/products/media/types/{attributeSetName}"),
    GET_MEDIA_CATALOG("/V1/products/{sku}/media/"),
    //GET_CARTS("/V1/carts/{orderId}"),
    GET_CARTS("/V1/carts/{quoteId}"),
    CREATE_QUOTE("/V1/carts"),
    GET_COOKIES("/customer/account/create"),
    CREATE_CUSTOMER("/customer/account/createpost/"),
    CONFIRM_CUSTOMER("/customer/account/confirm/?id={customerID}&key={confirmKey}"),
    CREATE_INVOICE_BY_ORDERID("/V1/order/{orderId}/invoice"),
    EMAIL_INVOICE_BY_INVOICE_ID("/V1/invoices/{id}/emails"),
    //Your choice
    CREATE_YOUR_CHOICE_TOKEN("V1/integration/customer/yourchoicetoken"),
    GET_CUSTOMER_PASSWORD_TOKEN("V1/customers/passwordToken/{email}"),
    RESET_CUSTOMER_PASSWORD("V1/customers/resetPassword"),
    GET_CUSTOMER_BY_YOUR_CHOICE_TOKEN("V1/customers/token/{token}"),
    // ISS
    ISS_STAFF_LOGIN("/V1/issStaff/login"),
    ISS_FETCH_STORES("/V1/issStores/listAllStores"),
    ISS_GENERATE_QR_CODE("/V1/qrCode/generateQrCode"),
    ISS_EMAIL_HANDOVER("/V1/iss/emailHandover"),
    ISS_ADD_TO_CART("/V1/guest-carts/%QUOTE_ID%/items"),
    ISS_SEND_TO_CUSTOMER("/V1/issBacket/send"),
    ISS_RETRIEVE_QUOTE("/V1/issBacket/retrieve"),
    ISS_AGE_VERIFY("/V1/issCustomer/verify"),
    ISS_GET_HANDOVER("/V1/iss/getHandover");

    private String url;

    ServiceEndPoints(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
}
