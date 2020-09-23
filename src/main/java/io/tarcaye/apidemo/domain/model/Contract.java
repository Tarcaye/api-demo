package io.tarcaye.apidemo.domain.model;

public class Contract {

    public enum Type {
        LOCATION, ACHAT
    }

    private final long id;
    private final Customer customer;
    private final Type type;

    public Contract(Customer customer, long id, Type type) {
        this.id = id;
        this.customer = customer;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public boolean belongsTo(Customer customer) {
        return this.customer.getId().equals(customer.getId());
    }
}
