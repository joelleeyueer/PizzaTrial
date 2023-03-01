package ssf2022.pizzatrial.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import ssf2022.pizzatrial.Repositories.orderRepo;



@RestController
public class restController {
    
    @Autowired
    orderRepo orderRep;

    @GetMapping(path="/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> orderIdDetail(@PathVariable String id){
        // JsonObject jo = orderRep.getOrder(id);
        JsonObject jo = orderRep.getOrder(id);

        try{
            jo.getJsonObject(id);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo.toString());
        }
        
        return ResponseEntity.status(200).body(jo.toString());
    }


}
