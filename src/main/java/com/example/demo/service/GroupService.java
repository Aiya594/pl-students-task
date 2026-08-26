package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.GroupValidator;
import com.example.demo.exception.GroupAlreadyExists;
import com.example.demo.exception.GroupNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group addGroup( Group g) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            GroupValidator.validate(g);

            Optional<Group> group = groupRepository.getGroupByNameYear(conn,g.getName(),g.getYear().getValue());
            if (group.isPresent()) {
                throw new GroupAlreadyExists(g.getName(),g.getYear().getValue());
            }

            return groupRepository.add(conn, g);
        } catch (Exception e){
            return null;
        }
    }

    public GroupStudents getGroupStudentsById( Long id) throws Exception{
        try (Connection conn = DBUtil.getConnection()) {
            Optional<GroupStudents> gs = groupRepository.getGroupStdeuntsById(conn, id);
            if (gs.isEmpty()) {
                throw new GroupNotFound(id);
            }
            return gs.get();
        }catch (Exception e){
            return null;
        }
    }
}
