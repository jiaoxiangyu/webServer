package com.sample.annontation;

import java.lang.annotation.*;

@Documented //一个简单的Annotations标记注解，表示是否将注解信息添加在java文档中。
@Retention(RetentionPolicy.RUNTIME) // 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Target(ElementType.TYPE)  //用于描述类、接口(包括注解类型) 或enum声明
public @interface WebServlet {
    String name() default ""; //servlet类名 默认全类名
    String urlPatterns(); //对应的URL
}
