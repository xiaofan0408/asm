package com.xiaofan0408;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author xuzefan  2019/8/9 16:03
 */
public class MyMethodAdapter extends AdviceAdapter {

    private String methodName;

    public MyMethodAdapter(int api, MethodVisitor mv, int access, String methodName, String desc) {
        super(api, mv, access, methodName, desc);
        this.methodName = methodName;
    }

    @Override
    protected void onMethodEnter() {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("CALL " + methodName);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    protected void onMethodExit(int i) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("RETURN " + methodName);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
