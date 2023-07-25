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
        
        - Input:
        **@Oiwai juxtranspose A B C**
        
        - Produces:
        ```
          | A    | B    | C
        A | A, A | A, B | A, C
        B | B, A | B, B | B, C
        C | C, A | C, B | C, C
        ```
        
    """.trimIndent()
) {
    override suspend fun run(message: Message, args: List<String>): CommandResult {
        return CommandResult(1, "Not implemented.")
    }

}