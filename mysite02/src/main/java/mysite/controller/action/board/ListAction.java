package mysite.controller.action.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.ActionServlet.Action;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> list = new BoardDao().findAll();
		request.setAttribute("list", list);
		
		int firstPage = (request.getParameter("firstPage")!=null)? Integer.parseInt(request.getParameter("firstPage")):1;
		int curPage = (request.getParameter("curPage")!=null)? Integer.parseInt(request.getParameter("curPage")):1;
		int lastPage = (request.getParameter("lastPage")!=null)? Integer.parseInt(request.getParameter("lastPage")):5;
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("lastPage", lastPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		rd.forward(request, response);
	}

}