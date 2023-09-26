package com.salmon.test.enums;

public enum DatabaseQueries {

    GET_SUBSCRIBER_BY_ID("select * from newsletter_subscriber ns inner join newsletter_subscriber_guest_details nsg on ns.subscriber_id=nsg.subscriber_id\n" +
            "where ns.subscriber_id=?"),
    GET_Registered_SUBSCRIBER_BY_ID("select * from newsletter_subscriber ns inner join customer_entity ce on ns.customer_id=? and ce.entity_id=?"),
    GET_MIN_SUBSCRIBER_ID("select * from newsletter_subscriber where subscriber_id in (select min(subscriber_id) from newsletter_subscriber where customer_id in (select entity_id from customer_entity) and customer_id<>0)\n"),
    GET_CUSTOMER_BY_ID("select * from customer_entity ce where ce.entity_id=?"),
    GET_CUSTOMER_ADDRESS_BY_ID("select * from customer_address_entity cae where cae.parent_id=?"),
    GET_CUSTOMER_ATTRIBUTES_BY_ID("(SELECT ea.attribute_code, cei.value from eav_entity_type eet inner join eav_attribute ea inner join customer_entity_int cei inner join customer_entity ce on ea.attribute_id=cei.attribute_id \n" +
            " and cei.entity_id=ce.entity_id and eet.entity_type_id=ea.entity_type_id where ce.entity_id=?)\n" +
            "UNION\n" +
            "(SELECT ea.attribute_code, cet.value from eav_entity_type eet inner join eav_attribute ea inner join customer_entity_text cet inner join customer_entity ce on ea.attribute_id=cet.attribute_id \n" +
            " and cet.entity_id=ce.entity_id and eet.entity_type_id=ea.entity_type_id where ce.entity_id=?)\n" +
            "UNION\n" +
            "(SELECT ea.attribute_code, cev.value from eav_entity_type eet inner join eav_attribute ea inner join customer_entity_varchar cev inner join customer_entity ce on ea.attribute_id=cev.attribute_id \n" +
            " and cev.entity_id=ce.entity_id and eet.entity_type_id=ea.entity_type_id where ce.entity_id=?)\n" +
            "UNION\n" +
            "(SELECT ea.attribute_code, ced.value from eav_entity_type eet inner join eav_attribute ea inner join customer_entity_decimal ced inner join customer_entity ce on ea.attribute_id=ced.attribute_id \n" +
            " and ced.entity_id=ce.entity_id and eet.entity_type_id=ea.entity_type_id where ce.entity_id=?)\n" +
            "UNION\n" +
            "(SELECT ea.attribute_code, cedt.value from eav_entity_type eet inner join eav_attribute ea inner join customer_entity_datetime cedt inner join customer_entity ce on ea.attribute_id=cedt.attribute_id \n" +
            " and cedt.entity_id=ce.entity_id and eet.entity_type_id=ea.entity_type_id where ce.entity_id=?)"),
    GET_EXTENSION_ATTRIBUTES_BY_ID("SELECT ns.subscriber_status from newsletter_subscriber ns inner join customer_entity ce on ns.customer_id=ce.entity_id where ce.entity_id=?"),
    GET_REGION_BY_ID("select dcr.region_id, dcr.default_name as region, dcr.code as region_code from directory_country_region dcr inner join customer_address_entity cae inner join customer_entity ce \n" +
            "on dcr.region_id=cae.region_id and cae.parent_id=ce.entity_id where ce.entity_id=? and cae.region_id=$"),
    DELETE_AUTO_CUSTOMERS("DELETE FROM customer_entity where email like 'auto_%'"),
    GET_SALES_ORDER("select * from sales_order where entity_id=?"),
    GET_INVENTORY("select * from inventory_source_item isi where sku='?'"),
    GET_WEBSITE_ID("select website_id from store where code='?'"),
    GET_CUSTOMER_ID_BY_EMAIL_AND_WEBSITE_ID("select entity_id,confirmation from customer_entity where website_id='{website_id}' and email='{email}'");


    private String query;

    DatabaseQueries(String query) {
        this.query = query;
    }
    public String getUrl(){
        return query;
    }
}
