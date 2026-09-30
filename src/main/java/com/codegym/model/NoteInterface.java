package com.codegym.model;

import java.util.List;

public interface NoteInterface {
    void save();
    void delete();
    List<NoteInterface> search(String keyword);
}