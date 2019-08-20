package com.xiaofan0408;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author xuzefan  2019/8/9 16:02
 */
public class MyMethodVisitor extends ClassVisitor implements Opcodes {

    public MyMethodVisitor(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//        return mv == null ? null : new MethodAdapter(mv);
        return new MyMethodAdapter(Opcodes.ASM5,mv,access,name,desc);
    }
}

