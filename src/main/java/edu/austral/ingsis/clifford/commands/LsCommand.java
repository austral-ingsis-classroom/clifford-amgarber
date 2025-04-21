package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemElement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LsCommand implements Command {
  private FileSystem fs;
  private String path;

  public LsCommand(FileSystem fs, String path) {
    this.fs = fs;
    this.path = path;
  }

  @Override
  public String execute() {
    List<FileSystemElement> children = fs.getCurrent().getChildren();
    List<FileSystemElement> output;

    if (path == null || path.isEmpty()) {
      // No ordering, preserve insertion order
      output = children;

    } else if (path.equals("--ord=asc")) {
      output = children.stream().sorted(Comparator.comparing(FileSystemElement::getName)).toList();
    } else if (path.equals("--ord=desc")) {
      output =
          children.stream()
              .sorted(Comparator.comparing(FileSystemElement::getName).reversed())
              .toList();
    } else {
      return "Error: Invalid path for ls";
    }

    return output.stream().map(FileSystemElement::getName).collect(Collectors.joining(" "));
  }
}
