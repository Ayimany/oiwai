import command.parseMessage
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import registry.CommandRegistry

suspend fun main(args: Array<String>) {
    CommandRegistry.registerAll()

    val kord = Kord(args[0])
    val botUser = kord.getSelf()

    kord.on <MessageCreateEvent> {

        if (botUser.id !in message.mentionedUserIds) {
            return@on
        }

        parseMessage(message)

    }

    kord.login()
}
