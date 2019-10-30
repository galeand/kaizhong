package com.sse.kaizhong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sse.kaizhong.uitl.SimpleHttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IocContextTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocContextTest.class);
        System.out.println("容器里面已经注入的bean：");
        printBeans(context);
//        Object testdruid = context.getBean("testDruid");
//        System.out.println(testdruid);
    }

    private void printBeans(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        // Collections类有一个private constructor，抑制默认构造函数，确保不可实例化
//        Collections collections = new Collections();

    }

    @Test
    public void testHttp(){
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=219.136.134.157";

        String res = null;
        try {
             res = SimpleHttpUtil.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);

        String ret = null;
        if (!StringUtils.isEmpty(res)) {
            JSONObject jsonObject = JSON.parseObject(res);
            if (jsonObject.containsKey("data")){
                ret = jsonObject.getString("data");
            }
        }
        System.out.println(ret);
    }

    @Test
    public void testStream(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = {1,5,13,56,23,-3,50,223,-4,-100,999};
        List<Integer> list1 = Arrays.stream(arr).collect(Collectors.toList());



//        list.addAll(Arrays.asList(arr));
//        list.stream().filter();
    }

    @Test
    public void test02(){
        /**
         * List<User> list1 = userList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
         *         System.out.println("==========按照id升序排序==============");
         *         User.printStudents(list1);
         */

        int[] data = {4, 5, 3, 6, 2, 5, 1};

        // int[] 转 List<Integer>
        List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
        // Arrays.stream(arr) 可以替换成IntStream.of(arr)。
        // 1.使用Arrays.stream将int[]转换成IntStream。
        // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
        // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。


        // int[] 转 Integer[]
        Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);
        // 前两步同上，此时是Stream<Integer>。
        // 然后使用Stream的toArray，传入IntFunction<A[]> generator。
        // 这样就可以返回Integer数组。
        // 不然默认是Object[]。

        // List<Integer> 转 Integer[]
        Integer[] integers2 = list1.toArray(new Integer[0]);
        //  调用toArray。传入参数T[] a。这种用法是目前推荐的。
        // List<String>转String[]也同理。


        // List<Integer> 转 int[]
        int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
        // 想要转换成int[]类型，就得先转成IntStream。
        // 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
        // 而IntStream中默认toArray()转成int[]。

        // Integer[] 转 int[]
        int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
        // 思路同上。先将Integer[]转成Stream<Integer>，再转成IntStream。

        // Integer[] 转 List<Integer>
        List<Integer> list2 = Arrays.asList(integers1);
        // 最简单的方式。String[]转List<String>也同理。

        // 同理
        String[] strings1 = {"a", "b", "c"};
        // String[] 转 List<String>
        List<String> list3 = Arrays.asList(strings1);
        // List<String> 转 String[]
        String[] strings2 = list3.toArray(new String[0]);
    }

}
