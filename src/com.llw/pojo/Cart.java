package com.llw.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 添加商品
     *
     * @param cartitem
     */
    public void addItem(CartItem cartitem) {
        CartItem item = items.get(cartitem.getId());
        if (item == null) {
            items.put(cartitem.getId(), cartitem);
        } else {
            item.setCount(item.getCount() + 1);
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(int id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(int id, int count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
