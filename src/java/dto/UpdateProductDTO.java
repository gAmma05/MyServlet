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
 * @author gAmma
 */
public class UpdateProductDTO implements DtoBase{
    
    private String updName;
    private String updPrice;
    private String updProductYear;
    private String updImage;
    private String updCategoryId;
    
    public UpdateProductDTO(){
        
    }

    public UpdateProductDTO(String updName, String updPrice, String updProductYear, String updImage, String updCategoryId) {
        this.updName = updName;
        this.updPrice = updPrice;
        this.updProductYear = updProductYear;
        this.updImage = updImage;
        this.updCategoryId = updCategoryId;
    }
    
    public String getUpdName() {
        return updName;
    }

    public void setUpdName(String updName) {
        this.updName = updName;
    }

    public String getUpdPrice() {
        return updPrice;
    }

    public void setUpdPrice(String updPrice) {
        this.updPrice = updPrice;
    }

    public String getUpdProductYear() {
        return updProductYear;
    }

    public void setUpdProductYear(String updProductYear) {
        this.updProductYear = updProductYear;
    }

    public String getUpdImage() {
        return updImage;
    }

    public void setUpdImage(String updImage) {
        this.updImage = updImage;
    }

    public String getUpdCategoryId() {
        return updCategoryId;
    }

    public void setUpdCategoryId(String updCategoryId) {
        this.updCategoryId = updCategoryId;
    }
    
    
    
    @Override
    public void validate() throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();

        //validate name
        if (updName == null || updName.trim().isEmpty()) {
            errors.add(new ValidationError("name", "Product name cannot be empty."));
        }
        
        // validate price
        try {
            float priceValue = Float.parseFloat(updPrice);
            if (priceValue <= 0) {
                errors.add(new ValidationError("price", "Price must be greater than 0."));
            }
        } catch (NumberFormatException e) {
            errors.add(new ValidationError("price", "Price must be a valid number."));
        }
        
        // validate product year
        try {
            int productYearValue = Integer.parseInt(updProductYear);
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if (productYearValue <= 1900 || productYearValue > currentYear) {
                errors.add(new ValidationError("productYear", "Product year must be between 1900 and the current year."));
            }
        } catch (NumberFormatException e) {
            errors.add(new ValidationError("productYear", "Product year must be a valid number."));
        }
        
        // validate image url
        if (updImage == null || updImage.trim().isEmpty()) {
            errors.add(new ValidationError("image", "Image URL cannot be empty."));
        }
        
        // validate category
        try {
            int categoryIdValue = Integer.parseInt(updCategoryId);
            if (categoryIdValue <= 0) {
                errors.add(new ValidationError("categoryId", "Category ID is invalid."));
            }
        } catch (NumberFormatException e) {
            errors.add(new ValidationError("categoryId", "Category ID must be a valid number."));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
}
