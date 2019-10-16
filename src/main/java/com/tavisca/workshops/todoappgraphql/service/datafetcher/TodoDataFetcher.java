package com.tavisca.workshops.todoappgraphql.service.datafetcher;

import com.tavisca.workshops.todoappgraphql.model.Todo;
import com.tavisca.workshops.todoappgraphql.repository.TodoRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoDataFetcher implements DataFetcher<Todo> {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo get(DataFetchingEnvironment dataFetchingEnvironment) {
        int todoId = dataFetchingEnvironment.getArgument("id");
        return todoRepository.findById(todoId).orElse(null);
    }
}
