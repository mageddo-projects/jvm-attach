#include <jvmti.h>

JNIEXPORT jint JNICALL
Agent_OnAttach(JavaVM *vm, char *options, void *reserved) {
    return Agent_OnLoad(vm, options, reserved);
}

JNIEXPORT jint JNICALL
Agent_OnLoad(JavaVM *jvm, char *options, void *reserved) {
    printf("I'm a native Agent!!! (%s)\n", options);
    return JNI_OK;
}