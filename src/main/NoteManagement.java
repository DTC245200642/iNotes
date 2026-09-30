package com.codegym.model;

import java.util.List;

public class NoteManagement {
    private NoteInterface noteStore; // Lưu trữ chiến lược hiện tại (DB hoặc File)

    // Phương thức thay đổi chiến lược lưu trữ (theo yêu cầu Strategy Pattern)
    public void changeNoteStore(String storeType) {
        if (storeType.equalsIgnoreCase("db")) {
            this.noteStore = new NoteDB();
        } else if (storeType.equalsIgnoreCase("file")) {
            this.noteStore = new NoteFile();
        }
    }

    // Tìm kiếm ghi chú dựa trên chiến lược đang chọn
    public List<NoteInterface> searchNotes(String keyword) {
        if (noteStore != null) {
            return noteStore.search(keyword);
        }
        return null;
    }
}