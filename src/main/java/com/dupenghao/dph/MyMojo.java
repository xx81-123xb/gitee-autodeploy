package com.dupenghao.dph;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.*;

/**
 * Goal which touches a timestamp file.
 *
 * @deprecated Don't use!
 */
@Mojo(name = "autodeploy", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo
        extends AbstractMojo {

    @Parameter(property = "autodeploy.repoPath")
    private String repoPath;

    @Parameter(property = "project.name")
    private String projectName;

    @Parameter(property = "project.version")
    private String version;

    public void execute()
            throws MojoExecutionException {
        String charset = System.getProperty("file.encoding");
        add(charset);
        commit(charset);
        push(charset);
    }

    //"git", "commit", "commitParam", "git", "push"
    private void add(String charset) {
        //1.获取命令行工具
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "git", "add", ".");
        //2.设置工作目录
        builder.directory(new File(repoPath));
        //3.设置标准输出和错误输出合并
        try {
            //4.启动进程
            Process process = builder.start();
            int exitCode = process.waitFor();

            // 处理命令行工具的输出
            if (exitCode == 0) {
                // 获取命令行工具的标准输出流
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行输出
                    System.out.println(line);
                }
            } else {
                // 获取命令行工具的错误输出流
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行错误输出
                    System.err.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void commit(String charset) {
        StringBuilder stringBuilder = new StringBuilder("version: ");
        stringBuilder.append(version).append("\t").append("project: ").append(projectName);
        String commitParam = stringBuilder.toString();
        //1.获取命令行工具
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "git", "commit", "-m" + commitParam);
        //2.设置工作目录
        builder.directory(new File(repoPath));
        //3.设置标准输出和错误输出合并
        try {
            //4.启动进程
            Process process = builder.start();
            int exitCode = process.waitFor();

            // 处理命令行工具的输出
            if (exitCode == 0) {
                // 获取命令行工具的标准输出流
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行输出
                    System.out.println(line);
                }
            } else {
                // 获取命令行工具的错误输出流
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行错误输出
                    System.err.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void push(String charset) {
        //1.获取命令行工具
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "git", "push", "origin", "master:dependency");
        //2.设置工作目录
        builder.directory(new File(repoPath));
        //3.设置标准输出和错误输出合并
        try {
            //4.启动进程
            Process process = builder.start();
            int exitCode = process.waitFor();

            // 处理命令行工具的输出
            if (exitCode == 0) {
                // 获取命令行工具的标准输出流
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行输出
                    System.out.println(line);
                }
            } else {
                // 获取命令行工具的错误输出流
                InputStream errorStream = process.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每行错误输出
                    System.err.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
