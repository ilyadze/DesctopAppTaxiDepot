package com.example.laba_5_sem_2_javafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String login;
    private String password;
    private String blockStatement;
    private String roleStatement;

    public User() {}

    public User(String login, String password, String blockStatement, String roleStatement) {
        this.login = login;
        this.password = password;
        this.blockStatement = blockStatement;
        this.roleStatement = roleStatement;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getBlockStatement() {
        return blockStatement;
    }

    public void setBlockStatement(String blockStatement) {
        this.blockStatement = blockStatement;
    }

    public String getRoleStatement() {
        return roleStatement;
    }

    public void setRoleStatement(String roleStatement) {
        this.roleStatement = roleStatement;
    }

    public static User existenceCheck(String login, String password) {
        List<User> userList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("UsersData.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> users = (List<User>) (objectInputStream.readObject());
//            User user = (User) (objectInputStream.readObject());
            for(User user:users) {
                userList.add(user);
            }
//            userList.add(user);
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
        for (User user : userList) {
            String userLogin = user.login;
            if (user.login.equals(login) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", isBlocked=" + blockStatement +
                ", isAdmin=" + roleStatement +
                '}';
    }
}
