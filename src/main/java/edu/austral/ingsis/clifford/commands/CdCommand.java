package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemElement;
import java.util.Optional;

public class CdCommand implements Command {
  private FileSystem fs;
  private String path;

  public CdCommand(FileSystem fs, String path) {
    this.fs = fs;
    this.path = path;
  }

  @Override
  public String execute() {
    if (path == null || path.isEmpty()) {
      return "Error: Invalid path"; // el path no puede ser nulo o vacio
    }
    // tengo que saber si empiezo desde el root o desde current
    Directory target = path.startsWith("/") ? fs.getRoot() : fs.getCurrent();
    if (path.equals(".")) {
      return "Moved to directory: '" + target.getName() + "'";
    }
    // divido el path con /
    String[] parts = path.split("/");
    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) {
        continue;
      }
      if (part.equals("..")) { // para ir al directorio padre
        if (target.getParent() != null) {
          target = target.getParent();
        }
        continue;
      }
      Optional<FileSystemElement> found =
          target.getChildren().stream().filter(e -> e.getName().equals(part)).findFirst();
      if (found.isEmpty()) {
        return "'" + part + "' directory does not exist";
      }
      if (!found.get().isDirectory()) {
        return "Error: '" + part + "' is not a directory";
      }
      target = (Directory) found.get();
    }
    fs.setCurrent(target);
    return "moved to directory '" + target.getName() + "'";
  }
}
