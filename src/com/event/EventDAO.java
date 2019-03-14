package com.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EventDAO {

	private Connection conn = null;

	public EventDAO(Connection conn){
		this.conn = conn;
	}

	// ADMIN ��ǰ�����͵��
	public void insertData(EventDTO dto) {

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into event (eventKey,eventName,originalName,saveFileName,";
			sql +="fileCategory,startDay,endDay) ";
			sql +="values (?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getEventKey());
			pstmt.setString(2, dto.getEventName());
			pstmt.setString(3, dto.getOriginalName());
			pstmt.setString(4, dto.getSaveFileName());
			pstmt.setString(5, dto.getFileCategory());

			pstmt.setString(6, dto.getStartDay());
			pstmt.setString(7, dto.getEndDay());

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//ADMIN ����Ʈ
	public List<EventDTO> getListAll(int start, int end) {

		List<EventDTO> lists = new ArrayList<EventDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select eventkey,eventName,saveFileName,filecategory,startday,endday ";
			sql+= "from event ";
			sql+= "order by eventKey )data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				EventDTO dto = new EventDTO();

				dto.setEventKey(rs.getInt("eventKey"));
				dto.setEventName(rs.getString("eventName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("fileCategory"));
				dto.setStartDay(rs.getString("startDay"));
				dto.setEndDay(rs.getString("endDay"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}
	
	//admin������ ��ǰ ����
	public int deleteData(String eventKey,String fileCategory){
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql= "delete product where eventKey=? and fileCategory=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventKey);
			pstmt.setString(2, fileCategory);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//admin������ ��ǰ ��ȸ_��������
	public EventDTO getUpdateData(String eventKey,String fileCategory) {

		EventDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select eventkey,eventName,originalName,saveFileName,filecategory,startday,endday ";
			sql += "from event where eventKey=? and fileCategory=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventKey);
			pstmt.setString(2, fileCategory);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new EventDTO();
				
				dto.setEventKey(rs.getInt("eventKey"));
				dto.setEventName(rs.getString("eventName"));
				dto.setSaveFileName(rs.getString("originalName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("fileCategory"));
				dto.setStartDay(rs.getString("startDay"));
				dto.setEndDay(rs.getString("endDay"));

			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	//�������� �̺�Ʈ �迭�� �ޱ�(����¡-O)
	public List<EventDTO> getLists(int start, int end) {

		List<EventDTO> lists = new ArrayList<EventDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select eventkey,eventName,saveFileName,filecategory ";
			sql+= "from event where filecategory='list' and endday>=sysdate ";
			sql+= "order by endday )data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				EventDTO dto = new EventDTO();

				dto.setEventKey(rs.getInt("eventkey"));
				dto.setEventName(rs.getString("eventName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("filecategory"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}

	//����� �̺�Ʈ �迭�� �ޱ�(����¡-O)
	public List<EventDTO> getLists_end(int start, int end) {

		List<EventDTO> lists = new ArrayList<EventDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select eventkey,eventName,saveFileName,filecategory ";
			sql+= "from event where filecategory='list' and endday<sysdate ";
			sql+= "order by endday )data)";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while(rs.next()){

				EventDTO dto = new EventDTO();

				dto.setEventKey(rs.getInt("eventkey"));
				dto.setEventName(rs.getString("eventName"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setFileCategory(rs.getString("filecategory"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}


	//��ü �������� ����(��������) 
	public int getDataCount() {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select nvl(count(*),0) from event where fileCategory='list' and endday>=sysdate";
			//searchKey�� ������ ���� �������, like ���� ?���� ���� �� �� ���� �ִ�.

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return totalDataCount;

	}
	
	//��ü �������� ����(�����) 
	public int getDataCount2() {

		int totalDataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select nvl(count(*),0) from event where fileCategory='detail' and endday<sysdate";
			//searchKey�� ������ ���� �������, like ���� ?���� ���� �� �� ���� �ִ�.

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return totalDataCount;

	}

	//�������� �̹��� �迭�� �޾ƿ���(����¡X)
	public List<EventDTO> getList(String eventKey){

		List<EventDTO> lists = new ArrayList<EventDTO>();

		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;

		try {

			sql="select eventName,saveFileName ";
			sql+="from event where eventKey=? and filecategory='detail'";

			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, eventKey);

			rs=pstmt.executeQuery();

			while(rs.next()){
				EventDTO dto=new EventDTO();

				dto.setEventName(rs.getString("eventName"));
				dto.setSaveFileName(rs.getString("saveFileName"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();


		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}

}
