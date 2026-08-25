package com.example.demo.repository.persistence;

import com.example.demo.entity.JournalEntity;
import com.example.demo.model.Journal;
import com.example.demo.repository.domain.JournalRepository;
import com.example.demo.repository.mapper.JournalMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class JournalJRepository implements JournalRepository {
    @Override
    public Journal add(Connection conn, Journal j) throws Exception {

        String sql =  """
                INSERT INTO journals (study_year, group_id, subject_id)
                VALUES (?,?,?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            JournalEntity je = JournalMapper.toEntity(j);

            ps.setString(1,je.getStudyYear());
            ps.setLong(2,je.getGroupID());
            ps.setLong(3,je.getSubjectID());

            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    return JournalMapper.toDomain(
                      JournalEntity.builder()
                              .journalID(rs.getLong("journal_id"))
                              .studyYear(rs.getString("study_year"))
                              .groupID(rs.getLong("group_id"))
                              .subjectID(rs.getLong("subject_id"))
                              .studentID(rs.getLong("student_id"))
                              .build()
                    );
                }
            }
        }

        return null;
    }

    @Override
    public Optional<Journal> getById(Connection conn, Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Journal> list(Connection conn) throws Exception {
        return List.of();
    }

//    @Override
//    public boolean delete(Connection conn, Long id) throws Exception {
//        return false;
//    }
}
