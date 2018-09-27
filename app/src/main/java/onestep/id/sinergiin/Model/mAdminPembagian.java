package onestep.id.sinergiin.Model;

public class mAdminPembagian {
    private String id,pembeli,produk,pcs,date,img;

    public mAdminPembagian(String id, String pembeli, String produk, String pcs, String date, String img) {
        this.id = id;
        this.pembeli = pembeli;
        this.produk = produk;
        this.pcs = pcs;
        this.date = date;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
