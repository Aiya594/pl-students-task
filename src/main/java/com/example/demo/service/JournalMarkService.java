package com.example.demo.service;

import com.example.demo.model.JournalMark;
import com.example.demo.repository.domain.JournalMarkRepository;
import com.example.demo.repository.domain.JournalRepository;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.JournalMarkValidator;
import com.example.demo.exception.JournalNotFound;
import com.example.demo.exception.StudentsNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalMarkService {

    private final JournalMarkRepository markRepo;
    private final StudentRepository studentRepo;
    private final JournalRepository journalRepo;

    public JournalMark add( JournalMark jm) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {
            JournalMarkValidator.validate(jm);
            if(studentRepo.getStudentById(conn,jm.getStudentID()).isEmpty()){
                throw new StudentsNotFound(jm.getStudentID());
            }

            if(journalRepo.getById(conn,jm.getJournalID()).isEmpty()){
                throw new JournalNotFound(jm.getJournalID());
            }


            return markRepo.add(conn,jm);
        }catch (Exception e){
            return null;
        }

    }

    public JournalMark updateMark( Long jmId, int mark) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {


            return markRepo.updateMark(conn,jmId,mark).get();
        }catch (Exception e){
            return null;
        }

    }

    public List<JournalMark> getMarks(Long journalId) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {


            return markRepo.getMarks(conn,journalId);
        }catch (Exception e){
            return null;
        }

    }


    boolean delete(Long id) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {

            return  markRepo.delete(conn,id);
        }catch (Exception e){
            return false;
        }
    }



}
