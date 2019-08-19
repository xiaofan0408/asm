package com.xiaofan0408;


import com.xiaofan0408.asm.LogVisitor;
import com.xiaofan0408.core.MyClassLoader;
import com.xiaofan0408.test.Test3;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzefan  2019/8/12 14:11
 */
public class App {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassReader cr = new ClassReader(Test3.class.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cr.accept(new LogVisitor(cw), ClassReader.EXPAND_FRAMES);
        byte[] data = cw.toByteArray();
        String path = Test3.class.getResource(Test3.class.getSimpleName() + ".class").getPath();
        File file = new File(path);

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.defineClassForName(Test3.class.getName(),data);
        Object object = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        methods[0].invoke(object,new Object[] {});
    }

}
