package model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS, query = "Select u from User u"),
        @NamedQuery(name = User.GET_USERS_BY_EMAIL, query = "Select u from User u where u.email = :email"),
        @NamedQuery(name = User.GET_USER_BY_ID, query = "Select u from User u where u.id = :id")

})
public class User {

    public static final String GET_ALL_USERS = "getAllUsers";
    public static final String GET_USERS_BY_EMAIL = "getUsersByEmail";
    public static final String GET_USER_BY_ID = "getUserById";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;

        System.out.println("this.id: " + this.id);
        System.out.println("other.id: " + other.id);
        System.out.println("this.email: " + this.email);
        System.out.println("other.email: " + other.email);

        return this.email.equals(other.email);
    }
}
