package guess.controller;

import guess.model.NumberGuessBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("/six")
public class numGuessController {
	
	@RequestMapping("/")
	public String guess(HttpSession session, HttpServletRequest request) {
		NumberGuessBean numguess = null;
		if(session.getAttribute("numguess") == null) {
			numguess = new NumberGuessBean();
			session.setAttribute("numguess", numguess);
		} else {
			numguess = (NumberGuessBean) session.getAttribute("numguess");
		}
		
		String num = request.getParameter("guess");
		if (num == null) {
			numguess.reset();
			return "guess";
		} else {
			numguess.setGuess(num);
			session.setAttribute("numguess", numguess);
			if (numguess.getCorrect()) {
				return "success";
			} else {
				return "continue";
			}
		}
	}
	
}