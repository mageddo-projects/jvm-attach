#include "jattach_posix.c"

int main() {
    printf("Starting...\n");
    char *args[] = {
            "load",
            "/home/typer/dev/projects/ram-spider/jattach/build/libsimple_agent.so",
            "true"
    };
    printf("array created!\n");
    jattach(17819, 3, args);
    return 0;
}