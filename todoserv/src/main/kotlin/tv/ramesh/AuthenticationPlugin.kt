package tv.ramesh

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.io.File

val AuthenticationPlugin = createApplicationPlugin("authentication") {
    // Read TokenFile

    val tokens = mutableListOf<String>();
    File("TokenFile").useLines {
        tokenLines -> tokenLines.forEach {
            if (it.isNotEmpty()) {
                tokens.add(it)
            }
        }
    }
    onCall { call->
        println(call.request.headers["Todo-Token"] == null)
        if (call.request.headers["Todo-Token"] == null) {
            call.respond(HttpStatusCode.BadRequest)
        }
        else if (!tokens.contains(call.request.headers["Todo-Token"])) {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }

    onCallReceive { call ->
        println("hallo")
        transformBody {
            data ->
               if (requestedType?.type == User::class)  {
                   // This will always be non-null at this point.
                   User(call.request.headers["Todo-Token"]!!)
               }
               else {
                    data
               }

        }

    }
    println("AuthenticationPlugin is installed")
}