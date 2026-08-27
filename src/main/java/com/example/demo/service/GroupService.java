package com.example.demo.service;

import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.AppException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.GroupValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
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
                throw new AlreadyExistsException("Group with name "+ g.getName() + " and year "+ g.getYear().getValue()+ " already exists");
            }

            return groupRepository.add(conn, g);
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }

    public GroupStudents getGroupStudentsById( Long id) {
        try (Connection conn = DBUtil.getConnection()) {
            Optional<GroupStudents> gs = groupRepository.getGroupStdeuntsById(conn, id);
            if (gs.isEmpty()) {
                throw new NotFoundException("Group with id="+id+" not found");
            }
            return gs.get();
        }catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }
}
