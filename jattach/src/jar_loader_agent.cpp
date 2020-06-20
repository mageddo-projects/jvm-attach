#include <jvmti.h>
#include <cstring>
#include <iostream>

void debug(char* msg...){
//    printf(msg);
}

int loadJar(JNIEnv *jni){

    debug("loading jar...\n");
    const char* javaNetUrlClassName = "java/net/URL";
    char* jarPath = (char*) "file://hello-world-library.jar";
    jclass urlClass = jni->FindClass(javaNetUrlClassName);
    if(urlClass == NULL){
        char msg[50];
        sprintf(msg, "couldn't find class: %s\n", javaNetUrlClassName);
        jni->FatalError((char *) msg);
    }
    debug("urlClass created\n");

    jmethodID urlConstructor = jni->GetMethodID(urlClass, "<init>", "(Ljava/lang/String;)V");
    jstring jarPathJString = jni->NewStringUTF(jarPath);
    jobject url = jni->NewObject(urlClass, urlConstructor, jarPathJString);
    jni->ReleaseStringUTFChars(jarPathJString, NULL);
    if(url == NULL){
        jni->FatalError("Couldn't create URL object");
    }
    debug("jar url created\n");

    jobjectArray urls = jni->NewObjectArray(1, jni->FindClass(javaNetUrlClassName), NULL);
    jni->SetObjectArrayElement(urls, 0, url);

    jclass classLoaderClass = jni->FindClass("java/net/URLClassLoader");
    jmethodID constructor = jni->GetMethodID(classLoaderClass, "<init>", "([Ljava/net/URL;)V");
    jobject classLoader = jni->NewObject(classLoaderClass, constructor, urls);
    debug("classloader created \n");

    jclass clazz = jni->FindClass("java/lang/Class");
    jmethodID classForNameMethod = jni->GetStaticMethodID(clazz, "forName", "(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;");
    if(classForNameMethod == NULL){
        jni->FatalError("Class.forName(String,boolean,java.lang.ClassLoader)java.lang.Class method not found\n");
    }

    char *jarInitClass = "samepackage.Calculator";
    jstring classToLoad = jni->NewStringUTF(jarInitClass);
    jclass jarClass = (jclass) jni->CallStaticObjectMethod(clazz, classForNameMethod, classToLoad, true, classLoader);
    jni->ReleaseStringUTFChars(classToLoad, NULL);
    if(jarClass == NULL){
        printf("jarClass found %s\n", jarClass);
        char msg[100];
        sprintf(msg, "jar init class not found: %s\n", jarInitClass);
        jni->FatalError((char *) msg);
    }

    jmethodID mainMethod = jni->GetStaticMethodID(jarClass, "sum", "(II)I");
    jint result = jni->CallStaticIntMethod(jarClass, mainMethod, 15, 8);
    printf("loaded jar and executed: %d\n", result);

}

void JNICALL vmInitCallback(jvmtiEnv *jvmtiEnv, JNIEnv* jniEnv, jthread thread){
    printf("callback called \n");
    loadJar(jniEnv);
}

JNIEXPORT jint JNICALL
Agent_OnAttach(JavaVM *jvm, char *options, void *reserved) {
    printf("on attach options=%s\n", options);

    jvmtiEnv *jvmti = NULL;
    jint result = jvm->GetEnv((void **) &jvmti, JVMTI_VERSION_1_1);
    if (result != JNI_OK) {
        printf("ERROR: Unable to access JVMTI! %d\n", result);
    }

    JNIEnv *jni;
    jint r = jvm->GetEnv((void **) &jni, JNI_VERSION_1_6);
    if (r != JNI_OK) {
        printf("couldn't get JNI env %d", r);
        return r;
    }
    loadJar(jni);
    return JNI_OK;
}

JNIEXPORT jint JNICALL
Agent_OnLoad(JavaVM *jvm, char *options, void *reserved) {

    printf("on load options=%s\n", options);
    static jvmtiEnv *jvmti = NULL;
    jint result = jvm->GetEnv((void **) &jvmti, JVMTI_VERSION_1_1);
    if (result != JNI_OK) {
        printf("ERROR: Unable to access JVMTI! %d\n", result);
    }

    jvmtiEventCallbacks callbacks;
    memset(&callbacks, 0, sizeof(callbacks));
    callbacks.VMInit = &vmInitCallback;
    jvmti->SetEventCallbacks(&callbacks, sizeof(callbacks));
    jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_VM_INIT, NULL);

    std::cout << "I'm a native Agent!!!\n";
    return JNI_OK;
}