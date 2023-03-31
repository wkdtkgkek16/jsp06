package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostsDAO {
	//사용자정보
	public UserVO read(String uid) {
		UserVO vo=new UserVO();
		try {
			String sql="select * from users where uid=?";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setUid(rs.getString("uid"));
				vo.setUpass(rs.getString("upass"));
				vo.setUname(rs.getString("uname"));
			}
		} catch (Exception e) {
			System.out.println("게시글목록"+e.toString());
		}
		return vo;
	}
	
	//게시글 정보
	public PostVO read(int id){
		PostVO vo = new PostVO();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			String sql="select * from posts where id=?";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setDate(rs.getTimestamp("date"));
				vo.setWriter(rs.getString("writer"));
				vo.setFmtDate(sdf.format(rs.getTimestamp("date")));
				vo.setBody(rs.getString("body"));
			}
		} catch (Exception e) {
			System.out.println("게시글목록"+e.toString());
		}
		return vo;
	}
	
	
	//게시글 수
	public int total() {
		int total=0;
		
		try {
			String sql="select count(*) from posts";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("count(*)");

			}
		} catch (Exception e) {
			System.out.println("토탈"+e.toString());
		}
		return total;
	}
	
	//게시글 목록데이터 출력
	public ArrayList<PostVO> list(int page, int size){
		ArrayList<PostVO> array = new ArrayList<PostVO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			String sql="select * from posts order by id desc limit ?,?";
			PreparedStatement ps=DB.CON.prepareStatement(sql);
			ps.setInt(1, (page-1)*size);
			ps.setInt(2, size);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				PostVO vo=new PostVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setDate(rs.getTimestamp("date"));
				vo.setWriter(rs.getString("writer"));
				vo.setFmtDate(sdf.format(rs.getTimestamp("date")));
//				System.out.println(vo.toString());
				array.add(vo);
			}
		} catch (Exception e) {
			System.out.println("게시글목록"+e.toString());
		}
		return array;
	}
}
