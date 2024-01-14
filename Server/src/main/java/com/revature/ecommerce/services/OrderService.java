package com.revature.ecommerce.services;

import com.revature.ecommerce.dto.MakeOrder;
import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.CreditCards;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.entities.Order;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.ecommerce.dto.ReceiptDto;

import java.time.LocalDateTime;
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

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService,
                        CartService cartService, ProductService productService, CartRepository cartRepository){
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Autowired
    EMailService eMailService;

    public Order makeAnOrder(String email, MakeOrder makeOrder){
        List<String> list = new ArrayList<String>();
        StringBuilder listBuffer = new StringBuilder();

        Date dateOfPurchase = new Date();
        Customer customer = customerService.findByEmail(email);

        Order order = new Order();
        order.setOrderStatus(true);
        order.setDateOfPurchase(dateOfPurchase);
        order.setPaymentMethod(makeOrder.getCreditCard().getCardNumber());
        order.setTotalCost(cartService.getTotal(email));
        order.setShipmentDate(dateOfPurchase);


        Set<Cart> customerCarts = customer.getCart();
        for(Cart c : customerCarts){
            list.add(String.valueOf(productService.findById(c.getProductId()).getName()));
            list.add("%");
            list.add(String.valueOf(productService.findById(c.getProductId()).getProductType()));
            list.add("%");
            list.add(String.valueOf(productService.findById(c.getProductId()).getDescription()));
            list.add("%");
            list.add(String.valueOf(c.getQuantity()));
            list.add("%");
            list.add(String.valueOf(c.getPrice()));
            list.add("^");
            cartRepository.deleteById(c.getCartId());
        }
        listBuffer.append(list);
        order.setOrderedItems(listBuffer.toString());

        //Create receipt

        //Correct the balance on card
        CreditCards creditCards = makeOrder.getCreditCard();
        creditCards.setAvailableBalance(creditCards.getAvailableBalance() - order.getTotalCost());

//        customerCarts = null;


//        Customer savedCustomer = customerService.saveCustomer(customer);

        System.out.println(customer.getCart());

        Order createdOrder = orderRepository.save(order);
        ReceiptDto receipt = new ReceiptDto(order.getOrderId(), order.getDateOfPurchase(),order.getPaymentMethod(),
                order.getTotalCost(), order.getOrderedItems());


        String subject = "E-Commerce-R-Us";
        String userEmail = "aekpewoh@gmail.com";
        String message = "Your order has been processed";

        eMailService.sendEmail(userEmail,subject,message);


        return createdOrder;
    }

    public Order viewOrder(Integer orderNumber){
        Optional<Order> order = orderRepository.findById(orderNumber);
        return order.orElse(null);
    }


}
