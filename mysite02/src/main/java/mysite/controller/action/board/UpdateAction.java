package mysite.controller.action.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.ActionServlet.Action;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo vo = new BoardDao().findById(id);
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardDao().update(vo);
		
		request.setAttribute("vo", vo);
		response.sendRedirect(request.getContextPath()+"/board?a=view&id="+vo.getId());
	}

}