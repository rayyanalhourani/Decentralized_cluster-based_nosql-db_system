package com.bootstrapping.service;

import com.bootstrapping.model.User;

public interface ITokenService {
    String createToken(User user);
}
