package com.tavisca.workshops.todoappgraphql.service.datafetcher;

import com.tavisca.workshops.todoappgraphql.model.Todo;
import com.tavisca.workshops.todoappgraphql.repository.TodoRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllTodosDataFetcher implements DataFetcher<List<Todo>> {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> get(DataFetchingEnvironment environment) {
        return todoRepository.findAll();
    }
}
