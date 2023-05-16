package com.llw.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {

    //商品id
    private Integer id;
    //商品名称
    private String name;
    //商品数量
    private Integer count = 1;
    //商品单价
    private BigDecimal price;
    //商品总价金额
    private BigDecimal totalPrice;

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public CartItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getId(), cartItem.getId()) && Objects.equals(getName(), cartItem.getName()) && Objects.equals(getCount(), cartItem.getCount()) && Objects.equals(getPrice(), cartItem.getPrice()) && Objects.equals(getTotalPrice(), cartItem.getTotalPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCount(), getPrice(), getTotalPrice());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count != null && count > 0) {
            this.count = count;
            this.setTotalPrice(this.getPrice().multiply(new BigDecimal(count.toString())));
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price != null && price.compareTo(new BigDecimal("0")) >= 0) {
            this.price = price;
            this.setTotalPrice(price.multiply(new BigDecimal(this.count.toString())));
        }
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    private void setTotalPrice(BigDecimal totalPrice) {
        if (totalPrice != null && totalPrice.compareTo(new BigDecimal("0")) >= 0) {
            this.totalPrice = totalPrice;
        }
    }
}
