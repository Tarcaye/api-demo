package io.tarcaye.apidemo.exposition.model;

import io.tarcaye.apidemo.domain.model.Contract;
import io.tarcaye.apidemo.domain.model.Contracts;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ContractsResponse {

    private final List<ContractResponse> contracts;

    public ContractsResponse(List<ContractResponse> contracts) {
        this.contracts = contracts;
    }
    public ContractsResponse() {
        this.contracts = emptyList();
    }

    public static ContractsResponse of(Contracts contracts) {
        List<ContractResponse> contractResponses = contracts.asList().stream()
                .map(ContractResponse::from)
                .collect(toList());

        return new ContractsResponse(contractResponses);
    }

    public List<ContractResponse> getContracts() {
        return unmodifiableList(contracts);
    }

    public static class ContractResponse {
        private final long id;
        private final Type type;

        public ContractResponse() {
            id=0;
            type = null;
        }
        public ContractResponse(long id, Type type) {

            this.id = id;
            this.type = type;
        }

        public static ContractResponse from(Contract contract) {
            return new ContractResponse(113L, Type.ACHAT);
        }

        public long getId() {
            return id;
        }

        public Type getType() {
            return type;
        }

        enum Type {
            ACHAT
        }
    }
}
