package onestep.id.sinergiin.Model;

public class mPengrajinQc {
    String id, img, title, date;
    int verif;

    public mPengrajinQc(String id, String img, String title, String date, int verif) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.date = date;
        this.verif = verif;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVerif() {
        return verif;
    }

    public void setVerif(int verif) {
        this.verif = verif;
    }
}
