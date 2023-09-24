package com.bootstrapping.service;

import com.bootstrapping.model.User;
import java.io.IOException;

public interface IAuthService {
    User login(User user) throws IOException;
    String signup(User user);
}
