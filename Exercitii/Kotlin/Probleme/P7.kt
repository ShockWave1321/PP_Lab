package Kotlin.Probleme

interface ChristmasTree {
    fun decorate(): String
}
class PineChristmasTree : ChristmasTree {

    override fun decorate() = "Christmas tree"
}
abstract class TreeDecorator
    (private val tree: ChristmasTree) : ChristmasTree {

    override fun decorate(): String {
        return tree.decorate()
    }
}
class BubbleLights(tree: ChristmasTree) : TreeDecorator(tree) {

    override fun decorate(): String {
        return super.decorate() + decorateWithBubbleLights()
    }

    private fun decorateWithBubbleLights(): String {
        return " with Bubble Lights"
    }
}
fun christmasTreeWithBubbleLights() {

    val christmasTree = BubbleLights(PineChristmasTree())
    val decoratedChristmasTree = christmasTree.decorate()
    println(decoratedChristmasTree)
}
class Garlands(private val tree: ChristmasTree) : ChristmasTree by tree {

    override fun decorate(): String {
        return tree.decorate() + decorateWithGarlands()
    }

    private fun decorateWithGarlands(): String {
        return " with Garlands"
    }
}
fun christmasTreeWithGarlands() {

    val christmasTree = Garlands(PineChristmasTree())
    val decoratedChristmasTree = christmasTree.decorate()
    println(decoratedChristmasTree)
}
fun main()
{
    christmasTreeWithGarlands()
}