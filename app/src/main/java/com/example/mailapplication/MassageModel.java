package com.example.mailapplication;

public class MassageModel {
    String gonderen,alici,konu,ileti;

    public MassageModel() {
    }

    public MassageModel(String gonderen, String alici, String konu, String ileti) {
        this.gonderen = gonderen;
        this.alici = alici;
        this.konu = konu;
        this.ileti = ileti;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public String getAlici() {
        return alici;
    }

    public void setAlici(String alici) {
        this.alici = alici;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getIleti() {
        return ileti;
    }

    public void setIleti(String ileti) {
        this.ileti = ileti;
    }
}
