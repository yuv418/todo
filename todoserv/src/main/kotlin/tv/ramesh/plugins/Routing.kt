package tv.ramesh.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import tv.ramesh.User

val ok = mapOf("status" to "ok");

fun Application.configureRouting() {
    // all: validate token based on tokenfile
    routing {
        // send json list of all todos
        get("/todos") {
            val user = call.receive<User>();
            println("user is $user")

            call.respond(user.getTodoFiles())
        }
        // parameters:
        // todofile: (string) file.txt (validate that it is a txt file)
        // tododata: (string) data
        post("/todo") {
            val user = call.receive<User>();

            val params = call.receiveParameters()
            val todoFile = params["todoFile"].toString()
            val todoData = params["todoData"].toString()

            user.writeTodoFile(todoFile, todoData);

            call.respond(ok)
        }
        // parameters:
        // todofile: file.txt (string)
        get("/todo") {
            val user = call.receive<User>();

            val params = call.receiveParameters()
            val todoFile = params["todoFile"].toString()

            call.respond(mapOf("status" to "ok", "content" to user.readTodoFile(todoFile)))
        }
    }
}
