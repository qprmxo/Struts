package studys.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jdbc.DBconnect;
import studys.form.UserForm;

public class UpdateAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		UserForm userForm = (UserForm) form;
		String cmd = req.getParameter("cmd");
		
		if(cmd.equals("確認")) {
			req.setAttribute("userForm", userForm);
			
			return mapping.findForward("updateCheck");
			
		}else {
			
			String id = userForm.getId();
			String name = userForm.getName();
			String kana = userForm.getKana();
			String birth = userForm.getBirth();
			String club = userForm.getClub();
			ActionMessages msgs = new ActionMessages();
			
			int n = new DBconnect().updateUser(name, kana, id);
			
			if(n>0) {
				Date date = Date.valueOf(birth);
				new DBconnect().updateUserdetail(date, club, id);
				
				msgs.add("update", new ActionMessage("msg.update.true"));
				saveMessages(req, msgs);
				
			}else {
				msgs.add("update", new ActionMessage("errors.update.false"));
				saveErrors(req, msgs);
			}
			
			return mapping.findForward("update");
		}
	}
}
