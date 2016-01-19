package com.home.it.store.controllers;

import com.home.it.jdbc.beans.Garment;
import com.home.it.jdbc.utils.LoggingUtil;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OrderController {
    public static final Logger logger = Logger.getLogger(LoggingUtil.getCurrentClassName());
    private static Set<Order>

    public class Order {
        private int id;
        private List<Garment> garments;

        private Order {
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
        public boolean equals(Object obj) {
            if (this == o) return true;
            if (!(o instanceof Order)) return false;

            Order order = (Order) o;

            if (id != order.id) return false;
            if (customerId != )
        }
    }
}
