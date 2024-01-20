package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Api;
import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.exceptions.UnableToAddItemException;
import com.revature.ecommerce.repositories.ApiRepository;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.ecommerce.dto.OrderDto;

import java.util.*;

@Service
@EnableTransactionManagement
@Transactional(Transactional.TxType.REQUIRED)
public class OrderService {
    private final OrderRepository orderRepository;
    private CustomerService customerService;
    private CartService cartService;
    private ProductService productService;
    private CartRepository cartRepository;

    private ApiRepository apiRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService,
                        CartService cartService, ProductService productService,
                        CartRepository cartRepository, ApiRepository apiRepository) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.apiRepository = apiRepository;

    }

    @Autowired
    EMailService eMailService;


    public Order makeAnOrder(String email) throws UnableToAddItemException{
        List<String> list = new ArrayList<String>();
        StringBuilder listBuffer = new StringBuilder();
        Double cost = 0.0;

        Date dateOfPurchase = new Date();
        Customer customer = customerService.findByEmail(email);

        Order order = new Order();
        order.setOrderStatus(true);
        order.setDateOfPurchase(dateOfPurchase);


        Set<Cart> customerCarts = customer.getCart();
        for (Cart c : customerCarts) {
            if(productService.findById(c.getProductId()) == null){
                throw new UnableToAddItemException(c.getProductId() + "Product has been removed");
            }
            list.add("productId: " + String.valueOf(c.getProductId()));
            System.out.println(c.getProductId());
            list.add("%");
            list.add("productType: " + String.valueOf(productService.findById(c.getProductId()).getProductType()));
            list.add("%");
            list.add("productPrice: " + String.valueOf(c.getPrice()));
            list.add("#");

            cost = cost + c.getPrice();

            Api api = new Api();
            api.setProductId(c.getProductId());
            api.setCustomer(customer);

            apiRepository.save(api);

            cartRepository.deleteById(c.getCartId());
        }
        listBuffer.append(list);
        order.setOrderedItems(listBuffer.toString());
        order.setTotalCost(cost);
        order.setCustomer(customer);

        Order createdOrder = orderRepository.save(order);

        SendEmail(order);
        return createdOrder;
    }

        public void SendEmail(Order order){
        String msg ="";
        msg = "Hello " + order.getCustomer().getFirstName() + ",\nOrder number: " + order.getOrderId() + " has been processed";
            String subject = "E-Commerce-R-Us Order Summary";
            String userEmail = "aekpewoh@gmail.com"; //order.getCustomer().getEmail();
            String message = msg;

            eMailService.sendEmail(userEmail, subject, message);
        }


    public OrderDto viewOrder(Integer orderNumber){
        OrderDto receipt = null;
        Optional<Order> order = orderRepository.findById(orderNumber);
        if(order.isPresent()){
            Order customerOrder = orderRepository.findById(orderNumber).get();
            String str = customerOrder.getOrderedItems();
            String [] strArray = str.replace("%", "\n").split("#");
            receipt = new OrderDto(customerOrder.getOrderId(), customerOrder.getDateOfPurchase(),
                    customerOrder.getTotalCost(), strArray);
            return receipt;
        }
        return receipt;
    }

    public Set<OrderDto> viewOrderByCustomer (String email){
       Set<Order> orders = customerService.findByEmail(email).getOrders();
       Set<OrderDto> orderDtos = new HashSet<>();
       for(Order o : orders){
           String str = o.getOrderedItems();
           String [] strArray = str.replace(str.charAt(str.indexOf('%')), '\n').split("#");
           orderDtos.add(new OrderDto(o.getOrderId(), o.getDateOfPurchase(),
                   o.getTotalCost(), strArray));
       }
       return orderDtos;
    }



}
