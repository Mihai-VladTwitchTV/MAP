package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Client")
public class Client {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientID;

    @Setter
    @Getter
    @Column(name = "firstName")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "lastName")
    private String lastName;

    public Client() {
    }

    public Client(int clientID, String firstName,String lastName, String emailAddress, int phoneNumber) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    @Setter
    @Getter
    @Column(name = "emailAddress")
    private String emailAddress;

    @Setter
    @Getter
    @Column(name = "phoneNumber")
    private int phoneNumber;
}
