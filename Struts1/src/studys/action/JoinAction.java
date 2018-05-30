package studys.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jdbc.DBconnect;
import studys.form.UserForm;

public class JoinAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		UserForm userForm = (UserForm) form;
		String cmd = req.getParameter("cmd");
		ActionMessages msgs = new ActionMessages();
		
		// idCheck
		if(cmd.equals("使用できるか確認")) {
			if(new DBconnect().isUser(userForm.getId())) {
				msgs.add("idCheck",new ActionMessage("errors.idCheck.false"));
				saveErrors(req, msgs);
			}else {
				msgs.add("idCheck",new ActionMessage("errors.idCheck.true"));
				saveErrors(req, msgs);
			}
			req.setAttribute("userForm", userForm);
			return mapping.findForward("idCheck");
			
		// joinCheck
		}else if(cmd.equals("登録します")) {
			if(userForm.getPass().equals(userForm.getPassCheck())) {
				req.setAttribute("userForm", userForm);
				return mapping.findForward("joinCheck");
			}else {
				msgs.add("passCheck",new ActionMessage("errors.passCheck.false"));
				saveErrors(req, msgs);
				return mapping.findForward("idCheck");
			}
			
		// join
		}else {
			
			String id = userForm.getId();
			String pass = userForm.getPass();
			String name = userForm.getName();
			String kana = userForm.getKana();
			String birth = userForm.getBirth();
			String club = userForm.getClub();
			
			int n = new DBconnect().insertUser(id, pass, name, kana);
			
			if(n>0) {
				Date date = Date.valueOf(birth);
				HttpSession session = req.getSession();
				
				new DBconnect().insertUserdetail(id, date, club);
			
				msgs.add("insert",new ActionMessage("msg.insert.true"));
				saveMessages(req, msgs);
				session.setAttribute("id", id);
				return mapping.findForward("search");
				
			}else {
				msgs.add("insert",new ActionMessage("errors.insert.false"));
				saveErrors(req, msgs);
				req.setAttribute("userForm", userForm);
				return mapping.findForward("idCheck");
				
			}
		}
	}

}
