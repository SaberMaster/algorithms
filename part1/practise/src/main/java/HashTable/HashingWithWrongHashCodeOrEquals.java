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
// but the speed could be not fast

// second if the equals is wrong, you put/ get function will be false
// put will override some diffent key, get may be get error key

// the same as the first one, if the equals logic is right, otherwise hastMap's put/get
// will occur error
