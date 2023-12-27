package tv.ramesh

import java.nio.file.Paths
import java.nio.file.NoSuchFileException
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

class User(private val token: String) {
    fun writeTodoFile(fileName: String, data: String) {
        val tf = TodoFile(token, fileName)
        tf.writeFile(data)
    }

    fun readTodoFile(fileName: String): String {
        return TodoFile(token, fileName).readFile()
    }

    fun getTodoFiles(): List<String> {
        return try {
            Path(token).listDirectoryEntries().map{ p -> p.fileName.toString() }
        }
        catch (e: NoSuchFileException) {
            emptyList()
        }
    }
}