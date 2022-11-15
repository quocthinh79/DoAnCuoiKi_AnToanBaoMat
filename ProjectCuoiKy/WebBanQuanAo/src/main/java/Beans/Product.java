package Beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String id;
    private String name;
    private String brand;
    private String price;
    private String description;
    private String thumbnail;
    private Timestamp date;
    private int numberOfRate;
    private int rate;
    private int number;
    private List<String> colors;
    private List<String> sizes;
    private List<String> tags;
    private List<String> urlImages;
    private String creator;
    

    public Product() {
        colors = new ArrayList<>();
        sizes = new ArrayList<>();
        tags = new ArrayList<>();
        urlImages = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getNumberOfRate() {
        return numberOfRate;
    }

    public void setNumberOfRate(int numberOfRate) {
        this.numberOfRate = numberOfRate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addColor(String color) {
        this.colors.add(color);
    }

    public void addSize(String size) {
        this.sizes.add(size);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void addUrlImage(String urlImage) {
        this.urlImages.add(urlImage);
    }

    public List<String> getColors() {
        return colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", date=" + date +
                ", numberOfRate=" + numberOfRate +
                ", rate=" + rate +
                ", number=" + number +
                ", colors=" + colors +
                ", sizes=" + sizes +
                ", tags=" + tags +
                ", urlImages=" + urlImages +
                '}';
    }
}
