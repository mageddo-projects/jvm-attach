#include "jattach_jni.cpp"

int main() {
    printf("Starting...\n");
    char *args[] = {
            "load",
            "C:\\dev\\jvm-attach\\jattach\\cmake-build-debug\\libsimple_agent.dll",
            "true"
    };
    printf("array created!\n");
    jattach(1696, 3, args);
    return 0;
}