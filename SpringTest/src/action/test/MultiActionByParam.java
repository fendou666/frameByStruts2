package action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MultiActionByParam  extends MultiActionController{

	public ModelAndView ATest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView rec = new ModelAndView();
		rec.addObject("multiStr", "Param Atest");
		rec.setViewName("WEB-INF/success/MultiSuccess.jsp");
		return rec;
	}
	public ModelAndView BTest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView rec = new ModelAndView();
		rec.addObject("multiStr", "Param BTest");
		rec.setViewName("WEB-INF/success/MultiSuccess.jsp");
		return rec;
	}
	public ModelAndView CTest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView rec = new ModelAndView();
		rec.addObject("multiStr", "Param CTest");
		rec.setViewName("WEB-INF/success/MultiSuccess.jsp");
		return rec;
	}
	public ModelAndView DTest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView rec = new ModelAndView();
		rec.addObject("multiStr", "Param DTest");
		rec.setViewName("WEB-INF/success/MultiSuccess.jsp");
		return rec;
	}

	
}
