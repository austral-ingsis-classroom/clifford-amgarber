package edu.austral.ingsis.clifford;

public class File implements FileSystemElement {
  private String name;
  private Directory parent;

  public File(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {

    return name;
  }

  @Override
  public boolean isDirectory() {

    return false;
  }

  public Directory getParent() {

    return parent;
  }

  public static File create(String name, Directory parent) {
    return new File(name, parent);
  }
}
