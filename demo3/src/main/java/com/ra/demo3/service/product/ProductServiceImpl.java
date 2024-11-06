package com.ra.demo3.service.product;

import com.ra.demo3.model.dto.ProductDto;
import com.ra.demo3.model.entity.Product;
import com.ra.demo3.repository.ProductRepository;
import com.ra.demo3.service.updateFile.UpdateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UpdateFileService updateFileService;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UpdateFileService updateFileService) {
        this.productRepository = productRepository;
        this.updateFileService = updateFileService;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(ProductDto productDto) {
        String img = updateFileService.updateFile(productDto.getFileImage());

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getPrice());
        product.setProductImage(img);
        product.setCategory(productDto.getCategory());
        product.setProductStatus(true);
        return productRepository.save(product);
    }

    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return false;
    }



    @Override
    public boolean update(Product product) {
        // Kiểm tra xem sản phẩm đã tồn tại chưa
        if (productRepository.existsById(product.getId())) {
            // Nếu tồn tại, lưu cập nhật vào cơ sở dữ liệu
            productRepository.save(product);
            return true;
        }
        // Nếu không tồn tại, trả về false
        return false;
    }

}
