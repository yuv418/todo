package tv.ramesh

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists

class TodoFile(private val token: String, private val fileName: String) {
    private val absPath: String = "$token/$fileName";
    private val todoF = File(absPath);

    init {
        val tokenDir = Paths.get(token)
        if (!tokenDir.exists()) {
            Files.createDirectory(tokenDir)
        }
    }

    fun writeFile(data: String) {
        File(absPath).writeText(data)
    }
    fun readFile(): String {
        return File(absPath).readText()
    }
}