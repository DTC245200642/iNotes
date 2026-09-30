package com.codegym.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDB implements NoteInterface {
    private int id;
    private String title;
    private String content;
    private int typeId;

    // Cấu hình thông tin kết nối MySQL (User và Password mặc định của XAMPP/MySQL thường là root và để trống)
    private static final String URL = "jdbc:mysql://localhost:3306/iNotes?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    // Constructors
    public NoteDB() {}

    public NoteDB(int id, String title, String content, int typeId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }

    @Override
    public void save() {
        String sql = "INSERT INTO notes (title, content, type_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.title);
            pstmt.setString(2, this.content);
            pstmt.setInt(3, this.typeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NoteInterface> search(String keyword) {
        List<NoteInterface> list = new ArrayList<>();
        String sql = "SELECT * FROM notes WHERE title LIKE ? OR content LIKE ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NoteDB note = new NoteDB(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("type_id")
                );
                list.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}