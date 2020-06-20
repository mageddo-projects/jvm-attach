#include <jvmti.h>
#include <iostream>

JNIEXPORT jint JNICALL
Agent_OnAttach(JavaVM *vm, char *options, void *reserved) {
    return Agent_OnLoad(vm, options, reserved);
}

JNIEXPORT jint JNICALL
Agent_OnLoad(JavaVM *jvm, char *options, void *reserved) {
    std::cout << "I'm a native Agent!!!\n";
    return JNI_OK;
}