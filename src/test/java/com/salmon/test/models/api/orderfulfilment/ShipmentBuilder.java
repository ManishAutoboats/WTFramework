package com.salmon.test.models.api.orderfulfilment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Builder
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentBuilder {

    public OrderFulfilment createNewShipmentForTheOrder() {
       return OrderFulfilment.builder()
                .items(Arrays.asList(buildShipmentItems()))
                .notify("true")
                .appendComment("true")
                .comment(buildComments())
                .tracks(Arrays.asList(buildTracks()))
                .arguments(buildArguments())
                .build();
    }

    private Items buildShipmentItems() {
        return Items.builder()
                .order_item_id("1234")
                .qty(1)
                .build();
    }

    private Comment buildComments() {
        return Comment.builder()
                .comment("test")
                .is_visible_on_front(1)
                .build();
    }

    private Tracks buildTracks() {
        return Tracks.builder()
                .track_number("1234567890")
                .title("orderShipment")
                .carrier_code("custom")
                .build();
    }

    private Arguments buildArguments() {
        ExtensionAttributes extensionAttributes = ExtensionAttributes.builder()
                .source_code("default")
                .build();

        return Arguments.builder()
                .extension_attributes(extensionAttributes)
                .build();
    }
}
