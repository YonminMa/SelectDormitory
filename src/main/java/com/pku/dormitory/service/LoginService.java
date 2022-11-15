package com.pku.dormitory.service;

import com.pku.dormitory.domain.Login;
import com.pku.dormitory.utils.Result;

public interface LoginService {

    Result login(Login login);
}
