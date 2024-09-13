package model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = Product.GET_ALL_PRODUCTS, query = "Select p from Product p")
})
public class Product {

    public static final String GET_ALL_PRODUCTS = "getAllProducts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

}
