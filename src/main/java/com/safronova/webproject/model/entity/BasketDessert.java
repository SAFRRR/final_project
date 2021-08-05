package com.safronova.webproject.model.entity;

import java.math.BigDecimal;

public class BasketDessert {
    private int id;
    private int count;
    private BigDecimal subTotal;
    private Dessert dessert;
    private Basket basket;

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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketDessert that = (BasketDessert) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (subTotal != null ? !subTotal.equals(that.subTotal) : that.subTotal != null) return false;
        if (dessert != null ? !dessert.equals(that.dessert) : that.dessert != null) return false;
        return basket != null ? basket.equals(that.basket) : that.basket == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + count;
        result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
        result = 31 * result + (dessert != null ? dessert.hashCode() : 0);
        result = 31 * result + (basket != null ? basket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasketFlower{");
        sb.append("id=").append(id);
        sb.append(", count=").append(count);
        sb.append(", subTotal=").append(subTotal);
        sb.append(", dessert=").append(dessert);
        sb.append(", basket=").append(basket);
        sb.append('}');
        return sb.toString();
    }
}
