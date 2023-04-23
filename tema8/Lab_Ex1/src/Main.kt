import chain.CEOHandler
import factory.FactoryProducer

fun main(args: Array<String>) {
    // se creeaza 1xFactoryProducer, 1xEliteFactory, 1xHappyWorkerFactory
    //...

    val factoryProducer = FactoryProducer()
    val eliteFactory = factoryProducer.getFactory("EliteFactory")
    val happyWorkerFactory = factoryProducer.getFactory("HappyWorkerFactory")

    // crearea instantelor (prin intermediul celor 2 fabrici):
    // 2xCEOHandler, 2xExecutiveHandler, 2xManagerHandler, 2xHappyWorkerHandler
    //...

    val CEOHandler1 = eliteFactory.getHandler("CEOHandler")
    val CEOHandler2 = eliteFactory.getHandler("CEOHandler")
    val ManagerHandler1 = eliteFactory.getHandler("ManagerHandler")
    val ManagerHandler2 = eliteFactory.getHandler("ManagerHandler")
    val ExecutiveHandler1 = eliteFactory.getHandler("ExecutiveHandler")
    val ExecutiveHandler2 = eliteFactory.getHandler("ExecutiveHandler")
    val HappyWorkerHandler1 = happyWorkerFactory.getHandler("HappyWorkerHandler")
    val HappyWorkerHandler2 = happyWorkerFactory.getHandler("HappyWorkerHandler")

    // se construieste lantul (se verifica intai diagrama de obiecte si se realizeaza legaturile)
    //...

    CEOHandler1.setHandlers(ExecutiveHandler1, CEOHandler2)
    ExecutiveHandler1.setHandlers(ManagerHandler1, ExecutiveHandler2)
    ManagerHandler1.setHandlers(HappyWorkerHandler1, ManagerHandler2)
    HappyWorkerHandler1.setHandlers(null, HappyWorkerHandler2)

    CEOHandler2.setHandlers(CEOHandler1, ExecutiveHandler2)
    ExecutiveHandler2.setHandlers(ExecutiveHandler1, ManagerHandler2)
    ManagerHandler2.setHandlers(ManagerHandler1, HappyWorkerHandler2)
    HappyWorkerHandler2.setHandlers(HappyWorkerHandler1,null)

    // se executa lantul utilizand atat mesaje de prioritate diferita, cat si directii diferite in lant
    //...
    CEOHandler2.handleRequest("down","2:Mesaj 1")
    ExecutiveHandler1.handleRequest("down","3:Mesaj 2")
    ManagerHandler1.handleRequest("up","1:Mesaj 3")
    HappyWorkerHandler2.handleRequest("up","4:Mesaj 4")
}