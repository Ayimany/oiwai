package command

import dev.kord.core.entity.Message
import registry.CommandRegistry

class HelpCommand: Command(
    "help",
    "Get help on a command or the bot in general",
    "@Oiwai help { Nothing | <CommandName> }",
    """
        Command | Help
        Retrieves information on a command, its arguments and its outputs
        
        Usage:
        **@Oiwai help**
        Provides general bot information as well as it's commands
        
        **@Oiwai help _CommandName_**
        Provides descriptive information on a command named _CommandName_
    """.trimIndent()
) {

    override suspend fun run(message: Message, args: List<String>): CommandResult {
        val builder = StringBuilder()

        if (args.size < 3) {
            builder.append("お祝い | A bot for lazy people\n\n")

            for (pair in CommandRegistry.registry) {
                builder.append("**${pair.key}**: ${pair.value.description}\n${pair.value.quickHelp}\n")
            }

            sendMessage(message.getChannel(), builder.toString())
        } else {
            val command = CommandRegistry.getOrNull(args[2])

            if (command == null) {
                sendMessage(message.getChannel(), "Unknown command ${args[2]}")
                return CommandResult(status = 1)
            }

            sendMessage(message.getChannel(), command.longHelp)

        }

        return CommandResult(status = 0)
    }

}