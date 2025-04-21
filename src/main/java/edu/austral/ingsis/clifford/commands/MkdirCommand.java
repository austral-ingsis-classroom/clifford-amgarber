package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemElement;

public class MkdirCommand implements Command {
  private FileSystem fs;
  private String dirName;

  public MkdirCommand(FileSystem fs, String dirName) {
    this.fs = fs;
    this.dirName = dirName;
  }

  @Override
  public String execute() {
    if (dirName == null || dirName.isEmpty()) {
      return "Error: Directory name cannot be empty";
    }
    if (dirName.contains("/") || dirName.contains(" ")) {
      return "Error: Directory name cannot contain / or spaces";
    }
    Directory current = fs.getCurrent();
    // no puede haber dos directorios con el mismo nombre
    for (FileSystemElement child : current.getChildren()) {
      if (child.getName().equals(dirName)) {
        return "Error: A file or directory with that name already exists";
      }
    }
    Directory newDir = new Directory(dirName, current);
    current.addChild(newDir);

    return "'" + dirName + "' directory created";
  }
}
