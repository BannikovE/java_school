package app.model.cart;

import app.model.Product;
import app.model.User;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int orderNum;
    private UserDTO userDTO;
    private final List<CartLine> cartLines = new ArrayList<CartLine>();

    public Cart() {
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    private CartLine findLineById(int id){
        for (CartLine line : this.cartLines){
            if (line.getProduct().getId().equals(id))
                return line;
        }
        return null;
    }

    public void addProduct(ProductDTO productDTO, int quantity){
        CartLine cartLine = this.findLineById(productDTO.getId());
        if (cartLine == null){
            cartLine = new CartLine();
            cartLine.setQuantity(0);
            cartLine.setProduct(productDTO);
            this.cartLines.add(cartLine);
        }
        int newQuantity = cartLine.getQuantity() + quantity;
        if (newQuantity <= 0)
            this.cartLines.remove(cartLine);
        else
            cartLine.setQuantity(newQuantity);
    }

    public void updateProduct(int id, int quantity){
        CartLine cartLine = this.findLineById(id);
        if (cartLine != null){
            if (quantity <= 0)
                this.cartLines.remove(cartLine);
            else
                cartLine.setQuantity(quantity);
        }
    }

    public void removeProduct(ProductDTO productDTO){
        CartLine cartLine = this.findLineById(productDTO.getId());
        if (cartLine != null)
            this.cartLines.remove(cartLine);
    }

    public boolean isEmpty(){
        return this.cartLines.isEmpty();
    }

    public int getQuantityTotal(){
        int quantity = 0;
        for (CartLine cartLine : this.cartLines)
            quantity += cartLine.getQuantity();
        return quantity;
    }

    public double getAmountTotal(){
        double total = 0;
        for (CartLine cartLine : this.cartLines)
            total += cartLine.getAmount();
        return total;
    }

    public void updateQuantity(Cart cart){
        if (cart != null){
            List<CartLine> lines = cart.getCartLines();
            for (CartLine cartLine : lines)
                this.updateProduct(cartLine.getProduct().getId(), cartLine.getQuantity());
        }
    }
}

