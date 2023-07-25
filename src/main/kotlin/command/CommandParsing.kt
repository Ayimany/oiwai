package command

import dev.kord.core.entity.Message
import registry.CommandRegistry

suspend fun parseMessage(message: Message): CommandResult {
    val args = message.content
        .split(" ")
        .filter { it.isNotBlank() }
        .map    { it.trim() }
    
    if (args.size < 2) {
        return CommandRegistry.HELP.run(message, emptyList())
    }

    return getCommand(args[1]).run(message, args.subList(2, args.size))

}

fun getCommand(commandName: String): Command {
    return CommandRegistry.registry[commandName] ?:
           CommandRegistry.HELP
}
