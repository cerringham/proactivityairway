package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "share_capital")
    private Float shareCapital;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Site> siteList;

    @Override
    public String toString() {
        return "Company name :" + name + " " + shareCapital;
    }
}
