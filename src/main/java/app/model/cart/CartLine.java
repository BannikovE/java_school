package app.model.cart;

import app.model.Product;

public class CartLine {
    private ProductDTO product;
    private int quantity;

    public CartLine() {
        this.quantity = 0;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){
        return this.product.getPrice() * this.quantity;
    }
}
