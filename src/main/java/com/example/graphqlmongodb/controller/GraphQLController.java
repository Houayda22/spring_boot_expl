package com.example.graphqlmongodb.controller;

import java.util.Map;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping(value = "/graphql", consumes = "application/json", produces = "application/json")
    public ExecutionResult executeGraphQLQuery(@RequestBody Map<String, Object> query) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query((String) query.get("query"))
                .build();
        return graphQL.execute(executionInput);
    }
}