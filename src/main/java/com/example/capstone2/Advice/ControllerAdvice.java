package com.example.capstone2.Advice;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Api.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    // api exception!
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    // validaiton exception!
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse>
    MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    //when the method requierments is different ; when i need to use GET but i used POST by mistake!
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedExcpetion(HttpRequestMethodNotSupportedException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    //when i foregt , or : in json format it will show this error!
    @ExceptionHandler(value =  HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // for url when i change string into integer or integer into string
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // for url!
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> NoResourceFoundException(NoResourceFoundException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // this for data integerity
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }


    // Could not commit JPA transaction
    // i got this error when i forget to add ticket status into check but it's already exist in pattern
    /*
    to make it more clear!
    in patter(regex="On Hold")
    in Column(columndefiniation = varchar(15) not null) << without adding On Hold in check!
    but after i added On Hold into (check) it fixed!
     */
    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity TransactionSystemException(TransactionSystemException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    // i didn't face this error but i added it coz it may happen!
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity RunTimeException(RuntimeException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

}

