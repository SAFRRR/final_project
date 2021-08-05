package com.safronova.webproject.model.entity;

public class Storage {
    private int id;
    private int count;
    private Dessert dessert;

    public Storage(Dessert dessert, int count) {
        this.dessert = dessert;
        this.count = count;
    }

    public Storage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (count != storage.count) return false;
        return dessert != null ? dessert.equals(storage.dessert) : storage.dessert == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + count;
        result = 31 * result + (dessert != null ? dessert.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Storage{");
        sb.append("id=").append(id);
        sb.append(", count=").append(count);
        sb.append(", flower=").append(dessert);
        sb.append('}');
        return sb.toString();
    }
}
