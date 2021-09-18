package edu.uic.cs474.f21.a1.solution;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import edu.uic.cs474.f21.a1.DynamicDispatchExplainer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class A1Solution implements DynamicDispatchExplainer {
    @Override
    public Set<String> explain(Map<String, ClassOrInterfaceDeclaration> classes, String receiverType, String methodName, String... argumentTypes) {
// equal = false
//         hashcode
//         equals java.lang.parser
//         tostring
//
        Set<String> ret = new HashSet<>();
        boolean flag = false;

        ClassOrInterfaceDeclaration d = classes.get(receiverType);
        if (d.getMethodsBySignature(methodName,argumentTypes).isEmpty()) {
//            Using the while loop to traverse through the hierarchy multiple times
            while (d.getMethodsBySignature(methodName, argumentTypes).isEmpty()) {
                ClassOrInterfaceType d1 = d.getExtendedTypes().get(0);
                d = classes.get(d1.getName().asString());
                flag = true;
            }
        }
        if (!d.getMethodsBySignature(methodName,argumentTypes).isEmpty()){
            MethodDeclaration a = d.getMethodsBySignature(methodName, argumentTypes).get(0);
//            To Check Method is Static or Private
            if (a.isStatic() || a.isPrivate()) {
//                Flag condition to check for A and B (Static and Private)
                if (!flag) {
                    ret.add(d.getName().asString());
                }
                return ret;
            }
//            To Check is Method "a" is Abstract and return Empty
            if (a.isAbstract()) {
                return ret;
            }
            ret.add(d.getName().asString());
        }
        return ret;
    }
}






