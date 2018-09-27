package onestep.id.sinergiin.Model;

public class mPembeliPembelian {
    private String id, img, produk, penjual, jumlah, durasi;

    public mPembeliPembelian(String id, String img, String produk, String penjual, String jumlah, String durasi) {
        this.id = id;
        this.img = img;
        this.produk = produk;
        this.penjual = penjual;
        this.jumlah = jumlah;
        this.durasi = durasi;
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

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
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

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }
}
