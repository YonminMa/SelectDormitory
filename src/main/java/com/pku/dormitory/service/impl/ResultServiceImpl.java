package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.ResultRepository;
import com.pku.dormitory.domain.Result;
import com.pku.dormitory.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public void save(Result result) {
        resultRepository.save(result);
    }
}
