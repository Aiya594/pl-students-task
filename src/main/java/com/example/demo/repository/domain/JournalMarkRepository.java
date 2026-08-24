package com.example.demo.repository.domain;

import com.example.demo.model.Journal;
import com.example.demo.model.JournalMark;

import java.net.ConnectException;
import java.sql.Connection;

public interface JournalMarkRepository {
    JournalMark add(Connection conn, JournalMark jm) throws Exception;



}
