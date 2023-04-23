package factory

import chain.CEOHandler
import chain.ExecutiveHandler
import chain.Handler
import chain.ManagerHandler

class EliteFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        return when (handler) {
            "CEOHandler" -> CEOHandler()
            "ExecutiveHandler" -> ExecutiveHandler()
            "ManagerHandler" -> ManagerHandler()
            else -> throw IllegalArgumentException("Invalid handler type: $handler")
        }
    }

}