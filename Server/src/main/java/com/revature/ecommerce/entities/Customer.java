package com.revature.ecommerce.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Integer customerId;
    @Column
    String email;
    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column
    String password;  //Remove password since there is an auth dto

    @OneToMany(mappedBy = "user")
    private Set<Cart> cart;



    public Customer() {
    }

    public Customer(String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.cart = cart;
    }

    public Customer(Integer customerId, String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.cart = cart;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    /*
    Setter method for customerId is commented out
     */

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
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(email, customer.email) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(password, customer.password) && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email, firstName, lastName, password, cart);
    }

    @Override
    public String toString() {
        return "User{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                '}';
    }
}
