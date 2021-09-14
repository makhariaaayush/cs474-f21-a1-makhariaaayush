package edu.uic.cs474.f21.a1.solution;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import edu.uic.cs474.f21.a1.DynamicDispatchExplainer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class A1Solution implements DynamicDispatchExplainer {
    @Override
    public Set<String> explain(Map<String, ClassOrInterfaceDeclaration> classes, String receiverType, String methodName, String... argumentTypes) {
        Set<String> ret = new HashSet<>();
        ClassOrInterfaceDeclaration d = classes.get(receiverType);

        if (!d.getMethodsByName(methodName).isEmpty()) {
            ret.add(d.getName().asString());
        }
        else
        {
            while(!d.getExtendedTypes().isEmpty())
            {
                ClassOrInterfaceType d1 = d.getExtendedTypes().get(0);
                d = classes.get(d1.getName().asString());
            }
            ret.add(d.getName().asString());
        }
//            ClassOrInterfaceDeclaration temp = new ClassOrInterfaceDeclaration();
//            if(!d.getExtendedTypes().isEmpty())
//            {
//                ClassOrInterfaceType a = d.getExtendedTypes().get(0);
//                ClassOrInterfaceDeclaration temp = classes.get(a.getName().asString());
//                if(!temp.getExtendedTypes().isEmpty())
//                {
//                    ClassOrInterfaceType a1 = temp.getExtendedTypes().get(0);
//                    ClassOrInterfaceDeclaration temp1 = classes.get(a1.getName().asString());
//                    ret.add(temp1.getName().asString());
//                }
//                ret.add(temp.getName().asString());
//            }
//        }
        return ret;
    }

}



