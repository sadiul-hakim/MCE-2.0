package org.learn.cms.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.learn.cms.contact.ContactModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @NotNull
    @Column(length = 75)
    private String name;

    @NotEmpty
    @NotNull
    @Email
    @Column(length = 100, unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @Column(length = 200)
    private String password;

    @Column(length = 500)
    private String about;

    @Column(length = 300)
    private String profilePic;
    private boolean enabled;
    private boolean emailVerified;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ContactModel> contacts = new ArrayList<>();

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String userProviderId;

    private String role = "ROLE_USER";

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp joiningDate = new Timestamp(System.currentTimeMillis());
}
