package io.tarcaye.apidemo.exposition.model;

import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Customer;

public class ContractRequest {

    public String id;

    public String type;

    public ContractRequest(String id, String type) {
        this.type = type;
        this.id = id;
    }

    public Contract toCore(String customerId){
        return new Contract(Customer.of(customerId), Long.parseLong(id), Contract.Type.valueOf(type));
    }
}
