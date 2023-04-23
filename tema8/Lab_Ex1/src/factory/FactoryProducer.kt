package factory

class FactoryProducer {
    fun getFactory(choice: String): AbstractFactory {
        return when (choice) {
            "EliteFactory" -> EliteFactory()
            "HappyWorkerFactory" -> HappyWorkerFactory()
            else -> throw IllegalArgumentException("Invalid factory type: $choice")
        }
    }
}