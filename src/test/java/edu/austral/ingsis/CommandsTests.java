package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.*;

import edu.austral.ingsis.clifford.*;
import edu.austral.ingsis.clifford.commands.*;
import org.junit.jupiter.api.Test;

public class CommandsTests {

  @Test
  void mkdirCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Command cmd = new MkdirCommand(fs, "docs");
    assertEquals("'docs' directory created", cmd.execute());

    Command duplicate = new MkdirCommand(fs, "docs");
    assertEquals("Error: A file or directory with that name already exists", duplicate.execute());

    assertEquals("Error: Directory name cannot be empty", new MkdirCommand(fs, "").execute());
    assertEquals(
        "Error: Directory name cannot contain / or spaces",
        new MkdirCommand(fs, "my dir").execute());
  }

  @Test
  void touchCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Command cmd = new TouchCommand(fs, "file.txt");
    assertEquals("'file.txt' file created", cmd.execute());

    Command duplicate = new TouchCommand(fs, "file.txt");
    assertEquals("Error: A file or directory with that name already exists", duplicate.execute());

    assertEquals("Error: File name cannot be empty", new TouchCommand(fs, "").execute());
    assertEquals(
        "Error: File name cannot contain '/' or spaces", new TouchCommand(fs, "my file").execute());
  }

  @Test
  void pwdCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Command mkdir = new MkdirCommand(fs, "dir");
    mkdir.execute();
    fs.setCurrent((Directory) fs.getCurrent().getChildren().get(0));
    Command pwd = new PwdCommand(fs);
    assertEquals("/dir", pwd.execute());
  }

  @Test
  void cdCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Directory root = fs.getRoot();
    Directory a = new Directory("a", root);
    Directory b = new Directory("b", a);
    root.addChild(a);
    a.addChild(b);

    Command cd1 = new CdCommand(fs, "a/b");
    assertEquals("moved to directory 'b'", cd1.execute());

    Command cd2 = new CdCommand(fs, "..");
    assertEquals("moved to directory 'a'", cd2.execute());

    Command cd3 = new CdCommand(fs, ".");
    assertEquals("Moved to directory: 'a'", cd3.execute());

    Command cdInvalid = new CdCommand(fs, "nonexistent");
    assertEquals("'nonexistent' directory does not exist", cdInvalid.execute());

    root.addChild(new File("file.txt", root));
    assertEquals("'file.txt' directory does not exist", new CdCommand(fs, "file.txt").execute());
  }

  @Test
  void lsCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Directory current = fs.getCurrent();
    current.addChild(new File("z.txt", current));
    current.addChild(new File("a.txt", current));

    Command lsDefault = new LsCommand(fs, "");
    assertEquals("z.txt a.txt", lsDefault.execute());

    Command lsAsc = new LsCommand(fs, "--ord=asc");
    assertEquals("a.txt z.txt", lsAsc.execute());

    Command lsDesc = new LsCommand(fs, "--ord=desc");
    assertEquals("z.txt a.txt", lsDesc.execute());

    Command lsInvalid = new LsCommand(fs, "--foo");
    assertEquals("Error: Invalid path for ls", lsInvalid.execute());
  }

  @Test
  void rmCommandTest() {
    FileSystem fs = new InMemoryFileSystem();
    Directory current = fs.getCurrent();

    File file = new File("file.txt", current);
    Directory folder = new Directory("folder", current);

    current.addChild(file);
    current.addChild(folder);

    assertEquals("'file.txt' removed", new RmCommand(fs, "file.txt", false).execute());
    assertEquals(
        "cannot remove 'folder', is a directory", new RmCommand(fs, "folder", false).execute());
    assertEquals("'folder' removed", new RmCommand(fs, "folder", true).execute());
    assertEquals(
        "Error: No file or directory named 'ghost'", new RmCommand(fs, "ghost", true).execute());
  }
}
