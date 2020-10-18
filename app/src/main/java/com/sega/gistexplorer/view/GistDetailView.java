package com.sega.gistexplorer.view;

public interface GistDetailView {
    void refreshFileContent(String filePath);
    void error(String message);
}
