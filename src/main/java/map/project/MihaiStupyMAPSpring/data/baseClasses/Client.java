package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientID;

    private String clientName;
    private String email;
    private int phone;
}
