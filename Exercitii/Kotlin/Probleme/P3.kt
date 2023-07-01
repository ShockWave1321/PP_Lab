package Kotlin.Probleme

data class Mail(val to:String,
                var title:String = "",
                var message:String = "",
                var cc: List<String> = listOf(),
                var bcc: List<String> = listOf(),
                var attachments: List<java.io.File> = listOf())
class MailBuilder(var to:String)
{
    private var mail:Mail = Mail(to);
    fun title(title:String):MailBuilder
    {
        mail.title = title
        return this
    }
    fun build():Mail
    {
        return mail
    }

    override fun toString(): String {
        return "MailBuilder(to='$to', mail=$mail)"
    }

}
fun main()
{
    val mail = Mail("one@receipt.nice","hi","How are u")
    val email = MailBuilder("hello@hello.com").title("what's up?").build()
    println(mail)
    println(email)
}

