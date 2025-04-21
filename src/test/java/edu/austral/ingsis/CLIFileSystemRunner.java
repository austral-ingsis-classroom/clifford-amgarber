package edu.austral.ingsis;

import edu.austral.ingsis.clifford.*;
import edu.austral.ingsis.clifford.commands.*;
import java.util.ArrayList;
import java.util.List;

public class CLIFileSystemRunner implements FileSystemRunner {

  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystem fs = new InMemoryFileSystem();
    List<String> results = new ArrayList<>();

    for (String input : commands) {
      String[] parts = input.split(" ");
      String cmd = parts[0];
      String[] args = new String[parts.length - 1];
      System.arraycopy(parts, 1, args, 0, args.length);

      String result =
          switch (cmd) {
            case "mkdir" ->
                args.length == 1
                    ? new MkdirCommand(fs, args[0]).execute()
                    : "Error: invalid mkdir usage";

            case "touch" ->
                args.length == 1
                    ? new TouchCommand(fs, args[0]).execute()
                    : "Error: invalid touch usage";

            case "cd" ->
                args.length == 1 ? new CdCommand(fs, args[0]).execute() : "Error: invalid cd usage";

            case "ls" ->
                args.length == 0
                    ? new LsCommand(fs, "").execute()
                    : new LsCommand(fs, args[0]).execute();

            case "pwd" -> new PwdCommand(fs).execute();

            case "rm" -> {
              if (args.length == 1 && !args[0].equals("--recursive")) {
                yield new RmCommand(fs, args[0], false).execute();
              } else if (args.length == 2 && args[0].equals("--recursive")) {
                yield new RmCommand(fs, args[1], true).execute();
              } else {
                yield "Error: invalid rm usage";
              }
            }

            default -> "Error: unknown command";
          };

      results.add(result);
    }

    return results;
  }
}
