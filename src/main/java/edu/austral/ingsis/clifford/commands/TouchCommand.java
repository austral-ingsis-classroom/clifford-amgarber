package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystem;

public class TouchCommand implements Command {
  private FileSystem fs;
  private String fileName;

  public TouchCommand(FileSystem fs, String fileName) {
    this.fs = fs;
    this.fileName = fileName;
  }

  @Override
  public String execute() {
    if (fileName == null || fileName.isEmpty()) {
      return "Error: File name cannot be empty";
    }
    if (fileName.contains("/") || fileName.contains(" ")) {
      return "Error: File name cannot contain '/' or spaces";
    }
    Directory currentDir = fs.getCurrent();

    boolean exists =
        currentDir.getChildren().stream().anyMatch(child -> child.getName().equals(fileName));
    if (exists) {
      return "Error: A file or directory with that name already exists";
    }
    File newFile = File.create(fileName, currentDir);
    currentDir.addChild(newFile);
    return "'" + fileName + "' file created";
  }
}
