package com.codegym.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteFile implements NoteInterface {
    private int id;
    private String title;
    private String content;
    private int typeId;
    private static final String FILE_PATH = "notes.txt";

    // Constructors
    public NoteFile() {}

    public NoteFile(int id, String title, String content, int typeId) {
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
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(id + "," + title + "," + content + "," + typeId + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        // Logic xóa ghi chú trong file (có thể triển khai đọc file, lọc bỏ dòng cần xóa rồi ghi đè lại)
    }

    @Override
    public List<NoteInterface> search(String keyword) {
        List<NoteInterface> list = new ArrayList<>();
        // Logic đọc file notes.txt dòng bằng dòng và tìm kiếm theo keyword
        return list;
    }
}