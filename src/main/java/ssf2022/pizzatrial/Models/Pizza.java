package ssf2022.pizzatrial.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Pizza {

    @NotNull(message = "Please select a pizza. Select from Bella, Margherita, Marinara, Spianatacalabrese, Trioformaggio.")
    private String pizzaName;

    @NotEmpty(message="Please choose a size. Available size are: sm, md and lg.")
    private String size;

    @NotNull(message ="Please indicates quantity between 1-10.")
    @Min(value = 1, message ="Minimum quantity is 1.")
    @Max(value=10, message ="Maximum quantity is 10.")
    private int quantity;

    public Pizza(){}

    public String getPizzaName(){return pizzaName;};
    public void setPizzaName(String name){this.pizzaName = name;}

    public String getSize(){return size;}
    public void setSize(String size){this.size = size;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int amount){this.quantity = amount;}


    @Override
    public String toString(){
        return "Pizza name: %s, Pizza size: %s, Pizza Quantity: %d".formatted(pizzaName, size, quantity);
    }




}
