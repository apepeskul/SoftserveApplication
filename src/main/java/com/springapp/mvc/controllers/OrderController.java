package com.springapp.mvc.controllers;

import com.springapp.mvc.model.*;
import com.springapp.mvc.repositories.*;
import org.hsqldb.types.Collation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/rest/order")

public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetaitlsRepository orderDetaitlsRepository;

    @Autowired
    CreditCardInfoRepository creditCardInfoRepository;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PriceRepository priceRepository;

   /* @InitBinder
    public void priceBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Price.class, new PriceEditor(priceRepository));
    }*/
    @InitBinder
    public void userBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor(userRepository));
    }

    @InitBinder
    public void dimensionBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Dimension.class, new DimensionEditor(dimensionRepository));
    }

   /* @InitBinder
    public void itemBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Item.class, new ItemEditor(itemRepository));
    }*/
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOrder(@PathVariable("id") Long id) throws JSONException {
        JSONArray orderArray = new JSONArray();
        Order order = orderRepository.findOne(id);
        JSONObject orderJSON = new JSONObject();
        orderJSON.put("id", order.getId());
        orderJSON.put("creationDate", order.getCreationDate());
        orderJSON.put("customer", order.getCustomerId());
        orderJSON.put("deliveryDate", order.getDeliveryDate());
        orderJSON.put("merchId", order.getMerchId());
        //orderJSON.put("orderDetailsSet", order.getOrderDetailsSet());
        orderJSON.put("orderNumber", order.getOrderNumber());
        orderJSON.put("payment", order.getPayment());
        orderJSON.put("preferableDate", order.getPreferableDate());
        orderJSON.put("status", order.getStatus());
        orderJSON.put("totalPrice", order.getTotalPrice());
        orderArray.put(orderJSON);

        return orderArray.toString();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editOrder(Order order){
        orderRepository.save(order);
        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public String addOrder(@ModelAttribute("order") Order order, @RequestParam (value = "orderId", required = false) Long oid ){
        Order temporder= orderRepository.findOne(oid);
        order.setOrderDetailsArray(temporder.getOrderDetails());
        orderRepository.save(order);

        return "redirect:/orders";
    }


    @RequestMapping(value = "/addorderdetail", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})

    public String addOrderDetail (@ModelAttribute("order") Order order ){
        orderRepository.save(order);

        return "redirect:/orders";
    }

    @RequestMapping(value = "/all{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List <Order> getAllOrders(@PathVariable ("id") Long customerId,@ModelAttribute ("user") User user  ) throws JSONException{
        user = userRepository.findOne(customerId);
        List <Order> ordersList =  orderRepository.findByCustomerId(user);

        return ordersList;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Long id) {
        orderRepository.delete(orderRepository.findOne(id));

        return "redirect:/orders";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String getOrderDetails(@PathVariable("id") Long id) throws JSONException {
        JSONArray orderDetailsArray = new JSONArray();
        OrderDetails orderDetails = orderDetaitlsRepository.findOne(id);
        JSONObject orderDetailsJSON = new JSONObject();
        orderDetailsJSON.put("id", orderDetails.getId());
        //orderDetailsJSON.put("dimension", orderDetails.getDimension());
        orderDetailsJSON.put("price", orderDetails.getPrice());
        orderDetailsArray.put(orderDetailsJSON);

        return orderDetailsArray.toString();
    }

    @RequestMapping(value = "/details/edit", method = RequestMethod.PUT)
    public String editOrderDetails(OrderDetails orderDetails){
        orderDetaitlsRepository.save(orderDetails);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/details/add", method = RequestMethod.POST)
    public String addOrderDetails(@RequestParam ("oid") Long oid, OrderDetails orderDetails, @RequestParam ("pid") Long id ){
        /*Set <OrderDetails> temp = order.getOrderDetailsSet();
        temp.add(orderDetails);
        order.setOrderDetailsSet(temp);*/
        Order order= orderRepository.findOne(oid);
        orderDetails.setPrice(priceRepository.findOne(id));
       // orderDetails.setOrder(order);
        order.addOrderDetail(orderDetails);
        order.setTotalPrice(order.getTotalPrice().add(new BigDecimal(orderDetails.getPrice().getPrice()*orderDetails.getQuantity())));
        orderDetaitlsRepository.save(orderDetails);
        //orderDetaitlsRepositrory.save(orderDetails);
        orderRepository.save(order);

        return "redirect:/order/"+order.getId();
    }


    @RequestMapping(value = "/details/all/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Collection <OrderDetails> getOrderDetailsForOrder(@PathVariable (value = "id") Long id   ) {
       Order order = orderRepository.findOne(id);

       return order.getOrderDetails();
    }

    /*@RequestMapping(value = "/details/all", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String getAllOrdersDetails() throws JSONException {
        JSONArray orderDetailsArray = new JSONArray();
        for(OrderDetails orderDetails : orderDetaitlsRepositrory.findAll()){
            JSONObject orderDetailsJSON = new JSONObject();
            orderDetailsJSON.put("id", orderDetails.getId());
            //orderDetailsJSON.put("creationDate", orderDetails.getDimension());
            orderDetailsJSON.put("customer", orderDetails.getPrice());

            orderDetailsArray.put(orderDetailsJSON);
        }
        return orderDetailsArray.toString();
    }*/

    @RequestMapping( value = "/details/delete/{id}", method = RequestMethod.DELETE)
    public String deleteOrderDetails(@PathVariable("id") Long id) {
        orderDetaitlsRepository.delete(orderDetaitlsRepository.findOne(id));
        return "redirect:/";  //TODO: URL
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public CreditCardInfo getCardInfo(@PathVariable("id") Long id) throws JSONException {
        return creditCardInfoRepository.findOne(id);
    }

    @RequestMapping(value = "/card/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editCardInfo(CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);
        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/card/add", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public String addCardInfo(CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping( value = "/delete/{id}")
    public String deleteCreditCardInfo(@PathVariable("id") Long id) {
        creditCardInfoRepository.delete(creditCardInfoRepository.findOne(id));

        return "redirect:/";  //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/card/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<CreditCardInfo> getPageInfo(@PathVariable ("page") int page,
                                            @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<CreditCardInfo> infos = creditCardInfoRepository.findAll(new PageRequest(page, size));

        return infos.getContent();
    }

    //TODO: @param name
    @RequestMapping(value = "/card/page/starting/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<CreditCardInfo> getSomeInfoByName(
            @PathVariable ("page") int page,
            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");

        Page<CreditCardInfo> infos = creditCardInfoRepository.findByCreditCardNumberStartingWith("32",
                new PageRequest(page, size));

        return infos.getContent();
    }

}
