package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CommentVO;
import model.CommentsDAO;

@WebServlet(value={"/comments.json","/comments/total","/comments/insert","/comments/delete"})
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentsDAO dao = new CommentsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		switch(request.getServletPath()) {
		case "/comments/total":
			int postid=Integer.parseInt(request.getParameter("postid"));
			out.println(dao.total(postid));
			break;
		case "/comments.json":
			postid=Integer.parseInt(request.getParameter("postid"));
			int page=Integer.parseInt(request.getParameter("page"));
			int size=Integer.parseInt(request.getParameter("size"));
			
			Gson gson = new Gson();
			out.println(gson.toJson(dao.list(postid, page, size)));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		switch(request.getServletPath()) {
		case "/comments/insert":
			CommentVO vo = new CommentVO();
			vo.setPostid(Integer.parseInt(request.getParameter("postid")));
			vo.setBody(request.getParameter("body"));
			vo.setWriter(request.getParameter("writer"));
			dao.insert(vo);
			break;
		case "/comments/delete":
			int id=Integer.parseInt(request.getParameter("id"));
			dao.delete(id);
			break;
		}
	}

}
