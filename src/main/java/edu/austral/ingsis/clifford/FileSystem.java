package edu.austral.ingsis.clifford;

public interface FileSystem {
  public Directory getRoot();

  public Directory getCurrent();

  public void setCurrent(Directory directory);
}
