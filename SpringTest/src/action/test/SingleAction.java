package action.test;

import java.awt.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.AbstractAction;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SingleAction extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView rec = new ModelAndView();
		rec.setViewName("WEB-INF/success/SingleSuccess.jsp");
		return rec;
	}
}
