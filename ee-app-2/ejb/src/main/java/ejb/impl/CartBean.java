package ejb.impl;

import ejb.remote.Cart;
import jakarta.ejb.Stateful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Stateful
public class CartBean implements Cart {
    private Map<String,Object> items = new HashMap<>();

    @Override
    public void addItem(String item) {
        items.put(item,item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public double getTotal() {
        return items.size();
    }

    @Override
    public void removeItem(String item) {
        items.remove(item);
    }

    @Override
    public Map<String, Object> getItems() {
        return items;
    }
}
