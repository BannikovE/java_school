package app;

import app.model.Product;
import app.service.ProductService;
import app.validators.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductValidatorTest {

    @InjectMocks
    private ProductValidator productValidator;
    @Mock
    private ProductService productService;

    private static Product product;
    private static Errors errors;

    private static final int QUANTITY_VALID = 10;
    private static final int QUANTITY_INVALID = -1;
    private static final String COLOR_VALID = "blue";
    private static final String COLOR_INVALID = "1blue";
    private static final int SIZE_VALID = 10;
    private static final int SIZE_INVALID = -1;
    private static final String BRAND_VALID = "blue";
    private static final String BRAND_INVALID = "1blue";
    private static final int PRICE_VALID = 10;
    private static final int PRICE_INVALID = -1;
    private static final String NAME_VALID = "blue";
    private static final String NAME_INVALID = "1blue";
    private static final String EMPTY_STRING = "";

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setQuantityInStock(QUANTITY_VALID);
        product.setColor(COLOR_VALID);
        product.setBrand(BRAND_VALID);
        product.setSize(SIZE_VALID);
        product.setPrice(PRICE_VALID);
        product.setName(NAME_VALID);
        product.setCategoryId(1);
        errors = new BeanPropertyBindingResult(product, "product");
        lenient().when(productService.getProductById(anyInt())).thenReturn(null);
    }

    @Test
    public void validateProductValid() {
        productValidator.validate(product, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validateProductNameEmpty() {
        product.setName(EMPTY_STRING);
        productValidator.validate(product, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("name"));
    }
}
