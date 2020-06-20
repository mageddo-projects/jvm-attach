#ifdef LINUX
    #include "jattach_posix.c"
#elif defined(WINDOWS)
    #include "jattach_windows.c"
#else
    #error OS not supported
#endif

#include <jvmti.h>
#include "jattach_jni.h"

JNIEXPORT jint JNICALL
Java_com_mageddo_jvm_attach_Jattach_load(JNIEnv *env, jclass thisClass, jint pid, jstring path, jstring options) {
    char *cPath = (char *) env->GetStringUTFChars(path, 0);
    char *cOptions = (char *) env->GetStringUTFChars(options, 0);
    char *args[] = {
            (char *) "load",
            cPath,
            (char *) "true",
            cOptions
    };
    int r = jattach(pid, 4, args);
    env->ReleaseStringUTFChars(path, cPath);
    env->ReleaseStringUTFChars(options, cOptions);
    return r;
}

JNIEXPORT jint JNICALL
Java_com_mageddo_jvm_attach_Jattach_loadLibrary(JNIEnv *env, jclass, jint pid, jstring path, jstring options){
    char *cPath = (char *) env->GetStringUTFChars(path, 0);
    char *cOptions = (char *) env->GetStringUTFChars(options, 0);
    char *args[] = {
            (char *) "load",
            cPath,
            (char *) "false"
    };
    int r = jattach(pid, 3, args);
    env->ReleaseStringUTFChars(path, cPath);
    env->ReleaseStringUTFChars(options, cOptions);
    return r;
}

JNIEXPORT jint JNICALL
Java_com_mageddo_jvm_attach_Jattach_attach(JNIEnv *env, jclass, jint pid, jint argc, jobjectArray javaArgs) {
    int stringCount = env->GetArrayLength(javaArgs);
    char *args[stringCount];
    for (int i = 0; i < stringCount; i++) {
        jstring javaStr = (jstring) env->GetObjectArrayElement(javaArgs, i);
        args[i] = (char *) env->GetStringUTFChars(javaStr, 0);
    }
    int r = jattach(pid, argc, args);
    for (int j = 0; j < stringCount; ++j) {
        env->ReleaseStringUTFChars((jstring) env->GetObjectArrayElement(javaArgs, j), args[j]);
    }
    return r;
}

JNIEXPORT jint JNICALL
Java_com_mageddo_jvm_attach_Jattach_loadJar(JNIEnv *env, jclass, jint pid, jstring path) {
    char *cPath = (char *) env->GetStringUTFChars(path, 0);
    char *args[] = {
            (char *) "load",
            (char *) "instrument",
            (char *) "false",
            cPath,
    };
    int r = jattach(pid, 4, args);
    env->ReleaseStringUTFChars(path, cPath);
    return r;
}