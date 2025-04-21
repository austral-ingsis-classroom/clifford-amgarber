package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemElement {
  private String name;
  private Directory parent;
  private List<FileSystemElement> children;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public Directory getParent() {
    return parent;
  }

  public List<FileSystemElement> getChildren() {
    return children;
  }

  public void addChild(FileSystemElement child) {
    children.add(child);
  }

  public void removeChild(FileSystemElement child) {
    children.remove(child);
  }
}
