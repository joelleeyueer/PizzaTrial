package ssf2022.pizzatrial.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ssf2022.pizzatrial.Models.Cart;
import ssf2022.pizzatrial.Models.Pizza;
import ssf2022.pizzatrial.Repositories.orderRepo;
import ssf2022.pizzatrial.Services.OrderService;

@Controller
@RequestMapping(path="/")
public class pizzaController {

    @Autowired
    OrderService orderSvc;

    @Autowired
    orderRepo orderRep;
    
    @GetMapping()
    public String orderPage(Model model){
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "index";
    }

    @PostMapping(path = "/pizza")
    public String receiveOrder(@Valid Pizza pizza, BindingResult result, HttpSession session, Model model){
        if(result.hasErrors()){
            return "index";
        }
        session.setAttribute("pizza", pizza);

        // To be deleted later.
        System.out.println(pizza);
        double cost = orderSvc.costCalculator(pizza);
        System.out.println(cost);

        Cart cart = new Cart();

        model.addAttribute("cart", cart);

        return "orderDetail";
    }

    @PostMapping(path="/pizza/order")
    public String orderDetail(@Valid Cart cart, BindingResult result, HttpSession session, Model model){
        if (result.hasErrors()){
            return "orderDetail";
        }

        double pizzaCost, totalCost;

        Pizza pizza = (Pizza)session.getAttribute("pizza");
        pizzaCost = orderSvc.costCalculator(pizza);
        totalCost = pizzaCost;

        if(cart.isRush()){
            totalCost = pizzaCost +2.0;
            System.out.println(totalCost);
        }
        String Id = orderSvc.generateId();

        orderRep.addOrder(Id, cart, pizza, totalCost);

        model.addAttribute("cost", pizzaCost);
        model.addAttribute("totalCost",totalCost);
        model.addAttribute("Id", Id);
        model.addAttribute("cart",cart);

        return "summary";
    }


}
