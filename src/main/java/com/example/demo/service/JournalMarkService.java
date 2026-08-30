package com.example.demo.service;

import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.AppException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.JournalMark;
import com.example.demo.repository.domain.JournalMarkRepository;
import com.example.demo.repository.domain.JournalRepository;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.JournalMarkValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalMarkService {

    private final JournalMarkRepository markRepo;
    private final StudentRepository studentRepo;
    private final JournalRepository journalRepo;

    public JournalMark add(JournalMark jm) {
        try (Connection conn = DBUtil.getConnection()) {

            JournalMarkValidator.validate(jm);

            if (studentRepo.getStudentById(conn, jm.getStudentID()).isEmpty()) {
                throw new NotFoundException("Student with id=" + jm.getStudentID() + " not found");
            }

            if (journalRepo.getById(conn, jm.getJournalID()).isEmpty()) {
                throw new NotFoundException("Journal with id=" + jm.getJournalID() + " not found");
            }

            return markRepo.add(conn, jm);

        }catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        }catch (Exception e) {
            throw new AppException("Error while adding journal mark: "+ e);
        }
    }

    public JournalMark updateMark(Long jmId, int mark) {
        try (Connection conn = DBUtil.getConnection()) {

            Optional<JournalMark> result =markRepo.updateMark(conn, jmId, mark);

            if (result.isEmpty()) {
                throw new NotFoundException("Journal mark with id=" + jmId + " not found");
            }

            return result.get();

        }catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Error while updating journal mark: "+ e);
        }
    }

    public List<JournalMark> getMarks(Long journalId) {
        try (Connection conn = DBUtil.getConnection()) {

            if (journalRepo.getById(conn, journalId).isEmpty()) {
                throw new NotFoundException("Journal with id=" + journalId + " not found");
            }

            return markRepo.getMarks(conn, journalId);
        } catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Error while getting journal marks: "+ e);
        }
    }

    public boolean delete(Long id) {
        try (Connection conn = DBUtil.getConnection()) {

            boolean deleted = markRepo.delete(conn, id);

            if (!deleted) {
                throw new NotFoundException("Journal mark with id=" + id + " not found");
            }

            return true;

        } catch (Exception e) {
            throw new AppException("Error while deleting journal mark: "+ e);
        }
    }
}
