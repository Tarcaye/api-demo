package io.tarcaye.apidemo.domain.model;

import java.util.List;

public class Contracts {

    private final List<Contract> values;

    public Contracts(List<Contract> values) {
        this.values = values;
    }

    public List<Contract> asList() {
        return values;
    }
}
