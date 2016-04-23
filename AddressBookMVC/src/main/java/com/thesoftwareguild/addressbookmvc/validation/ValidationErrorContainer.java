
package com.thesoftwareguild.addressbookmvc.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorContainer {
    
    
    private List<ValidationError> errors = new ArrayList();
    
    public void addError(String message, String fieldName) {
        errors.add(new ValidationError(fieldName, message));
    }
    
    public List<ValidationError> getErrors() {
        return errors;
    }
    
    
}
