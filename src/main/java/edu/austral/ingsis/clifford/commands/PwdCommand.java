package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;

public class PwdCommand implements Command {
  private FileSystem fs;

  public PwdCommand(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String execute() {
    Directory current = fs.getCurrent();
    StringBuilder path = new StringBuilder();

    while (current != null) {
      // Si es root (no tiene padre), solo agregamos una barra si el path está vacío
      if (current.getParent() == null) {
        if (path.length() == 0) path.insert(0, "/");
      } else {
        path.insert(0, "/" + current.getName());
      }
      current = current.getParent();
    }

    return path.toString();
  }
}
