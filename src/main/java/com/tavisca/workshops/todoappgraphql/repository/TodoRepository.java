package com.tavisca.workshops.todoappgraphql.repository;

import com.tavisca.workshops.todoappgraphql.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
