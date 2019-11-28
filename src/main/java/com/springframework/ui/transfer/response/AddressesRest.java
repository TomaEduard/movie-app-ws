package com.springframework.ui.transfer.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressesRest {

    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;

}
