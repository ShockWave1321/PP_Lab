package Kotlin.Probleme

data class User(val name:String)
{

}
interface Command
{
    fun exec(user:User)
}
class AddUser:Command
{
    override fun exec(user: User)
    {
        println("Adding a new user with name: "+user.name)
    }
}
class EditUser:Command
{
    override fun exec(user: User)
    {
        println("Editing a new user with name: "+user.name)
    }
}
fun main()
{
    var user = User("Kotlin")
    var add = AddUser()
    add.exec(user)

    var edit = EditUser()
    edit.exec(user)
}