package com.fullstack.fullstackproject.model;

import java.io.Serializable;

public enum Language implements Serializable {
    AR("AR"),
    DA("DA"),
    DE("DE"),
    EN("EN"),
    ES("ES"),
    FR("FR"),
    IT("IT"),
    JA("JA"),
    LB("LB"),
    PL("PL"),
    RU("RU"),
    TH("TH"),
    TR("TR"),
    ZH("ZH");

    private final String name;
    Language(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
