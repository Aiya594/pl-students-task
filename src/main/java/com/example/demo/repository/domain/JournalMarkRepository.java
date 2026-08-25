package com.example.demo.repository.domain;

import com.example.demo.model.Journal;
import com.example.demo.model.JournalMark;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface JournalMarkRepository {
    JournalMark add(Connection conn, JournalMark jm) throws Exception;
    Optional<JournalMark> updateMark(Connection conn, Long jmId, int mark) throws Exception;
    List<JournalMark> getMarks(Connection conn, Long journalId) throws Exception;
    boolean delete(Connection conn, Long id) throws Exception;
}
