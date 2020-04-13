package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final Map<String, List<Order>> ordersPerUser;

    @Autowired
    public OrderRepository() {
        this.ordersPerUser = new ConcurrentHashMap<>();
    }

    public Map<String, List<Order>> getOrdersPerUser() {
        return ordersPerUser;
    }

    public Order addOrder(String userId, Order order) {
        if (!ordersPerUser.containsKey(userId)) {
            ordersPerUser.put(userId, new ArrayList<>());
        }
        List<Order> listOfOrders = ordersPerUser.get(userId);
        listOfOrders.add(order);
        return order;
    }

    public List<Order> getReportOfOrders(String userId) {
        return ordersPerUser.get(userId);
    }
}
