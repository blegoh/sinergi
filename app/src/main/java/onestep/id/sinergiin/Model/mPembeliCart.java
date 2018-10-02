package onestep.id.sinergiin.Model;

public class mPembeliCart {
    String id, barang, penjual, jumlah, harga, img;

    public mPembeliCart(String id, String barang, String penjual, String jumlah, String harga, String img) {
        this.id = id;
        this.barang = barang;
        this.penjual = penjual;
        this.jumlah = jumlah;
        this.harga = harga;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
