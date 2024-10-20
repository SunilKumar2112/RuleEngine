package com.example.ruleengineast.controller.test;

import com.example.ruleengineast.controller.RuleController;
import com.example.ruleengineast.entity.Rule;
import com.example.ruleengineast.service.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RuleControllerTest {

    @Mock
    private RuleService ruleService;

    @InjectMocks
    private RuleController ruleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testCreateRule_Success() {
        // Mock input request
        Map<String, String> request = new HashMap<>();
        request.put("ruleName", "Test Rule");
        request.put("ruleString", "age > 30 AND salary > 50000");

        // Mock RuleService response
        Rule mockRule = new Rule();
        when(ruleService.createRule(anyString(), anyString())).thenReturn(mockRule);

        // Call the createRule endpoint
        ResponseEntity<?> response = ruleController.createRule(request);

        // Assert success response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRule, response.getBody());
    }

    @Test
    public void testCreateRule_Failure() {
        // Mock input request
        Map<String, String> request = new HashMap<>();
        request.put("ruleName", "Test Rule");
        request.put("ruleString", "INVALID_RULE_STRING");

        // Mock RuleService to throw an exception
        when(ruleService.createRule(anyString(), anyString())).thenThrow(new IllegalArgumentException("Error parsing rule"));

        // Call the createRule endpoint
        ResponseEntity<?> response = ruleController.createRule(request);

        // Assert error response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error creating rule: Error parsing rule", response.getBody());
    }

    @Test
    public void testCombineRules_Success() {
        // Mock input request
        Map<String, Object> request = new HashMap<>();
        request.put("ruleId1", 1L);
        request.put("ruleId2", 2L);
        request.put("operator", "AND");

        // Mock RuleService response
        Rule mockCombinedRule = new Rule();
        when(ruleService.combineRules(anyLong(), anyLong(), anyString())).thenReturn(mockCombinedRule);

        // Call the combineRules endpoint
        ResponseEntity<?> response = ruleController.combineRules(request);

        // Assert success response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCombinedRule, response.getBody());
    }

    @Test
    public void testCombineRules_Failure() {
        // Mock input request
        Map<String, Object> request = new HashMap<>();
        request.put("ruleId1", 1L);
        request.put("ruleId2", 2L);
        request.put("operator", "INVALID_OPERATOR");

        // Mock RuleService to throw an exception
        when(ruleService.combineRules(anyLong(), anyLong(), anyString())).thenThrow(new IllegalArgumentException("Invalid operator"));

        // Call the combineRules endpoint
        ResponseEntity<?> response = ruleController.combineRules(request);

        // Assert error response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error combining rules: Invalid operator", response.getBody());
    }

    @Test
    public void testEvaluateRule_Success() {
        // Mock input request
        Map<String, Object> request = new HashMap<>();
        request.put("ruleId", 1L);
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        userData.put("salary", 60000);
        request.put("userData", userData);

        // Mock RuleService response
        when(ruleService.evaluateRule(anyLong(), anyMap())).thenReturn(true);

        // Call the evaluateRule endpoint
        ResponseEntity<?> response = ruleController.evaluateRule(request);

        // Assert success response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    public void testEvaluateRule_Failure() {
        // Mock input request
        Map<String, Object> request = new HashMap<>();
        request.put("ruleId", 1L);
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        request.put("userData", userData);

        // Mock RuleService to throw an exception
        when(ruleService.evaluateRule(anyLong(), anyMap())).thenThrow(new IllegalArgumentException("Rule not found"));

        // Call the evaluateRule endpoint
        ResponseEntity<?> response = ruleController.evaluateRule(request);

        // Assert error response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error evaluating rule: Rule not found", response.getBody());
    }
}
