package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.PostsDAO;
import model.UserVO;

@WebServlet(value={"/posts","/posts/read","/posts.json","/posts/total","/login","/logout"})
public class PostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostsDAO dao = new PostsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		switch(request.getServletPath()) {
		case "/logout":
			session.invalidate();
			response.sendRedirect("/posts");
			break;
		case "/login":
			request.setAttribute("pageName", "/login.jsp");
			dis.forward(request, response);
			break;
		case "/posts/total":
			out.println(dao.total());
			break;
		case "/posts":
			request.setAttribute("pageName", "/posts/list.jsp");
			dis.forward(request, response);
			break;
		case "/posts/read":
			int id=Integer.parseInt(request.getParameter("id"));
			request.setAttribute("post", dao.read(id));
			request.setAttribute("pageName", "/posts/read.jsp");
			dis.forward(request, response);
			break;
		case "/posts.json":
			int page = Integer.parseInt(request.getParameter("page"));
			int size = Integer.parseInt(request.getParameter("size"));
			Gson gson = new Gson();
			out.println(gson.toJson(dao.list(page,size)));
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		switch(request.getServletPath()) {
		case "/login":
			String uid=request.getParameter("uid");
			String upass=request.getParameter("upass");
			
			UserVO vo=dao.read(uid);
			int result=0;
			if(vo.getUid() != null) {
				if(vo.getUpass().equals(upass)) {
					result=1;
					session.setAttribute("uid", vo.getUid());
					session.setAttribute("uname", vo.getUname());
				}else {
					result=2;
				}
			}
			out.println(result);
			break;
		}
		
	}

}
