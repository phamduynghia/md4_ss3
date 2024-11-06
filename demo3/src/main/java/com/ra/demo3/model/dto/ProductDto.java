package com.ra.demo3.model.dto;

import com.ra.demo3.model.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @Size(min = 10,message = "Tối thiểu 10 ký tự")
    private String productName;
    @Min(value = 1,message = "Giá không được nhỏ hơn 0")
    private double price;
    private Integer productStock;
    private MultipartFile fileImage;
    private boolean status;

    private Category category;
}
