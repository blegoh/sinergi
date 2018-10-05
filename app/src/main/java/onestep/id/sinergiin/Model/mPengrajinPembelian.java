package onestep.id.sinergiin.Model;

public class mPengrajinPembelian {
    String id,img,pembeli,notrans,jumlah;

    public mPengrajinPembelian() {
    }

    public mPengrajinPembelian(String id, String img, String pembeli, String notrans, String jumlah) {
        this.id = id;
        this.img = img;
        this.pembeli = pembeli;
        this.notrans = notrans;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public String getNotrans() {
        return notrans;
    }

    public void setNotrans(String notrans) {
        this.notrans = notrans;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
