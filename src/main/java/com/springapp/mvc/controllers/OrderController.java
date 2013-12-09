package com.springapp.mvc.controllers;

import com.springapp.mvc.model.*;
import com.springapp.mvc.repositories.*;
import org.hsqldb.types.Collation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 24.11.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value = "/rest/order")

public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetaitlsRepositrory orderDetaitlsRepositrory;

    @Autowired
    CreditCardInfoRepository creditCardInfoRepository;
    @Autowired
    DimensionRepository dimensionRepository;
    @Autowired
    ItemRepositrory itemRepository;
    @Autowired
    PriceRepository priceRepository;
    /*
      *GET read
      * /rest/order/{id}
     */
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

    /*
      *PUT update
      * /rest/order/edit
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editOrder(Order order){
        orderRepository.save(order);
        return "redirect:/"; //TODO: URL
    }

     /*
      *POST create
      * /rest/order/add
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public String addOrder(@ModelAttribute("order") Order order){

        orderRepository.save(order);
        return "redirect:/orders";
    }


    @RequestMapping(value = "/addorderdetail", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})

    public String addOrderDetail (@ModelAttribute("order") Order order ){

        orderRepository.save(order);
        return "redirect:/orders";
    }

    /*
      *GET read
      * /rest/order/all
     */
    @RequestMapping(value = "/all{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List <Order> getAllOrders(@PathVariable ("id") Long customerId,@ModelAttribute ("user") User user  ) throws JSONException{



        user = userRepository.findOne(customerId);
        List <Order> ordersList =  orderRepository.findByCustomerId(user);



        return ordersList;
    }



    /*
    * DELETE
    * rest/order/delete/{id}
    * */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Long id) {
        orderRepository.delete(orderRepository.findOne(id));
        return "redirect:/orders";
    }

    /*
      *GET read
      * /rest/order/details/{id}
     */
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String getOrderDetails(@PathVariable("id") Long id) throws JSONException {
        JSONArray orderDetailsArray = new JSONArray();
        OrderDetails orderDetails = orderDetaitlsRepositrory.findOne(id);
        JSONObject orderDetailsJSON = new JSONObject();
        orderDetailsJSON.put("id", orderDetails.getId());
        //orderDetailsJSON.put("dimension", orderDetails.getDimension());
        orderDetailsJSON.put("price", orderDetails.getPrice());
        orderDetailsArray.put(orderDetailsJSON);

        return orderDetailsArray.toString();
    }

    /*
      *PUT update
      * /rest/order/details/edit
     */
    @RequestMapping(value = "/details/edit", method = RequestMethod.PUT)
    public String editOrderDetails(OrderDetails orderDetails){
        orderDetaitlsRepositrory.save(orderDetails);
        return "redirect:/"; //TODO: URL
    }

    /*
     *POST create
     * /rest/order/details/add
    */
    @RequestMapping(value = "/details/add", method = RequestMethod.POST)
    public void addOrderDetails(@ModelAttribute ("order") Order order, OrderDetails orderDetails, @RequestParam ("pid") Long id ){
        /*Set <OrderDetails> temp = order.getOrderDetailsSet();
        temp.add(orderDetails);
        order.setOrderDetailsSet(temp);*/
        orderDetails.setPrice(priceRepository.findOne(id));
         order.addOrderDetail(orderDetails);



    }


    @RequestMapping(value = "/details/all", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public Collection <OrderDetails> getOrderDetails(@ModelAttribute ("order") Order order  ) {

       return order.getOrderDetails();
    }

    /*
      *GET read
      * /rest/order/details/all
     */

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

    /*
    * DELETE
    * rest/order/details/delete/{id}
    * */
    @RequestMapping( value = "/details/delete/{id}", method = RequestMethod.DELETE)
    public String deleteOrderDetails(@PathVariable("id") Long id) {
        orderDetaitlsRepositrory.delete(orderDetaitlsRepositrory.findOne(id));
        return "redirect:/";  //TODO: URL
    }

    /*
      *GET read
      * /rest/order/card/{id}
     */
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String getCardInfo(@PathVariable("id") Long id) throws JSONException {
        JSONArray creditCardInfoArray = new JSONArray();
        CreditCardInfo creditCardInfo = creditCardInfoRepository.findOne(id);
        JSONObject creditCardInfoJSON = new JSONObject();
        creditCardInfoJSON.put("id", creditCardInfo.getId());
        creditCardInfoJSON.put("creditCardNumber", creditCardInfo.getCreditCardNumber());
        creditCardInfoJSON.put("CVV2Code", creditCardInfo.getCVV2Code());
        creditCardInfoJSON.put("expiryDate", creditCardInfo.getExpiryDate());
        creditCardInfoJSON.put("issueNumber", creditCardInfo.getIssueNumber());
        creditCardInfoJSON.put("startDate", creditCardInfo.getStartDate());
        creditCardInfoJSON.put("type", creditCardInfo.getType());

        creditCardInfoArray.put(creditCardInfoJSON);
        return creditCardInfoArray.toString();
    }

    /*
      *PUT update
      * /rest/order/card/edit
     */
    @RequestMapping(value = "/card/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editCardInfo(CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);
        return "redirect:/"; //TODO: URL
    }

    /*
     *POST create
     * /rest/order/card/add
    */
    @RequestMapping(value = "/card/add", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public String addCardInfo(CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);
        return "redirect:/"; //TODO: URL
    }

}
