package registry

import command.Command
import command.HelpCommand

object CommandRegistry: Registry<String, Command> {
    override val registry: MutableMap<String, Command> = mutableMapOf()

    val HELP_COMMAND = HelpCommand()

    override fun registerAll() {
        registerCmd(HELP_COMMAND)
    }

    private fun registerCmd(command: Command) {
        register(command.name, command)
    }

}
