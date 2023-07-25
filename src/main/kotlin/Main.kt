import command.parseMessage
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import registry.CommandRegistry

const val TOKEN: String = "MTEzMzE2MzMyODM1MzYxOTk3OQ.G7rOWd.87GVa0r-gjltMCB9ysaHc-sd2u0eUaYegQyDeQ"

suspend fun main(args: Array<String>) {
    CommandRegistry.registerAll()

    val kord = Kord(TOKEN)
    val botUser = kord.getSelf()

    kord.on <MessageCreateEvent> {

        if (botUser.id !in message.mentionedUserIds) {
            return@on
        }

        parseMessage(message)

    }

    kord.login()
}
