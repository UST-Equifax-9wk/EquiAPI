package com.revature.ecommerce.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class Customer implements UserDetails {
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

    @Column(columnDefinition = "varchar(255) default 'CUSTOMER'")
    String role;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;   //CHECK THIS

//    @OneToMany(mappedBy = "user" /* supposed to be customer? */)
//    private Set<Card> cart;



    public Customer() {
    }

    public Customer(String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
//        this.cart = cart;
    }

    public Customer(Integer customerId, String email, String firstName, String lastName, String password, Set<Cart> cart) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
//        this.cart = cart;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }


    public Integer getCustomerId() {
        return customerId;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Cart> getCart() {
//        return cart;
//    }

//    public void setCart(Set<Cart> cart) {
//        this.cart = cart;
//    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(email, customer.email)
                && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName)
                && Objects.equals(password, customer.password);// && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email, firstName, lastName, password); //, cart);
    }

    @Override
    public String toString() {


        return "Customer{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '}'; //\'' +
//                ", cart=" + cart +
//                '}';
    }

}