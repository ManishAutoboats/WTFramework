package com.salmon.test.page_objects.gui.constants;

import com.salmon.test.page_objects.gui.models.RegistrationPageModel.Address;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.enums.PermittedCharacters.NUMERIC;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public enum Locale {
    GLODE("glode") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("12 The Cloisters")
                    .city(random(6, ALPHABETS))
                    .postcode("UB10 9DW")
                    .country("Deutschland")
                    .phoneNumber("11" + random(10, NUMERIC))
                    .build();
        }
    },
    IT("it") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("val")
                    .city("Badia")
                    .postcode("39036")
                    .country("Italia")
                    .phoneNumber("8899125463")
                    .build();
        }
    },
    VYPEIT("vypeit") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEIT("vuseit") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    NL("nl") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    PL("pl") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    KZ("kz") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("Lieferadresse")
                    .city("kazaki")
                    .postcode("123456")
                    .country("KZ")
                    .phoneNumber("9889912546")
                    .UserID(random(12,NUMERIC))
                    .build();
        }
    },
    DE("de") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    DK("dk") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    FR("fr") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSECO("vuseco") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VELOZA("veloza") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEFR("vusefr") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    IE("ie") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("val")
                    .city("Dublin")
                    .postcode("D04 E8K7")
                    .country("Ireland")
                    .phoneNumber("8899125463")
                    .build();
        }
    },
    MX("mx") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEMX("vusemx") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    UK("uk") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEUK("vuseuk") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },

    LYFTDK("lyftdk") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    LYFTSE("lyftse") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("Nils Ericsons plan 4")
                    .city("Stockholm")
                    .postcode("11164")
                    .country("Sweden")
                    .phoneNumber("8899125463")
                    .build();
        }
    },
    VUSEZA("vuseza") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    EPOK("epok") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEDK("vusedk") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VELODE("velode") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("Kieler Strasse 70")
                    .city("Taufkirchen")
                    .postcode("84413")
                    .country("Germany")
                    .phoneNumber("8630737725")
                    .build();
        }
    },
    VELOBE("velobe") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("Bisschopweg 9")
                    .city("Aalst")
                    .postcode("9310")
                    .country("Belgium")
                    .phoneNumber("07944816325")
                    .build();
        }
    },
    VELOPL("velopl") {
        @Override
        public Address getDefaultAddress() {
            return null;
        }
    },
    VUSEDE("vusede") {
        @Override
        public Address getDefaultAddress() {
            return Address.builder()
                    .streetAndHouseNumber("Oberschwemke 1")
                    .city("Sundern")
                    .postcode("59846")
                    .country("Germany")
                    .phoneNumber("345189478468")
                    .build();
        }
    };

    private String name;

    Locale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Address getDefaultAddress();
}
