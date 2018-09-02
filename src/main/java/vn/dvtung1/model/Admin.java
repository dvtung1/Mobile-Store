package vn.dvtung1.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class Admin extends ActionForm {
	private String userName;
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors actionErrors = new ActionErrors();

		//Enter your admin username and password here
		if (!userName.equals("") || !passWord.equals("")) {
			actionErrors.add("admin.login.required", new ActionMessage("error.admin.login.required"));
		}

		return actionErrors;
	}

}
