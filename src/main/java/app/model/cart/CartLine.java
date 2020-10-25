package app.model.cart;

public class CartLine {
    private ProductDTO productDTO;
    private int quantity;

    public CartLine() {
        this.quantity = 1;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount(){
        return this.productDTO.getPrice() * this.quantity;
    }
}
