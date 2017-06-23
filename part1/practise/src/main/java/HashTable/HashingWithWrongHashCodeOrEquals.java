/**
 * @file   HashingWithWrongHashCodeOrEquals.java
 * @author Lyn <lyn1990828@gmail.com>
 * @date   Fri Jun 23 10:00:55 2017
 *
 * @brief  Suppose that you implement a data type 𝙾𝚕𝚢𝚖𝚙𝚒𝚌𝙰𝚝𝚑𝚕𝚎𝚝𝚎 for use in a 𝚓𝚊𝚟𝚊.𝚞𝚝𝚒𝚕.𝙷𝚊𝚜𝚑𝙼𝚊𝚙.

 Describe what happens if you override 𝚑𝚊𝚜𝚑𝙲𝚘𝚍𝚎() but not 𝚎𝚚𝚞𝚊𝚕𝚜().
 Describe what happens if you override 𝚎𝚚𝚞𝚊𝚕𝚜() but not 𝚑𝚊𝚜𝚑𝙲𝚘𝚍𝚎().
 Describe what happens if you override 𝚑𝚊𝚜𝚑𝙲𝚘𝚍𝚎() but implement 𝚙𝚞𝚋𝚕𝚒𝚌 𝚋𝚘𝚘𝚕𝚎𝚊𝚗 𝚎𝚚𝚞𝚊𝚕𝚜(𝙾𝚕𝚢𝚖𝚙𝚒𝚌𝙰𝚝𝚑𝚕𝚎𝚝𝚎 𝚝𝚑𝚊𝚝) instead of 𝚙𝚞𝚋𝚕𝚒𝚌 𝚋𝚘𝚘𝚕𝚎𝚊𝚗 𝚎𝚚𝚞𝚊𝚕𝚜(𝙾𝚋𝚓𝚎𝚌𝚝 𝚝𝚑𝚊𝚝).
 *
 *
 */
// first i think you can insert into hashMap, and you can get element,
// but the speed could be not fast, sometimes may be duplicate if same key has different
// hashCode

// second if the equals is wrong, you put/ get function will be occur error
// put will override some diffent key, get may be get error key

// the same as the first one, if the equals logic is right, otherwise hastMap's put/get
// will occur error

// as i test implement 𝚙𝚞𝚋𝚕𝚒𝚌 𝚋𝚘𝚘𝚕𝚎𝚊𝚗 𝚎𝚚𝚞𝚊𝚕𝚜(𝙾𝚕𝚢𝚖𝚙𝚒𝚌𝙰𝚝𝚑𝚕𝚎𝚝𝚎 𝚝𝚑𝚊𝚝) instead of 𝚙𝚞𝚋𝚕𝚒𝚌 𝚋𝚘𝚘𝚕𝚎𝚊𝚗 𝚎𝚚𝚞𝚊𝚕𝚜(𝙾𝚋𝚓𝚎𝚌𝚝 𝚝𝚑𝚊𝚝) it will error, will always consider two key diffenent

// import java.util.HashMap;
// import java.util.Map;
// import java.util.Iterator;

// public class test{
//     int i;
//     public test(int i) {
//         this.i = i;
//     }

//     // public int hashCode() {
//     //     return this.i % 10;
//     // }
//     public boolean equals(test that) {
//         if (null == that) return false;
//         if (this == that) return true;
//         if (this.getClass() != that.getClass()) return false;
//         if (((test) that).i == this.i) return true;
//         return false;
//     }
//     // public int hashCode() {
//     //     return (int) Math.random();
//     // }

//     public int hashCode() {
//         return 1;
//     }

//     // public boolean equals(test that) {
//     //     return true;
//     // }

//     public static void main(String[] args) {
//         Map st = new HashMap<test, Integer>();
//         for (int i = 0; i < 10; i++) {
//             test test1 = new test(i);
//             st.put(test1,new Integer(i));
//         }
//         Iterator iter = st.entrySet().iterator();
//         while (iter.hasNext()) {
//             Map.Entry entry = (Map.Entry) iter.next();
//             Object key = entry.getKey();
//             Object val = entry.getValue();
//             System.out.println(val);
//         }
//         for (int i = 0; i < 10; i++) {
//             test test1 = new test(i);
//             System.out.println(st.get(test1));
//         }

//         test test1 = new test(1);
//         System.out.println(test1.equals(new test(1)));
//     }
// }
