package spring.security.fullstack.model;

import spring.security.fullstack.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name="username")
    @NotNull
    private String username;

    @Column(name="password")
    @NotNull
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = "This is not a valid email")
    @Column(name = "email",unique = true)
    @NotNull
    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    //nu va fi stocat in baza de date
    @Transient
    private String token;

    //TODO userul cred ca ar trebui sa porneasca cu un cart nou
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> ordersList;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    //TODO ma bate putin asta
    public void setCart(Cart cart) {
        cart.setUser(this);
        this.cart = cart;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", role=" + role +
                ", cart=" + cart +
                ", ordersList=" + ordersList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
