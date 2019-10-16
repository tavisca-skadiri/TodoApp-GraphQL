package com.tavisca.workshops.todoappgraphql.service;

import com.tavisca.workshops.todoappgraphql.service.datafetcher.AllTodosDataFetcher;
import com.tavisca.workshops.todoappgraphql.service.datafetcher.TodoDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    @Value("classpath:todo.graphql")
    Resource resource;
    private GraphQL graphQL;
    @Autowired
    private TodoDataFetcher todoDataFetcher;
    @Autowired
    private AllTodosDataFetcher allTodosDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        File file = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        GraphQLSchema graphQLSchema = new SchemaGenerator()
                .makeExecutableSchema(
                        typeDefinitionRegistry,
                        buildRuntimeWiring()
                );
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("todo", todoDataFetcher)
                        .dataFetcher("allTodos", allTodosDataFetcher)
                ).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}