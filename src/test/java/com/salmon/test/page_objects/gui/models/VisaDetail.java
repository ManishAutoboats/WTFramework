package com.salmon.test.page_objects.gui.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VisaDetail {

  String cc_holderName;
  String cc_number;
  String expire_month;
  String expire_year;
  String securityCode;
  String tickTC;
  String errorType;

}

