package com.example.demo.repository.persistence;

import com.example.demo.entity.JournalMarkEntity;
import com.example.demo.model.JournalMark;
import com.example.demo.model.Student;
import com.example.demo.repository.domain.JournalMarkRepository;
import com.example.demo.repository.mapper.JournalMarkMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JournalMarkJRepository implements JournalMarkRepository {
    @Override
    public JournalMark add(Connection conn, JournalMark jm) throws Exception {
        String sql =  """
                INSERT INTO journal_marks (journal_id, student_id, mark)
                VALUES (?,?,?,?)
                """;
        try(PreparedStatement ps= conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            JournalMarkEntity j = JournalMarkMapper.toEntity(jm);
            ps.setLong(1,j.getJournalID());
            ps.setLong(2,j.getStudentID());
            ps.setInt(3,j.getMark());

            ps.executeUpdate();

            try(ResultSet res = ps.getGeneratedKeys()){
                if(res.next()){
                    return JournalMarkMapper.toDomain(
                            JournalMarkEntity
                                    .builder()
                                    .jmID(res.getLong("journal_entry_id"))
                                    .mark(res.getInt("mark"))
                                    .date(res.getDate("date"))
                                    .journalID(res.getLong("journal_id"))
                                    .studentID(res.getLong("student_id"))
                                    .build());
                }
            }
        }
        return null;
    }

    @Override
    public Optional<JournalMark> updateMark(Connection conn, Long jmId, int mark) throws Exception {
        String sql = """
        UPDATE journal_marks
        SET mark = ?, date = NOW()
        WHERE journal_entry_id = ?
        RETURNING journal_entry_id, journal_id, student_id, mark, date
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, mark);
            ps.setLong(2, jmId);

            try (ResultSet res = ps.executeQuery()) {

                if (res.next()) {
                    JournalMark journalMark = JournalMark
                            .builder()
                            .jmID(res.getLong("journal_entry_id"))
                            .mark(res.getInt("mark"))
                            .date(res.getDate("date"))
                            .journalID(res.getLong("journal_id"))
                            .studentID(res.getLong("student_id"))
                            .build();

                    return Optional.of(journalMark);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<JournalMark> getMarks(Connection conn, Long journalId) throws Exception {
        String sql = "SELECT journal_entry_id, journal_id, student_id, mark, date WHERE journal_id=?";

        try(PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet res=ps.executeQuery()){
            ps.setLong(1,journalId);
            List<JournalMark> list = new ArrayList<>();
            ps.executeUpdate();

            while(res.next()){
                JournalMarkEntity je=JournalMarkEntity
                                .builder()
                                .jmID(res.getLong("journal_entry_id"))
                                .mark(res.getInt("mark"))
                                .date(res.getDate("date"))
                                .journalID(res.getLong("journal_id"))
                                .studentID(res.getLong("student_id"))
                                .build();

                list.add(JournalMarkMapper.toDomain(je));

            }
            return list;
        }
    }

    @Override
    public boolean delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM journal_marks WHERE journal_entry_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
