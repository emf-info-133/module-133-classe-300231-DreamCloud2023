package doudix.ch.ctrlrest1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
 
    }

    public String toString(){
        return username + password;
    }
}

