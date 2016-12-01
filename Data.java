package developer.shivam.myapplication;

/**
 * Created by shivam on 30/11/16.
 */

public class Data {
    private int price, imageid;
    private String id,name,description,image_link;

    public Data(String id,String name,int price, String description, int imageid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageid = imageid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public int getImageId(){ return imageid; }

    public void setImageId(int imageid){ this.imageid = imageid; }

}
