package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.GroupRepository;
import com.pku.dormitory.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public int getCountById(int id) {
        return groupRepository.getCountById(id);
    }
}
