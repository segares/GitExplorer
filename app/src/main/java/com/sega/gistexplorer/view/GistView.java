package com.sega.gistexplorer.view;

import com.sega.gistexplorer.model.Gist;

import java.util.List;

public interface GistView {

    void refreshGistTable(List<Gist> gists);

    void error(String message);

}
