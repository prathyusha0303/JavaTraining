package com.OnlineBiddingSystem.demo;

import java.util.ArrayList;
import java.util.List;

import com.OnlineBiddingSystem.demo.entity.Item;
import com.OnlineBiddingSystem.demo.entity.User;

public class NotificationService {

    private List<User> users;

    public NotificationService() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void notifyOutbid(User outbidUser, User winningUser, Item item) {
        String message = "You have been outbid on item '" + item.getName() + "'. The current winning bid is $" + item.getCurrentHighestBid()
                + " placed by user '" + winningUser.getUserName() + "'.";
        sendMessage(outbidUser, message);
    }

    private void sendMessage(User user, String message) {
        // Send the message to the user (e.g., via email, push notification, etc.)
        System.out.println("Sending notification to user '" + user.getUserName() + "': " + message);
    }
}
