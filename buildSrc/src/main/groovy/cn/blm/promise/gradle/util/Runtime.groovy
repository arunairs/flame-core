package cn.blm.promise.gradle.util

import org.apache.commons.io.IOUtils

import java.nio.charset.StandardCharsets

/**
 * @author jiaan.zhang@oracle.com
 * @date 12/27/15 1:34 AM
 */
enum OS {
    WINDOWS,
    LINUX,
    OSX,
}

final class Runtime {

    static OS getCurrentOS() {
        String os = System.getProperty("os.name").toUpperCase()
        if (os.contains("WINDOWS")) return OS.WINDOWS
        if (os.contains("LINUX")) return OS.LINUX
        if (os.contains("MAC")) return OS.OSX
    }

    static Process run(String[] commands, File directory, Map<String, String> environment, boolean wait = false) {
        ProcessBuilder processBuilder = new ProcessBuilder(commands)
        processBuilder.directory(directory)
        Map<String, String> environmentMap = processBuilder.environment()
        environmentMap.putAll(environment)
        Process process = processBuilder.start()
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
//        String s
//        while ((s = bufferedReader.readLine()) != null)
//            println s
        if (wait)
            process.waitFor()
        return process
    }

    static void kill(String processName) {
        Process p1 = java.lang.Runtime.getRuntime().exec(["ps"] as String[]);
        InputStream input = p1.getInputStream();
        Process p2 = java.lang.Runtime.getRuntime().exec(["grep", processName] as String[]);
        OutputStream output = p2.getOutputStream();
        IOUtils.copy(input, output);
        output.close();
        List<String> results = IOUtils.readLines(p2.getInputStream(), StandardCharsets.UTF_8);
        if (results != null) {
            String[] array
            String pid
            for (String line : results) {
                array = line.trim().split(" ")
                pid = array[0]
                java.lang.Runtime.getRuntime().exec("kill -9 ${pid}")
            }
        }
    }
}
