package ssf2022.pizzatrial.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Cart {
    
    @NotBlank(message="Please provide your name.")
    @Size(min=3, max=100)
    private String name;

    @NotBlank(message="Please provide your address.")
    private String address;

    @Pattern(regexp = "(\\8|9)[0-9]{7}", message = "Invalid phone format.")
    private String phone;

    private boolean rush;
    private String comment;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }


}
