package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DatabaseContext;
import entity.Category;
import entity.Movie;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Movie> getAllMovies() {
		List<Movie> list = new ArrayList<>();
		String query = "SELECT VideoID, VideoTitle, category.CategoryName "
				+ "FROM `video` INNER JOIN `category` "
				+ "ON video.VideoCategoryID = category.CategoryID "
				+ "ORDER BY RAND() LIMIT 12";
		
		try {
			conn = DatabaseContext.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Movie(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)));
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
		return list;
	}
	public List<Movie> getMovieByKeyword(String keyword) {
		List<Movie> list = new ArrayList<>();
		String query = "SELECT VideoID, VideoTitle, category.CategoryName "
				+ "FROM `video` INNER JOIN `category` "
				+ "ON video.VideoCategoryID = category.CategoryID "
				+ "WHERE VideoTitle LIKE '%"+keyword+"%'";
		
		try {
			conn = DatabaseContext.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Movie(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)));
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
		return list;
	}
	public List<Movie> getRandomMovieList(String videoId) {
		List<Movie> list = new ArrayList<>();
		String query = "SELECT VideoID, VideoTitle, category.CategoryName "
				+ "FROM `video` INNER JOIN `category` "
				+ "ON video.VideoCategoryID = category.CategoryID "
				+ "WHERE NOT(VideoID = '"+videoId+"') ORDER BY RAND() LIMIT 6";
		
		try {
			conn = DatabaseContext.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Movie(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)));
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
		return list;
	}
	
	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		String query = "SELECT * FROM category";
		
		try {
			conn = DatabaseContext.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Category(
						rs.getString(1),
						rs.getString(2)));
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
		return list;
	}
	public List<Movie> getMovieByCategory(String categoryId) {
		List<Movie> list = new ArrayList<>();
		String query = "SELECT VideoID, VideoTitle, category.CategoryName "
				+ "FROM `video` INNER JOIN `category` "
				+ "ON video.VideoCategoryID = category.CategoryID "
				+ "WHERE VideoCategoryID = '" + categoryId + "'";
		
		try {
			conn = DatabaseContext.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Movie(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)));
			}
			conn.close();
		}
		catch(Exception e) {
			
		}
		return list;
	}
}
