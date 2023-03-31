package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentsDAO {
	
	public void delete(int id) {
			
			try {
				String sql="delete from comments where id=?";
				PreparedStatement ps=DB.CON.prepareStatement(sql);
				ps.setInt(1, id);
				ps.execute();
			} catch (Exception e) {
				System.out.println("��ۻ���"+e.toString());
			}
		}
	
	//��۵��
	public void insert(CommentVO vo) {
		
		try {
			String sql="insert into comments(postsid, body, writer) values(?,?,?)";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setInt(1, vo.getPostid());
			ps.setString(2, vo.getBody());
			ps.setString(3, vo.getWriter());
			ps.execute();
		} catch (Exception e) {
			System.out.println("��۵��"+e.toString());
		}
	}
	
	//��ۼ�
	public int total(int postid) {
		int total=0;
		try {
			String sql="select count(*) cnt from comments where postsid=?";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setInt(1, postid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				total=rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("��ۼ�"+e.toString());
		}
		return total;
	}

	//��۸��
	public ArrayList<CommentVO> list(int postid, int page, int size) {
		 ArrayList<CommentVO> array = new  ArrayList<CommentVO>();
		 try {
			String sql="select * from comments where postsid=? order by id desc limit ?,?";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setInt(1, postid);
			ps.setInt(2, (page-1)*size);
			ps.setInt(3, size);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CommentVO vo=new CommentVO();
				vo.setId(rs.getInt("id"));
				vo.setBody(rs.getString("body"));
				vo.setDate(rs.getTimestamp("date"));
				vo.setWriter(rs.getString("writer"));
				array.add(vo);
			}
		} catch (Exception e) {
			System.out.println("��۸��"+e.toString());
		}
		 return array;
	}
}
