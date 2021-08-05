package com.safronova.webproject.model.entity;

import java.math.BigDecimal;

public class Dessert {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int weight;
    private String dessertImage;
    private DessertType dessertType;
    private Storage storage;

    public Dessert(String name, String description, BigDecimal price,  int weight, DessertType dessertType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.dessertType = dessertType;
    }

    public Dessert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDessertImage() {
        return dessertImage;
    }

    public void setDessertImage(String dessertImage) {
        this.dessertImage = dessertImage;
    }

    public DessertType getDessertrType() {
        return dessertType;
    }

    public void setDessertType(DessertType dessertType) {
        this.dessertType = dessertType;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dessert dessert = (Dessert) o;

        if (id != dessert.id) return false;
        if (price != null ? !price.equals(dessert.price) : dessert.price != null) return false;
        if (weight != dessert.weight) return false;
        if (name != null ? !name.equals(dessert.name) : dessert.name != null) return false;
        if (description != null ? !description.equals(dessert.description) : dessert.description != null) return false;
        if (dessertImage != null ? !dessertImage.equals(dessert.dessertImage) : dessert.dessertImage != null) return false;
        if (dessertType != null ? !dessertType.equals(dessert.dessertType) : dessert.dessertType != null) return false;
        return storage != null ? storage.equals(dessert.storage) : dessert.storage == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (dessertImage != null ? dessertImage.hashCode() : 0);
        result = 31 * result + (dessertType != null ? dessertType.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flower{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", soil=").append(weight);
        sb.append(", flowerImage='").append(dessertImage).append('\'');
        sb.append(", flowerType=").append(dessertType);
        sb.append(", storage=").append(storage);
        sb.append('}');
        return sb.toString();
    }
}
