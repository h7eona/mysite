package mysite.controller.action.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.ActionServlet.Action;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int gNo  = (request.getParameter("gNo")!=null)? Integer.parseInt(request.getParameter("gNo")):-1;
		int oNo  = (request.getParameter("oNo")!=null)? Integer.parseInt(request.getParameter("oNo")):-1;
		int depth  = (request.getParameter("depth")!=null)? Integer.parseInt(request.getParameter("depth")):-1;
		
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setHit(0);
		boardVo.setgNo(gNo); //답글이면 부모의 gNo, 새글이면 -1이 들어있음
		boardVo.setoNo(oNo);
		boardVo.setDepth(depth);
		
		
		new BoardDao().insert(boardVo, authUser);
		response.sendRedirect(request.getContextPath() +"/board");
	}

}