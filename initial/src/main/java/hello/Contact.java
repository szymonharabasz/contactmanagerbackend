package hello;

import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Contact {
    @Id
    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String website;
    private String notes;

    public Contact() {
        this("","","","","","");
    }
    public Contact(String name, String phone, String address, String email, String website, String notes) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.website = website;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String s) {
        phone = s;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String s) {
        address = s;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String s) {
        email = s;
    }
    public String getWebsie() {
        return website;
    }
    public void setWebsite(String s) {
        website = s;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String s) {
        notes = s;
    }
    @Override
    public String toString() {
        return "{\n" +
            "\tname: " + name + ",\n" +
            "\tphone: " + phone + ",\n" +
            "\taddress: " + address + ",\n" +
            "\temail: " + email + ",\n" +
            "\twebsite: " + website + ",\n" +
            "\tnotes: " + notes + ",\n" +
            "}";
            
    }
}
