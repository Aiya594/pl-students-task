package com.example.demo.repository.domain;

import com.example.demo.model.Journal;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface JournalRepository {
    Journal add(Connection conn, Journal j) throws Exception;
    Optional<Journal> getById(Connection conn, Long id) throws Exception;
    List<Journal> list(Connection conn) throws Exception;
    //boolean delete(Connection conn, Long id) throws Exception;
}
