package onestep.id.sinergiin.Model;

/**
 * Created by altintop on 05/10/18.
 */

public class mPengrajinDetailPembelian {
  String id,img,barang,jumlah,harga;

  public mPengrajinDetailPembelian(String id, String img, String barang, String jumlah, String harga) {
    this.id = id;
    this.img = img;
    this.barang = barang;
    this.jumlah = jumlah;
    this.harga = harga;
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

  public String getBarang() {
    return barang;
  }

  public void setBarang(String barang) {
    this.barang = barang;
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
