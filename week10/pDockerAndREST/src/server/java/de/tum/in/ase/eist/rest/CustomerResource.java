package de.tum.in.ase.eist.rest;

import de.tum.in.ase.eist.model.Customer;
import de.tum.in.ase.eist.service.CustomerService;
import de.tum.in.ase.eist.util.CustomerSortingOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(value = "sortField", defaultValue = "ID") CustomerSortingOptions.SortField sortField,
            @RequestParam(value = "sortingOrder", defaultValue = "ASCENDING")
            CustomerSortingOptions.SortingOrder sortingOrder) {
        return ResponseEntity.ok(customerService.getAllPersons(new CustomerSortingOptions(sortingOrder,
                sortField)));
    }
    // TODO Part 1: Implement the specified endpoints here

    @PostMapping("customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        // response if id is not set as good else bad
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Customer toSave = customerService.savePerson(customer);
        return ResponseEntity.ok(toSave);
    }
    @PutMapping("customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCustomer,
                                                   @PathVariable("customerId") UUID customerId) {
        // check if condition not ok
        if (!updatedCustomer.getId().equals(customerId)) {
            return ResponseEntity.badRequest().build();
        }

        // update
        Customer toUpdate = customerService.savePerson(updatedCustomer);
        return ResponseEntity.ok(toUpdate);
    }
    @DeleteMapping("customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") UUID customerId) {
        if (customerId == null) {
            return ResponseEntity.badRequest().build();
        }

        customerService.deletePerson(customerId);
        // no content after success build
        return ResponseEntity.noContent().build();
    }

}
