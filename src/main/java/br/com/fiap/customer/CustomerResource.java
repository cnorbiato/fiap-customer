package br.com.fiap.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.xml.ws.Response;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CustomerResource {

    @Autowired
    private  CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerCreateResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.status(CREATED).body(customerService.create(customerRequest));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(HttpServerErrorException httpServerErrorException){
        return ResponseEntity.status(httpServerErrorException.getStatusCode()).body(new ErrorResponse(httpServerErrorException.getStatusText()));
    }
}
