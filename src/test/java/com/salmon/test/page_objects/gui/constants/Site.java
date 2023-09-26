package com.salmon.test.page_objects.gui.constants;

import lombok.Getter;

public enum Site {
    GLO("glo"),
    LYFT("lyft"),
    VYPE("vype"),
    VELO("velo"),
    VUSE("vuse");

    @Getter
    private String site;

    Site(String site) {
        this.site = site;
    }
}
