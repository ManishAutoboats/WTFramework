package com.salmon.test.models.api.yourchoice;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetCustomerPassword {
    String email;
    String resetToken;
    String newPassword;
}
