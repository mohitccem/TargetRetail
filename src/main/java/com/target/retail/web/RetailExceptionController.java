package com.target.retail.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.target.retail.dto.ErrorResponse;
import com.target.retail.exceptions.InternalServerException;
import com.target.retail.exceptions.InvalidRequestException;
import com.target.retail.exceptions.ProductNotFoundException;

@ControllerAdvice
public class RetailExceptionController extends InternalServerException {

    private static final Logger log = LoggerFactory.getLogger(RetailExceptionController.class);

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(final ProductNotFoundException ex) {
        log.error("Product Not found ", ex);
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Product Not found for " + ex.getMessage());
        return error;
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequest(final InvalidRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid Request. " + ex.getMessage());
        return errorResponse;
    }

}