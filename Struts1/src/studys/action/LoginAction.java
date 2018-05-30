package studys.action;

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
import studys.form.LoginForm;
import studys.form.UserForm;

/**
 * @author choi.hyuncheol
 *　ユーザーログイン関連ロジック。
 */
public class LoginAction extends Action{
	
	private HttpSession session = null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		LoginForm loginForm = (LoginForm) form;
		
		String id = loginForm.getId();
		String pwd = loginForm.getPass();
		String cmd = req.getParameter("cmd");
		
		session = req.getSession();
		
		if(cmd.equals("ログイン")) {
		
			boolean login = new DBconnect().isUser(id);
			
			ActionMessages msgs = new ActionMessages();
			
			if(login) {
				UserForm user = new DBconnect().findList(id);
				if(pwd.equals(user.getPass())) {
					session.setAttribute("id", id);
					return mapping.findForward("success");
				}else {
					msgs.add("pass", new ActionMessage("errors.boolean","パスワード"));
					saveErrors(req, msgs);
					return mapping.findForward("fail");
				}			
			}else {
				msgs.add("id", new ActionMessage("errors.boolean","ユーザーID"));
				saveErrors(req, msgs);
				return mapping.findForward("fail");
			}
			
		}else {
			session.invalidate();		
			
			return mapping.findForward("fail");
		}
	}
}
