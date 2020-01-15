package com.zhd.ultimate.sociology.reflection;

import com.zhd.ultimate.sociology.proxy.UserManager;

import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-15 15:42
 */
public class CompilerTest {
    static final String JAVA_SOURCE_CODE =
            "package com.zhd.ultimate.sociology.reflection;                   "
                    + "import com.zhd.ultimate.sociology.proxy.UserManagerImpl;       "
                    + "public class UserManagerImplProxy extends UserManagerImpl {    "
                    + "    public UserManagerImplProxy() {                            "
                    + "        System.out.println(\"UserManagerImplProxy构建了\");     "
                    + "    }                                                          "
                    + "}                                                              ";

    public static void main(String[] args) throws Exception {
        JavaStringCompiler compiler = new JavaStringCompiler();
        Map<String, byte[]> results = compiler.compile("UserManagerImplProxy.java", JAVA_SOURCE_CODE);
        Class<?> clazz = compiler.loadClass("com.zhd.ultimate.sociology.reflection.UserManagerImplProxy", results);
        // try instance:
        UserManager user = (UserManager) clazz.newInstance();
        user.doWork("lisisisis");
    }
}
