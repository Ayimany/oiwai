package command

import dev.kord.core.entity.Message

class JuxtransposeCommand: Command(
    "juxtranspose",
    "Spans a list of items across the x and y axes in a string matrix",
    "@Oiwai juxtranspose { SpaceSeparatedItems }",
    """
        **Juxtranspose Command**
        Takes in a list of words separated by spaces and spreads them out on a 2x2 matrix matching column to row headers
        
        Usage:
        **@Oiwai juxtranspose { Items }**
        
        Examples:
        - Command:
        **@Oiwai juxtranspose A B C**
        
        - Output:
        ```
          | A    | B    | C
        A | A : A | A : B | A : C
        B | B : A | B : B | B : C
        C | C : A | C : B | C : C
        ```
        
    """.trimIndent()
) {
    override suspend fun run(message: Message, args: List<String>): CommandResult {
        val longestArg = args.maxBy { it.length }
        val lal = longestArg.length

        val eSep = " : "
        val cSep = " | "

        val lep = (lal * 2) + eSep.length

        val matrix = Array(args.size) { a -> Array(args.size) { b ->
            val rls = lal - args[a].length
            val rrs = lal - args[b].length
            "${args[a]}${" ".repeat(rls)}${eSep}${args[b]}${" ".repeat(rrs)}"
        }}

        val header = Array<String?>(args.size) { null }

        val builder = StringBuilder()

        for ((i, arg) in args.withIndex()) {
            val rsp = lep - arg.length
            header[i] = "${arg}${" ".repeat(rsp)}"
        }

        builder.append("${" ".repeat(lal)} | ${header.joinToString(separator = cSep)}\n")

        for ((i, entry) in matrix.withIndex()) {
            val ras = lal - args[i].length
            builder.append("${args[i]}${" ".repeat(ras)}${cSep}${entry.joinToString(separator = cSep)}\n")
        }

        sendMessage(message.getChannel(), "```${builder}```")


        return CommandResult(0)
    }

}