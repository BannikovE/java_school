package app.model;

import app.model.enums.UserRole;
import app.model.enums.UserStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Transient
    private String stringDateOfBirth;
    @Column
    private String email;
    @Column
    private String password;
    @Transient
    private String passwordConfirm;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @OneToMany (mappedBy = "user", fetch=FetchType.EAGER)
    private List<Address> addresses;

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getStringDateOfBirth() {
        return stringDateOfBirth;
    }

    public void setStringDateOfBirth(String stringDateOfBirth) {
        this.stringDateOfBirth = stringDateOfBirth;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public static User.Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setId(Integer id) {
            User.this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            User.this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setStringDateOfBirth(String dateOfBirth) {
            User.this.stringDateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setPasswordConfirm(String password) {
            User.this.passwordConfirm = password;
            return this;
        }

        public Builder setRole(UserRole userRole) {
            User.this.role = userRole;
            return this;
        }

        public Builder setStatus(UserStatus userStatus) {
            User.this.status = userStatus;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
