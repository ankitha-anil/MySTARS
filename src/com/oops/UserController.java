package com.oops;

import java.util.logging.FileHandler;

public class UserController extends User{

    User U;
    FileHandler fileHandler;

    private static int loginCheck(String userName, String passWord){
        if(passWord==password)
            return 1;
        else
            return 0;
    }
}
