package i.met.you.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PasswordChangeBean extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = -540065699389843545L;

	private String oldPassword = "";

	private String newPassword = "";

	private String newPasswordConfirm = "";

	private boolean reminderPasswd = false;

	private BigDecimal usrId = null;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public boolean isReminderPasswd() {
		return reminderPasswd;
	}

	public void setReminderPasswd(boolean reminderPasswd) {
		this.reminderPasswd = reminderPasswd;
	}

	public BigDecimal getUsrId() {
		return usrId;
	}

	public void setUsrId(BigDecimal usrId) {
		this.usrId = usrId;
	}

}
