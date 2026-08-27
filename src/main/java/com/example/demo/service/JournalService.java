package com.example.demo.service;

import com.example.demo.exception.*;
import com.example.demo.model.Journal;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.repository.domain.JournalRepository;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.repository.domain.SubjectRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.JournalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepo;
    private final GroupRepository groupRepo;
    private final SubjectRepository subjectRepo;
    private final StudentRepository studentRepo;

    public Journal addJournal(Journal j) {
        try (Connection con = DBUtil.getConnection()) {
            JournalValidator.validate(j);

            int secondYear = Integer.parseInt(j.getStudyYear().substring(5));
            int curYear= LocalDate.now().getYear();

            if(secondYear> curYear){
                throw new ValidationException("Study year cannot be in the future");
            }

            if(studentRepo.getStudentById(con,j.getStudentID()).isEmpty()){
                throw new NotFoundException("Student  with id="+ j.getStudentID()+" not found");
            }

            if(subjectRepo.findById(con, j.getSubjectID()).isEmpty()){
                throw new NotFoundException("Subject  with id="+ j.getSubjectID()+" not found");
            }

            if(groupRepo.getGroupByID(con, j.getGroupID()).isEmpty()){
                throw new NotFoundException("Group  with id="+ j.getGroupID()+" not found");
            }
            return journalRepo.add(con, j);
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }

    }

    public Journal getById(Long id) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {
            Optional<Journal> j = journalRepo.getById(conn,id);
            if(j.isEmpty()){
                throw new NotFoundException("Journal  with id="+id+" not found");
            }
            return j.get();
        }catch (Exception e){
            throw new AppException("Error: " + e);
        }

    }

    public List<Journal> list() {
        try (Connection conn = DBUtil.getConnection()) {
            return journalRepo.list(conn);
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }

    }
}
