package command

import dev.kord.core.entity.Message
import registry.CommandRegistry

class HelpCommand: Command(
    "help",
    "Get help on a command or the bot in general",
    "@Oiwai help { Nothing | <CommandName> }",
    """
        **Help Command**
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

        if (args.isEmpty()) {
            builder.append("**お祝い: A bot for lazy people**\n\n")

            for (pair in CommandRegistry.registry) {
                builder.append("**${pair.key}**: ${pair.value.description}\nUsage: ${pair.value.quickHelp}\n\n")
            }

            sendMessage(message.getChannel(), builder.toString())

        } else {
            val command = CommandRegistry.getOrNull(args[0])
                ?: return CommandResult(status = 1, "Command ${args[0]} does not exist")

            sendMessage(message.getChannel(), command.longHelp)

        }

        return CommandResult(status = 0)
    }

}

class InfoCommand: Command(
    "info",
    "Provides bot info",
    "@Oiwai info",
    """
        **Info Command**
        Retrieves the bot's:
        - Version
        - Kotlin Version
        - Repo URL
        
        Usage:
        **@Oiwai info**
        Retrieves information
    """.trimIndent()
) {

    override suspend fun run(message: Message, args: List<String>): CommandResult {
        // TODO: Use variables & templates
        sendMessage(message.getChannel(), """
            **Oiwai, because I was too lazy to make a table with names on each axis**
            
            **Version:** 0.0.1
            **Kotlin:** 231-1.9.0
            
            **Repo:** https://github.com/Ayimany/oiwai
            
        """.trimIndent())

        return CommandResult(status = 0)
    }

}
