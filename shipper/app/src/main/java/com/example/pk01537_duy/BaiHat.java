package com.example.pk01537_duy;

public class BaiHat {

    private Integer Id;
    private String Ten;
    private String tac;
    private String loi;
    private String ngay;

    public BaiHat(Integer id, String ten, String tac, String loi, String ngay) {
        Id = id;
        Ten = ten;
        this.tac = tac;
        this.loi = loi;
        this.ngay = ngay;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
