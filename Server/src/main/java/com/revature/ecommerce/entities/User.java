package com.revature.ecommerce.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    @Column
    String email;
    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column
    String password;

    @OneToMany(mappedBy = "user")
    private Set<Cart> cart;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.cart = cart;
    }

    public User(Integer userId, String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.cart = cart;
    }

    public Integer getUserId() {
        return userId;
    }

    /*
    Setter method for customerId is commented out
     */

//    public void setCustomerId(Integer customerId) {
//        this.customerId = customerId;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cart> getCart() {
        return cart;
    }

    public void setCart(Set<Cart> cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(cart, user.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, firstName, lastName, password, cart);
    }

    @Override
    public String toString() {
        return "User{" +
                "customerId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                '}';
    }
}
