package com.xiaofan0408;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author xuzefan  2019/8/12 10:02
 */
public class AgentPreMain {
    public static class ClassLoggerTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            if (!className.startsWith("com/xiaofan0408")) {
                return classfileBuffer;
            }
            ClassReader cr= null;
            try {
                cr = new ClassReader(className);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            cr.accept(new MyMethodVisitor(cw), ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        }
    }

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        ClassFileTransformer classFileTransformer = new ClassLoggerTransformer();
        instrumentation.addTransformer(classFileTransformer, true);
    }
}
