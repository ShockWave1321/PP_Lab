#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <regex.h>
#include <stdlib.h>

struct mesg_buffer {
    long mesg_type;
    char mesg_text[2000];
} message;

int main()
{
    key_t key;
    int msgid;
    int len;

    msgid = msgget(-1, 0666 | IPC_CREAT);
    len = msgrcv(msgid, &message, sizeof(message), 1, 0);
    if(len == -1)
    {
        printf("Mesaj invalid\n");
        return 0;
    }

    printf("Data Received is : \n%s\n", message.mesg_text);

    int is_html = 0;
    regex_t regex;
    if (regcomp(&regex, "<[a-z]+>.*?</[a-z]+>", REG_EXTENDED) != 0)
        return 0;

    if (regexec(&regex, message.mesg_text, 0, NULL, 0) == 0)
        is_html = 1;

    if(is_html == 1)
        printf("Format HTML\n");
    else
        printf("Nu are format HTML\n");

    msgctl(msgid, IPC_RMID, NULL);
    return 0;
}
