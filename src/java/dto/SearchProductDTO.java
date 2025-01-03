/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import exceptions.ValidationError;
import exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vothimaihoa
 */
public class SearchProductDTO implements DtoBase {

    private String categoryId;
    private String productName;
    private String minPrice;
    private String maxPrice;

    public SearchProductDTO() {
    }

    public SearchProductDTO(String categoryId, String productName, String minPrice, String maxPrice) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void validate() throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            try {
                int categoryIdValue = Integer.parseInt(categoryId);
                if (categoryIdValue <= 0) {
                    errors.add(new ValidationError("categoryId", "Category ID is invalid."));
                }
            } catch (NumberFormatException e) {
                errors.add(new ValidationError("categoryId", "Category ID must be a valid number."));
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
