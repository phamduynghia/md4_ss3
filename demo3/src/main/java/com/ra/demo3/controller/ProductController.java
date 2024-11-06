package com.ra.demo3.controller;

import com.ra.demo3.model.dto.ProductDto;
import com.ra.demo3.model.entity.Product;
import com.ra.demo3.service.category.CategoryService;
import com.ra.demo3.service.product.ProductService;
import com.ra.demo3.service.updateFile.UpdateFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UpdateFileService updateFileService;

    @GetMapping
    public ResponseEntity<List<Product>> index() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @ModelAttribute  ProductDto productDto,
                                          BindingResult result, Model model) {
        Product productNew = new Product();
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productNew.setProductName(productDto.getProductName());
        productNew.setProductPrice(productDto.getPrice());
        productNew.setProductStatus(productDto.isStatus());

        productNew = productService.save(productDto);
            return new ResponseEntity<>(productNew, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        Map<String, String> error = new HashMap<>();
        error.put("message", "Product not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @ModelAttribute ProductDto productDto,
                                    BindingResult result, Model model) {
        // Kiểm tra xem có lỗi xác thực dữ liệu trong đối tượng ProductDto hay không
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Tìm sản phẩm theo ID từ database
        Product product = productService.findById(id);
        if (product == null) {
            // Nếu không tìm thấy sản phẩm, trả về NOT_FOUND với thông báo lỗi
            Map<String, String> error = new HashMap<>();
            error.put("message", "Product not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        }
        // Cập nhật tên và giá của sản phẩm dựa vào dữ liệu từ ProductDto
        String image = updateFileService.updateFile(productDto.getFileImage());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getPrice());
        product.setProductStock(productDto.getProductStock());
        product.setProductImage(image);
        product.setProductStatus(productDto.isStatus());
        // Gọi phương thức update trong ProductService để lưu sản phẩm đã cập nhật
        if (productService.update(product)) {
            // Nếu cập nhật thành công, trả về đối tượng sản phẩm và trạng thái OK
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        // Nếu cập nhật thất bại, trả về trạng thái INTERNAL_SERVER_ERROR
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Product not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        if (productService.delete(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
