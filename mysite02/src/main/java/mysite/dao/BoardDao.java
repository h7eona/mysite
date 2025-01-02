package mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mysite.vo.BoardVo;
import mysite.vo.UserVo;


public class BoardDao {

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select b.id, b.title, b.contents, b.hit, date_format(b.reg_date, '%Y-%m-%d %h:%i:%s'), b.g_no, b.o_no, b.depth, b.user_id, u.name "
						+ "	from board b, user u "
						+ " where b.user_id = u.id"
						+ " order by b.g_no desc, b.o_no asc");
				ResultSet rs = pstmt.executeQuery();
				
		) {
			while (rs.next()) {
				Long id = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int gNo = rs.getInt(6);
				int oNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userId = rs.getLong(9);
				String userName = rs.getString(10);
				
				BoardVo vo = new BoardVo();
				vo.setId(id);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserId(userId);
				vo.setUserName(userName);
				
				result.add(vo); 
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		return result;
	}
	
	public BoardVo findById(Long boardId) {
		BoardVo boardVo = null;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select  title, contents, hit, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), g_no, o_no, depth, user_id "
																+ " from board where id = ?");
			){
				// 4. Parameter Binding  
				pstmt.setLong(1, boardId); 
				
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String title = rs.getString(1);
					String contents = rs.getString(2);
					int hit = rs.getInt(3);
					String regDate = rs.getString(4);
					int gNo = rs.getInt(5);
					int oNo = rs.getInt(6);
					int depth = rs.getInt(7);
					Long userId = rs.getLong(8);
					
					boardVo = new BoardVo();
					boardVo.setId(boardId);
					boardVo.setTitle(title);
					boardVo.setContents(contents);
					boardVo.setHit(hit);
					boardVo.setRegDate(regDate);
					boardVo.setgNo(gNo);
					boardVo.setoNo(oNo);
					boardVo.setDepth(depth);
					boardVo.setUserId(userId);
				}
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			return boardVo;
	}
	
	public int getMaxgNo() {
		
		Optional<Integer> max_gNo;
		int ret = 0;
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select max(g_no) from board");
			){							
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					max_gNo = Optional.of(rs.getInt(1));
					if(max_gNo.isEmpty()) ret = 0; // null 방지 
					else ret = max_gNo.get();
				}
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
		
		return ret;
	}
	private void updateOrderNo(int gNo, int oNo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update board set o_no = o_no+1 where g_no=? and o_no>=?");
			){
				// 4. Parameter Binding  
				pstmt.setInt(1, gNo); 
				pstmt.setInt(2, oNo); 
				
				// 5. SQL 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
	}
	public void insert(BoardVo vo, UserVo authUser) {
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into board values(null, ?, ?, ?, now(), ?, ?, ?, ?);");
		){			
			// 4. Parameter Binding  
			pstmt.setString(1, vo.getTitle()); 
			pstmt.setString(2, vo.getContents()); 
			pstmt.setInt(3, vo.getHit()); 
			if(vo.getgNo()!=-1) { //답글
				pstmt.setInt(4, vo.getgNo());
				pstmt.setInt(5, vo.getoNo()+1);
				pstmt.setInt(6, vo.getDepth()+1);
				updateOrderNo( vo.getgNo(), vo.getoNo()+1);
			}
			else { //새글
				pstmt.setInt(4, getMaxgNo()+1);
				pstmt.setInt(5, 1);
				pstmt.setInt(6, 0);
			}			
			pstmt.setLong(7, authUser.getId());
			
			// 5. SQL 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	}

	public void deleteById(Long id) {
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from board where id = ?");
		){
			// 4. Parameter Binding  
			pstmt.setLong(1, id); 
			
			// 5. SQL 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
	}
	
	public void update(BoardVo vo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update board set title = ?, contents=? where id=?");
			){
				// 4. Parameter Binding  
				pstmt.setString(1, vo.getTitle()); 
				pstmt.setString(2, vo.getContents()); 
				pstmt.setLong(3, vo.getId()); 
				
				// 5. SQL 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 			
	}

	public void increaseHit(BoardVo vo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update board set hit = hit+1 where id=?");
			){
				// 4. Parameter Binding  
				pstmt.setLong(1, vo.getId()); 
				
				// 5. SQL 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 	
	}
	
	
	
	

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
	
			// 2. 연결하기 
			String url = "jdbc:mariadb://192.168.0.11:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}







}