package com.bootstrapping.service;

import com.bootstrapping.model.User;

public interface INodeService {
    String assignUserToNode(User user);
    String clearNode(User user);
}
