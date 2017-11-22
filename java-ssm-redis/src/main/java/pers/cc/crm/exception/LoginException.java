package pers.cc.crm.exception;

/**
 * 登陆自定义异常类
 * 
 * @author 郝晨成
 * @company china-soa
 * @createTime 2016年11月19日
 * @projectName SoaMdm
 * @className CustomException
 */
public class LoginException extends Exception {

	/**
	 * serialVersionUID:TODO
	 */

	private static final long serialVersionUID = 1L;
	// 异常信息
	public String message;

	/**
	 * @param message
	 */
	public LoginException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
