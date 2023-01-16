package com.example.laba_5_sem_2_javafx.entity.facade;



import com.example.laba_5_sem_2_javafx.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthenticationFacade {
//    public User Authentication() {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Login: ");
//        String login = in.next();
//        System.out.println("Password: ");
//        String password = in.next();
//
//        User user = User.existenceCheck(login, password);
//        if (user != null) {
//            if(!user.isBlocked()) {
//                return user;
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//
//    }

    public static boolean Registration(String login, String password) {


        List<User> userList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("UsersData.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> users = (List<User>) (objectInputStream.readObject());
            for(User user : users) {
                userList.add(user);
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        User user = User.existenceCheck(login, password);
        if(user == null) {
            try {
                userList.add(new User(login, password, "Not blocked", "Not admin"));
                FileOutputStream fileOutputStream = new FileOutputStream("UsersData.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(userList);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
