package org.learn.cms.contact;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.learn.cms.converter.SpecialContactConverter;
import org.learn.cms.pojo.SpecialContact;
import org.learn.cms.converter.StringListConverter;
import org.learn.cms.user.UserModel;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
public class ContactModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 75)
    private String name;

    @Column(length = 25)
    private String phone;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "JSON",length = 75)
    private List<String> emails;

    @Convert(converter = SpecialContactConverter.class)
    private List<SpecialContact> links;
    private String address;

    @Column(length = 300)
    private String picture;

    @Column(length = 500)
    private String description;
    private boolean favourite;

    @ManyToOne
    private UserModel user;
}
