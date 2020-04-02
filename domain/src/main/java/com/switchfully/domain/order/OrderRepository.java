package com.switchfully.domain.order;

import com.switchfully.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private Map<String, List<Order>> ordersPerUser;

    public OrderRepository() {
        this.ordersPerUser = new ConcurrentHashMap<>();
    }

    public Order addOrder(String userId, Order order) {
        if (!ordersPerUser.containsKey(userId)) {
            ordersPerUser.put(userId, new ArrayList<>());
        }
        List<Order> listOfOrders = ordersPerUser.get(userId);
        listOfOrders.add(order);
        return order;
    }

    public List<Order> getReportOfOrders(String id) {
        return ordersPerUser.get(id);
    }
}
