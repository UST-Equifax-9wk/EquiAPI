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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Order makeAnOrder(String email, MakeOrder makeOrder){
        List<String> list = new ArrayList<String>();
        StringBuffer listBuffer = new StringBuffer();

        Date dateOfPurchase = new Date();
        Customer customer = customerService.findByEmail(email);

        Order order = new Order();
        order.setOrderStatus(false);
        order.setDateOfPurchase(dateOfPurchase);
        order.setPaymentMethod(makeOrder.getCreditCard().getCardNumber());
        order.setTotalCost(cartService.getTotal(email));
        order.setShipmentDate(dateOfPurchase);

        /**
         * I used not too regular symbols to separate the fields and the end of product
         * This should facilitate easy removal and replacement with new lines
         */
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

        }
        listBuffer.append(list);
        order.setOrderedItems(listBuffer.toString());

        //Create receipt

        //Correct the balance on card
        CreditCards creditCards = makeOrder.getCreditCard();
        creditCards.setAvailableBalance(creditCards.getAvailableBalance() - order.getTotalCost());



        //Empty the carts
        System.out.println("This is customer id: "+customer.getCustomerId());

        cartRepository.deleteAllByCustomerId(customer.getCustomerId());
        customerCarts = null;

//        customer.setCart(customerCarts);

        System.out.println(customer.getCart());

        Customer savedCustomer = customerService.saveCustomer(customer);

        System.out.println(savedCustomer.getCart());

        Order createdOrder = orderRepository.save(order);
        ReceiptDto receipt = new ReceiptDto(order.getOrderId(), order.getDateOfPurchase(),order.getPaymentMethod(),order.getTotalCost(), order.getOrderedItems());

        return createdOrder;
    }

    public Order viewOrder(Integer orderNumber){
        return orderRepository.findById(orderNumber).get();
    }


}
