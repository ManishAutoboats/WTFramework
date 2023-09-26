package com.salmon.test.step_definitions.gui;

import lombok.Getter;
import lombok.Setter;

/**
 * @author msmith
 * Order Object - for storing and asserting
 * variables within the order process
 */

// LOMBOK to ensure all getting and setters are automatically taken care of
@Getter
@Setter
public class Order {

  String productName;
  String productSku;
  String price;
  String totalCost;
  String qty;
  Boolean discountToBeApplied;
  String orderNumber;

  public Order(String productName, String price, String qty) {
    this.productName = productName;
    this.price = price;
    this.qty = qty;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Product Name : ").append(getProductName()).append("\n");
    sb.append("Product Price :").append(getPrice()).append("\n");
    sb.append("qty: ").append(getQty()).append("\n");
    sb.append("orderNumber: ").append(getOrderNumber()).append("\n");
    return sb.toString();
  }

}
