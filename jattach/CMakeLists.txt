cmake_minimum_required(VERSION 3.16)
project(jattach)

set(CMAKE_CXX_STANDARD 14)

#add_subdirectory(${JAVA_HOME}/include/linux)
#add_subdirectory(${JAVA_HOME}/include)

find_package(JNI)

if (JNI_FOUND)
    message(STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message(STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif ()

set(HEADER_BASE "${CMAKE_CURRENT_SOURCE_DIR}/include")
include_directories(${JNI_INCLUDE_DIRS})
include_directories(${HEADER_BASE})

add_compile_definitions(WINDOWS)
add_library(jattach SHARED src/jattach_jni.cpp)
add_library(simple_agent SHARED src/simple_agent.cpp)
add_executable(jattach_main src/jattach_main.cpp)
