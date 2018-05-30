package studys.action;

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

public class MoveAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		String cmd = req.getParameter("cmd");
		String id = req.getParameter("id");
		
		if(cmd.equals("更新")) {
			UserForm userForm = new DBconnect().findList(id);
			req.setAttribute("userForm", userForm);
			return mapping.findForward("updateWrite");
			
		}else if(cmd.equals("削除")) {
			UserForm userForm = new DBconnect().findList(id);
			req.setAttribute("userForm", userForm);
			return mapping.findForward("deleteCheck");
			
		}else {
			UserForm userForm = (UserForm) form;
			
			ActionMessages msgs = new ActionMessages();
			
			int n = new DBconnect().deleteUserdetail(userForm.getId());
			if(n>0) {
				new DBconnect().deleteUser(userForm.getId());
				msgs.add("delete", new ActionMessage("msg.delete.true"));
				saveMessages(req, msgs);
			}else {
				msgs.add("delete", new ActionMessage("errors.delete.false"));
				saveErrors(req, msgs);
			}
			return mapping.findForward("delete");
		}
	}
}
