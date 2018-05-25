package studys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jdbc.DBconnect;
import studys.form.UserForm;

/**
 * @author choi.hyuncheol
 *　ユーザーログイン関連ロジック。
 */
public class LoginAction extends Action{
	
	private HttpSession session = null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		UserForm userForm = (UserForm) form;
		
		String id = userForm.getId();
		String pwd = userForm.getPass();
		
		boolean login = new DBconnect().isUser(id);
		
		session = req.getSession();
		
		if(login) {
			UserForm user = new DBconnect().findList(id);
			if(pwd.equals(user.getPass())) {
				session.setAttribute("id", id);
				return mapping.findForward("success");
			}else {
				req.setAttribute("result", "パスワードが間違っています。");
				req.setAttribute("cmd", "fail");
				return mapping.findForward("result");
			}			
		}else {
			req.setAttribute("result", "ユーザーIDが間違っています。");
			req.setAttribute("cmd", "fail");
			return mapping.findForward("result");
		}
	}
}
