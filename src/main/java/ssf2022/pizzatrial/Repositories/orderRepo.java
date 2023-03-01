package ssf2022.pizzatrial.Repositories;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import ssf2022.pizzatrial.Models.Cart;
import ssf2022.pizzatrial.Models.Pizza;

@Repository
public class orderRepo {
    
    @Autowired @Qualifier("pizza")
    RedisTemplate<String, String> template;
    
    public void addOrder(String Id, Cart cart, Pizza pizza, double totalCost){
        JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
        orderBuilder.add("orderId",Id)
        .add("name",cart.getName())
        .add("address",cart.getAddress())
        .add("phone",cart.getPhone())
        .add("rush",cart.isRush())
        .add("comments",cart.getComment())
        .add("pizza",pizza.getPizzaName())
        .add("size",pizza.getSize())
        .add("quantity",pizza.getQuantity())
        .add("total",totalCost);

        JsonObject jo = orderBuilder.build();

        template.opsForList().rightPush("orderIdList",Id);
        template.opsForHash().put("pizzaOrder",Id,jo.toString());
    }

    public JsonObject getOrder(String Id){

        String payload = (String) template.opsForHash().get("pizzaOrder",Id);
        JsonObject jo = null;
        try{
            Reader reader = new StringReader(payload);
            JsonReader or = Json.createReader(reader);
            jo = or.readObject();
        } catch (Exception ex){
            JsonObjectBuilder errBuilder = Json.createObjectBuilder();
            errBuilder.add("Message","Order %s not found.".formatted(Id));
            jo = errBuilder.build();
        }
        
        return jo;
    }



}
