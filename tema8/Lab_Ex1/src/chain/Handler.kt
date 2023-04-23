package chain

interface Handler {
    fun handleRequest(forwardDirection: String, messageToBeProcessed: String)
    fun setHandlers(handler1: Handler?, handler2: Handler?)
}