package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.Objects;

public class Food {
    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private int price;
    private int discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, int price,
                int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return getPrice() == food.getPrice()
                && getDiscount() == food.getDiscount()
                && Objects.equals(getName(), food.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getDiscount());
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expireDate=" + expireDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
