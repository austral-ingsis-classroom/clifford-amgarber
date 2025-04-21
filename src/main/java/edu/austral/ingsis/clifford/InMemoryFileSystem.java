package edu.austral.ingsis.clifford;

public class InMemoryFileSystem implements FileSystem {
  private final Directory root;
  private Directory current;

  public InMemoryFileSystem() {
    this.root = new Directory("/", null);
    this.current = root;
  }

  @Override
  public Directory getRoot() {
    return root;
  }

  @Override
  public Directory getCurrent() {
    return current;
  }

  @Override
  public void setCurrent(Directory directory) {
    this.current = directory;
  }
}
