package onestep.id.sinergiin.Model;

public class mPengrajinEcom {
    String id, price, title, img;

    public mPengrajinEcom(String id, String price, String title, String img) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.img = img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {

        return img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
