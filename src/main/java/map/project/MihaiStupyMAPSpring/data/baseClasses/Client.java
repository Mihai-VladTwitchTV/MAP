package map.project.MihaiStupyMAPSpring.data.baseClasses;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Client")
public class Client {
    @Setter
    @Getter
    @Id
    private int clientID;

    @Setter
    @Getter
    @Column(name = "firstName")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "lastName")
    private String lastName;

    @Setter
    @Getter
    @Column(name = "emailAddress")
    private String emailAddress;

    @Setter
    @Getter
    @Column(name = "phoneNumber")
    private int phoneNumber;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference // This annotation handles the forward part of the reference
    private List<Project> projects;

    public Client() {
        this.projects = new ArrayList<>();
    }

    public Client(int clientID, String firstName, String lastName, String emailAddress, int phoneNumber) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.projects = new ArrayList<>();
    }
}
