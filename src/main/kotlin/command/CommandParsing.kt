package command

import dev.kord.core.entity.Message
import registry.CommandRegistry

suspend fun parseMessage(message: Message) {
    val args = message.content
        .split(" ")
        .filter { it.isNotBlank() }
        .map    { it.trim() }
    
    if (args.size < 2) {
        CommandRegistry.HELP_COMMAND.run(message, emptyList())
    }

    getCommand(args[1]).run(message, args)

}

fun getCommand(commandName: String): Command {
    return CommandRegistry.registry[commandName] ?:
           CommandRegistry.HELP_COMMAND
}
