import javafx.scene.image.Image;

public class Speaker {
    private String productName, description;
    private double price;
    private Image image;
    private int speakerID;

    public Speaker(int speakerID, String productName, String description, double price, Image image) {
        setSpeakerID(speakerID);
        setProductName(productName);
        setDescription(description);
        setPrice(price);
        setImage(image);
    }

    public int getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(int speakerID) {
        if (speakerID > 0)
            this.speakerID = speakerID;
        else
            throw new IllegalArgumentException("speaker ID must be greater than 0");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName.isEmpty())
            throw new IllegalArgumentException("productName cannot be empty");
        else
            this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty())
            throw new IllegalArgumentException("description cannot be empty");
        else
            this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else
            throw new IllegalArgumentException("price must be greater than 0");
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String toString()
    {
        return productName;
    }
}
