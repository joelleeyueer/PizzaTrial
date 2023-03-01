package ssf2022.pizzatrial.Services;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ssf2022.pizzatrial.Models.Pizza;

@Service
public class OrderService {

    public String generateId(){
        return UUID.randomUUID().toString().substring(0,8);
    }

    public double costCalculator(Pizza pizza){

        int unitCost=0;
        double multiplier = 1.0;
        List<String> pizzaList = new LinkedList<>();

        pizzaList.add("bella");
        pizzaList.add("marinara");
        pizzaList.add("spianatacalabrese");
        
        if(pizzaList.contains(pizza.getPizzaName())){
            unitCost = 30;
        }else if (pizza.getPizzaName().equals("margherita")){
            unitCost = 22;
        }else if (pizza.getPizzaName().equals("trioformaggio")){
            unitCost = 25;
        }

        if(pizza.getSize().equals("lg")){
            multiplier = 1.5;
        }else if(pizza.getSize().equals("md")){
            multiplier = 1.2;
        }

        return pizza.getQuantity()*unitCost*multiplier;
    }

}
