package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;

public class ClientDTO {
    private int clientID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int phoneNumber;

    // Getters and setters
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Client toClient() {
        Client client = new Client();
        client.setClientID(this.clientID);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setEmailAddress(this.emailAddress);
        client.setPhoneNumber(this.phoneNumber);
        return client;
    }
}