/*
package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.*;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = new InMemoryFileSystem();

        System.out.println(new MkdirCommand(fs, "Users").execute());
        System.out.println(new CdCommand(fs, "Users").execute());
        System.out.println(new MkdirCommand(fs, "clifford").execute());
        System.out.println(new CdCommand(fs, "clifford").execute());
        System.out.println(new MkdirCommand(fs, "files").execute());
        System.out.println(new TouchCommand(fs, "ciclon.txt").execute());
        System.out.println(new TouchCommand(fs, "san-lorenzo.txt").execute());

        System.out.println("PWD: " + new PwdCommand(fs).execute());
        System.out.println("ls: " + new LsCommand(fs, "").execute());
        System.out.println("ls asc: " + new LsCommand(fs, "--ord=asc").execute());
        System.out.println("ls desc: " + new LsCommand(fs, "--ord=desc").execute());

        // SOLO ejecutamos rm una vez
        System.out.println("rm san-lorenzo.txt: " + new RmCommand(fs, "san-lorenzo.txt", false).execute());
        System.out.println("ls después del rm: " + new LsCommand(fs, "").execute());

        System.out.println("cd files: " + new CdCommand(fs, "files").execute());
        System.out.println("PWD: " + new PwdCommand(fs).execute());

        System.out.println("cd ..: " + new CdCommand(fs, "..").execute());

        // Probamos remover files
        System.out.println("rm files sin recursive: " + new RmCommand(fs, "files", false).execute());
        System.out.println("rm files con recursive: " + new RmCommand(fs, "files", true).execute());

        // Final
        System.out.println("ls final: " + new LsCommand(fs, "").execute());
    }
}
*/
