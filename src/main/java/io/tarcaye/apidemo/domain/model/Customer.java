package io.tarcaye.apidemo.domain.model;

public class Customer {
    private final Long id;

    private Customer(Long id) {
        this.id = id;
    }

    public static Customer of(String id) {
        return new Customer(Long.valueOf(id));
    }

    public Long getId() {
        return id;
    }
}
