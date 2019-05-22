package com.owen.lamp_control_module_common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>Title: ClassUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/19 14:16
 */
@Slf4j
public class ClassUtil {

    /**
     * 获取类加载器
     *
     * @return 结果 类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类（默认为 false）
     *
     * @param name 类名
     */
    public static Class<?> loadClass(String name) {
        return loadClass(name, false);
    }

    /**
     * 加载类
     *
     * @param className     类名
     * @param isInitialized 是否要初始化
     * @return 结果 类对象
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    /**
     * 获取指定包名下的所有类
     *
     * @param packageName 包名
     * @return 结果 类集合
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urlEnumeration = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while (jarEntryEnumeration.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
                                                .replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    /**
     * 添加类到集合中
     *
     * @param classSet    类设置
     * @param packagePath 包路径
     * @param packageName 报名称
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(
                (file) -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory()
        );
        for (File file : Objects.requireNonNull(files)) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 添加类到集合中并加载
     *
     * @param classSet  类集合
     * @param className 类名称
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> clazz = loadClass(className, false);
        classSet.add(clazz);
    }

//    public static Set<Map.Entry<Object,Object>> getManifest

}
