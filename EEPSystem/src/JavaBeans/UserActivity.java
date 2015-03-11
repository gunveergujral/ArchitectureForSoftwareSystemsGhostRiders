/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author songxiong
 */
public class UserActivity {
    private int userActivityID;
    private int userID;
    private String loginTime;
    private String logoutTime;

    /**
     * @return the userActivityID
     */
    public int getUserActivityID() {
        return userActivityID;
    }

    /**
     * @param userActivityID the userActivityID to set
     */
    public void setUserActivityID(int userActivityID) {
        this.userActivityID = userActivityID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the loginTime
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime the loginTime to set
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the logoutTime
     */
    public String getLogoutTime() {
        return logoutTime;
    }

    /**
     * @param logoutTime the logoutTime to set
     */
    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }
            
}
