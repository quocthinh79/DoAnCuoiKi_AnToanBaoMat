package api.object_reponse;

import Beans.Brand;
import Beans.Color;
import Beans.Size;
import Beans.Tag;

import java.util.ArrayList;
import java.util.List;

public class InfoFormAddProduct {
    private List<Brand> brandList;
    private List<Color> colorList;
    private List<Size> sizeList;
    private List<Tag> tagList;

    public InfoFormAddProduct() {
        this.brandList = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.sizeList = new ArrayList<>();
        this.tagList = new ArrayList<>();
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }
}
