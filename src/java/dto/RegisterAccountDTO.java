/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.AccountDAO;
import exceptions.ValidationError;
import exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gAmma
 */
public class RegisterAccountDTO implements DtoBase{
    private String username;
    private String password;
    
    public RegisterAccountDTO(){
        
    }
    
    public RegisterAccountDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void validate() throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();

        if(username == null || username.trim().isEmpty()){
            errors.add(new ValidationError("username", "This field cannot be empty"));
        }
        
        if(password == null || password.trim().isEmpty()){
            errors.add(new ValidationError("password", "This field cannot be empty"));
        }else if(password.length() <= 4){
            errors.add(new ValidationError("password", "The password must be more than 4 characters"));
        }
        
        AccountDAO accountDAO = new AccountDAO();
        List<String> userList = accountDAO.getAllAccount();
        for(String user : userList){
            if(username.equals(user)){
                errors.add(new ValidationError("username", "This username is already in use. Try other username!"));
            }
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
}
