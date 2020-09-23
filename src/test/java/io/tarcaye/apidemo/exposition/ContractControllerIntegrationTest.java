package io.tarcaye.apidemo.exposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tarcaye.apidemo.ApiDemoApplication;
import io.tarcaye.apidemo.exposition.model.ContractRequest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(classes = ApiDemoApplication.class)
class ContractControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getContracts() throws Exception {
        String uri = "/customers/9001/contracts";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Approvals.verify(content);
    }

    @Test
    void addContract() throws Exception {
        String uri = "/customers/21/contracts";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(new ContractRequest("12", "ACHAT")))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Approvals.verify(content);
    }

    @Test
    void addAndRetrieveContracts() throws Exception {
        String uri = "/customers/42/contracts";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(new ContractRequest("12", "ACHAT")))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(new ContractRequest("14", "ACHAT")))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(new ContractRequest("156", "ACHAT")))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(new ContractRequest("178", "LOCATION")))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Approvals.verify(content);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}