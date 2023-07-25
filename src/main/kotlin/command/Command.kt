package command

import dev.kord.core.entity.Message
import dev.kord.core.entity.channel.MessageChannel

data class CommandResult(val status : Int)

abstract class Command(val name: String, val description: String, val quickHelp: String, val longHelp: String) {

    abstract suspend fun run(message: Message, args: List<String>): CommandResult

    suspend fun sendMessage(channel : MessageChannel, msg: String) {
        channel.createMessage(msg)
    }

}
