package com.owen.lamp_control_module_common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>Title: LoadJarUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/23 9:54
 */
@Slf4j
public class LoadJarUtil {

    public static void loadJar(String jarPath) {
        URL url = null;
        // 获取jar的真实路径，过滤掉标志前缀(本地路径以"local|"标志，远程路径以"remote|"标志，s3路径以"s3|"标志)
        String realJarPath = jarPath.substring(jarPath.indexOf(Constants.FILE_PATH_SPLIT) + 1);
        try{
            // 根据jarPath前缀获取url
            if(jarPath.startsWith(Constants.LOCAL_FILE_PRE)){
                // 加载本地jar，比如：local|/User/xxx/test/demo.jar
                File jarFile = new File(realJarPath);
                url = jarFile.toURI().toURL();
            }else if(jarPath.startsWith(Constants.REMOTE_FILE_PRE)){
                // 加载远程jar，比如：remote|http://192.168.xx.xxx/test/demo.jar
                url = new URL(realJarPath);
            }
        }catch (Exception e){
            log.error("get url exception, jarPath:" + jarPath, e);
        }

        if(null != url){
            // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
            Method method = null;
            try {
                method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            } catch (NoSuchMethodException e) {
                log.error("get method exception, jarPath:" + jarPath, e);
            }

            // 获取方法的访问权限以便写回
            boolean accessible = method.isAccessible();
            try {
                method.setAccessible(true);

                // 获取系统类加载器
                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                method.invoke(classLoader, url);
            } catch (Exception e) {
                log.error("load url to classLoader exception, jarPath:" + jarPath, e);
            } finally {
                method.setAccessible(accessible);
            }
        }
    }
}
