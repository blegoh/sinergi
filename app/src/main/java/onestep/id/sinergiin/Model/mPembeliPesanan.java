package onestep.id.sinergiin.Model;

public class mPembeliPesanan {
    String id,produk,jumlah,estimasi,verif;

    public mPembeliPesanan(String id, String produk, String jumlah, String estimasi, String verif) {
        this.id = id;
        this.produk = produk;
        this.jumlah = jumlah;
        this.estimasi = estimasi;
        this.verif = verif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }
}
