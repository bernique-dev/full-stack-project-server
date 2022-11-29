package com.fullstack.fullstackproject.model;

import java.io.Serializable;

public enum Language implements Serializable {
    AR("AR"),
    ZH("ZH"),
    DA("DA"),
    EN("EN"),
    FR("FR"),
    DE("DE"),
    IT("IT"),
    JA("JA"),
    LB("LB"),
    PL("PL"),
    RU("RU"),
    ES("ES"),
    TH("TH"),
    TR("TR");

    private final String name;
    private Language(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
