package studys.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jdbc.DBconnect;
import studys.form.SearchForm;
import studys.form.UserForm;

public class SearchAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		SearchForm searchForm = (SearchForm) form;
		
		ActionMessages msgs = new ActionMessages();
			
		String id = searchForm.getId();
		String name = searchForm.getName();
		String kana = searchForm.getKana();
		
		if(id == "" && name == "" && kana == "") {
			msgs.add("searchCheck",new ActionMessage("errors.searchCheck"));
			saveErrors(req, msgs);
		}else {
			ArrayList<UserForm> list = new DBconnect().findList(id, name, kana);
			req.setAttribute("list", list);
		}
		
		return mapping.findForward("search");

	}
}
