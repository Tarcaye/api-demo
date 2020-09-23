package io.tarcaye.apidemo.domain.model;

public class Contract {

    private final Customer customer;

    public Contract(Customer customer) {

        this.customer = customer;
    }

    public boolean belongsTo(Customer customer) {
        return this.customer.getId().equals(customer.getId());
    }
}
