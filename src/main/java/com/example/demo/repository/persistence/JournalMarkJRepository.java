package com.example.demo.repository.persistence;

import com.example.demo.model.JournalMark;
import com.example.demo.repository.domain.JournalMarkRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JournalMarkJRepository implements JournalMarkRepository {
    @Override
    public JournalMark add(Connection conn, JournalMark jm) throws Exception {
        String sql =  """
                INSERT INTO (
                """;
        return null;
    }

    @Override
    public Optional<JournalMark> updateMark(Connection conn, Long jmId, int mark) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<JournalMark> getMarks(Connection conn, Long journalId) throws Exception {
        return List.of();
    }

    @Override
    public boolean delete(Connection conn, Long id) throws Exception {
        return false;
    }
}
