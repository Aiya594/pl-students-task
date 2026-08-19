package com.example.demo.repository.persistence;

import com.example.demo.model.Group;
import com.example.demo.repository.domain.GroupRepository;

import java.sql.Connection;

public class GroupJRepository implements GroupRepository {

    @Override
    public Group add(Connection connection, Group g) throws Exception {
        return null;
    }

    @Override
    public boolean archive(Connection connection, Long groupId) throws Exception {
        return false;
    }
}
