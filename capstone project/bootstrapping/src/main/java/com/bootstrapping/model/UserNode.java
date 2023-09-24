package com.bootstrapping.model;

import java.util.*;

public class UserNode {
    //contains the user username and the node that assigned to it
    private static Map<String , Node> userToNode =  new HashMap<>();
    private static int numOfNodes= Integer.parseInt(System.getenv("numOfNodes"));
    private static Deque<Node> availableNodes=new LinkedList<>();
    private static UserNode userNode = null;
    private UserNode(){}
    public static UserNode getInstance(){
        if(userNode==null){
            userNode= new UserNode();
            for (int i = 1; i <= numOfNodes; i++) {
                availableNodes.addLast(new Node("node"+i,"8080",i));
            }
        }
        return userNode;
    }

    public Node addUserToNode(User user){
        Node node=availableNodes.removeFirst();
        availableNodes.addLast(node);
        userToNode.put(user.getUsername(),node);
        return node;
    }

    public void removeUserFromNode(User user){
        Node node = userToNode.get(user.getUsername());
        availableNodes.removeFirstOccurrence(node);
        availableNodes.addFirst(node);
        userToNode.remove(user.getUsername());
    }

    public Map<String, Node> getUserToNode() {
        return userToNode;
    }
}
