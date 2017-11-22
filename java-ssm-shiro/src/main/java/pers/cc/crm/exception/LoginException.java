package pers.cc.crm.exception;


/**
 * 系统自定义异常类
 * @author 郝晨成
 * @company china-soa
 * @createTime 2016年11月19日
 * @projectName SoaMdm
 * @className CustomException
 */
public class LoginException extends Exception{

    //异常信息
    public String message;

    /**
     * @param message
     */
    public LoginException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
