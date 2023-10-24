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
    @Column(name = "clientName")
    private String clientName;

    public Client() {
    }

    public Client(int clientID, String clientName, String emailAddress, int phoneNumber) {
        this.clientID = clientID;
        this.clientName = clientName;
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
