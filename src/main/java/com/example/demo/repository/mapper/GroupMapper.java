package com.example.demo.repository.mapper;

import com.example.demo.entity.GroupEntity;
import com.example.demo.model.Group;

import java.time.Year;


public class GroupMapper {
    public Group toDomain(GroupEntity entity) {
        return Group
                .builder()
                .groupId(entity.getGroupID())
                .name(entity.getName())
                .year(Year.of(entity.getYear()))
                .build();

    }

    public GroupEntity toEntity(Group model) {
        return GroupEntity
                .builder()
                .groupID(model.getGroupId())
                .name(model.getName())
                .year(model.getYear().getValue())
                .build();
    }
}
