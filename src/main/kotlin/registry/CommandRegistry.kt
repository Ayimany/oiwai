package registry

import command.Command
import command.HelpCommand
import command.InfoCommand
import command.JuxtransposeCommand

object CommandRegistry: Registry<String, Command> {
    override val registry: MutableMap<String, Command> = mutableMapOf()

    val HELP = HelpCommand()
    private val INFO = InfoCommand()
    private val JUXTRANSPOSE = JuxtransposeCommand()

    override fun registerAll() {
        registerCmd(HELP)
        registerCmd(INFO)
        registerCmd(JUXTRANSPOSE)
    }

    private fun registerCmd(command: Command) {
        register(command.name, command)
    }

}
