package me.kys.springdeveloper.test;

import java.util.ArrayList;
import java.util.List;
// stream() 이해하기
//stream()은 원본을 건드리지 않고, 결과는 새로 만들어냄
public class test {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        List<Integer> result = new ArrayList<>();
//        for (int i : nums) {
//            System.out.println(i);
//            if(i % 2 == 0){
//                result.add(i*2);
//            }
//        }
        List<Integer> result = nums.stream().filter(n -> n%2==0).map(n -> n*2).toList();
        System.out.println(result);
    }
}
