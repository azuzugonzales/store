package com.home.it.store.controllers;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.utils.LoggingUtil;
import org.apache.log4j.Logger;

import java.util.*;

public class OrderController {
    public static final Logger logger = Logger.getLogger(LoggingUtil.getCurrentClassName());
    private static Set<Order> orders = new LinkedHashSet<>();

    public OrderController() {}

    public Order createOrder(int customerId) {
        Order order = new Order();
        order.setId(getMaxId() + 1);
        order.setCustomerId(customerId);
        orders.add(order);
        return order;
    }

    public void addGarmentToOrder(Order order, Garment garment) {
        order.getGarments().add(garment);
        logger.debug("Garment id = " + garment.getId() + " , added to order id = " + order.getId()
            + ". Orders list now is " + Arrays.toString(orders.toArray(new Order[0])));
    }

    private int getMaxId() {
        if (orders.size() == 0) {
            return 0;
        } else {
            return ((Order) orders.toArray()[orders.size() - 1]).getId();
        }
    }

    public class Order {
        private int id;
        private List<Garment> garments;
        private int customerId;

        private Order() {
            garments = new LinkedList<>();
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Garment> getGarments() {
            return garments;
        }

        public void setGarments(List<Garment> garments) {
            this.garments = garments;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Order)) return false;

            Order order = (Order) o;

            if (id != order.id) return false;
            if (customerId != order.customerId) return false;
            return !(garments != null ? !garments.equals(order.garments) : order.garments != null);
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", garments=" + garments +
                    ", customerId" + customerId +
                    '}';
        }
    }
}
