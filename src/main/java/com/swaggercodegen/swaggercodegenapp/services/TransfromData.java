package com.swaggercodegen.swaggercodegenapp.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.swaggercodegen.swaggercodegenapp.model.RawTransaction;
import com.swaggercodegen.swaggercodegenapp.model.Transaction;
import com.swaggercodegen.swaggercodegenapp.model.TransactionDetail;

@Service
public class TransfromData {

    private String str = "[{\"orderRef\":\"0227b25c-a015-4adf-83ce-159427ed8be4\",\"referenceId\":\"14077145\",\"policyNum\":2971286348,\"proposalNum\":\"\",\"serviceType\":\"8 - ONLINE PAYMENT\",\"partnerName\":\"VTBOL10\",\"accnumOfPartner\":60003742590,\"paymentDate\":\"2023-07-27 11:16:39.396516\",\"bankDate\":null,\"bankRef\":null,\"description\":null,\"debitAmount\":null,\"accountNum\":null,\"accountName\":null,\"billId\":\"02023052900052\",\"policyStatus\":null,\"reason\":null,\"status\":\"COMPLETED\",\"applyDate\":null,\"returnDate\":null,\"pendingDays\":7,\"userId\":nul…Num\":null,\"serviceType\":\"8 - ONLINE PAYMENT\",\"partnerName\":\"VTBOL10\",\"accnumOfPartner\":60003742590,\"paymentDate\":\"2023-07-27 14:15:48.410107\",\"bankDate\":null,\"bankRef\":null,\"description\":null,\"debitAmount\":null,\"accountNum\":null,\"accountName\":null,\"billId\":\"O2023072100116\",\"policyStatus\":null,\"reason\":301,\"status\":\"COMPLETED\",\"applyDate\":null,\"returnDate\":null,\"pendingDays\":7,\"userId\":null,\"modifiedDate\":null,\"remark\":null,\"createdDate\":null,\"creditAmount\":300000,\"totalCreditAmount\":17100000}]";

    public TransactionDetail transform() throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        
        List<RawTransaction> rawTransactions = objectMapper.readValue(str, new TypeReference<List<RawTransaction>>() {
        });

        // TransactionDetail transactionDetail = new TransactionDetail();
        // transactionDetail.getDetail().forEach(t);
        // Transaction transaction = new Transaction();
        return null;
    }
}
