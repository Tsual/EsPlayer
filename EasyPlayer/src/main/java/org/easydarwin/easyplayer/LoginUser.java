package org.easydarwin.easyplayer;


import java.util.Date;

public class LoginUser {
    private static LoginUser Current;

    public static LoginUser getCurrentUser() {
        if (Current == null)
            Current = new LoginUser();
        return Current;
    }

    public static void Reset() {
        Current = null;
    }

    private LoginUser() {

    }


    public boolean isLogin = false;
    public String LID = "";
    public int UID = -1;
    public String ORI_PWD = "";
    public String Name = "";
    public boolean hasFacedata = false;
    public String LoginToken = "";
    public Date LoginDate = null;
}
