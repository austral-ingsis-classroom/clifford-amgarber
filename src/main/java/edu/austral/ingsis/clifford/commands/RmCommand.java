package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemElement;
import java.util.Optional;

public class RmCommand implements Command {
  private FileSystem fs;
  private String path;
  private boolean recursive;

  public RmCommand(FileSystem fs, String path, boolean recursive) {
    this.fs = fs;
    this.path = path;
    this.recursive = recursive;
  }

  @Override
  public String execute() {
    if (path == null || path.isEmpty()) return "Error: Invalid path";

    Directory current = fs.getCurrent();
    Optional<FileSystemElement> toRemove =
        current.getChildren().stream().filter(e -> e.getName().equals(path)).findFirst();

    if (toRemove.isEmpty()) {
      return "Error: No file or directory named '" + path + "'";
    }

    FileSystemElement element = toRemove.get();

    if (element.isDirectory() && !recursive) {
      return "cannot remove '" + path + "', is a directory";
    }

    current.removeChild(element);
    return "'" + path + "' removed";
  }
}
