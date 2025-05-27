// file: `array/arr7.java`
package array;

import java.util.ArrayList;

public class arr7 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {2, 3, 4, 4, 5, 11, 12};

        // dono sorted array ka union banane ke liye function call
        ArrayList<Integer> arrUnion = makeUnionOfArray(arr1, arr2);
        for (int num : arrUnion) {
            System.out.println(num);
        }
    }

    // makeUnionOfArray function do pointer technique ka use karta hai
    private static ArrayList<Integer> makeUnionOfArray(int[] arr1, int[] arr2) {
        ArrayList<Integer> unionArray = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;

        // jab tak dono arrays me elements bache hai tab tak compare karo
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] < arr2[p2]) {
                if (!unionArray.contains(arr1[p1])) {
                    unionArray.add(arr1[p1]);
                }
                p1++;
            } else if (arr1[p1] > arr2[p2]) {
                if (!unionArray.contains(arr2[p2])) {
                    unionArray.add(arr2[p2]);
                }
                p2++;
            } else {
                // dono element equal hain, sirf ek baar add karo
                if (!unionArray.contains(arr1[p1])) {
                    unionArray.add(arr1[p1]);
                }
                p1++;
                p2++;
            }
        }

        // agar arr1 me bache elements hain to add karo
        while (p1 < arr1.length) {
            if (!unionArray.contains(arr1[p1])) {
                unionArray.add(arr1[p1]);
            }
            p1++;
        }
        // agar arr2 me bache elements hain to add karo
        while (p2 < arr2.length) {
            if (!unionArray.contains(arr2[p2])) {
                unionArray.add(arr2[p2]);
            }
            p2++;
        }
        return unionArray;
    }
}
//Explanation (Hindi):
//Do pointers (p1 aur p2) initialize kiye gaye hain jo arr1 aur arr2 ke starting index par hain.
//Jab tak dono arrays me elements available hain, unhe compare karte hain.
//Agar arr1[p1] chhota hai, to us element ko union list me add karte hain (agar pehle se maujood na ho) aur p1 ko increment karte hain.
//Agar arr2[p2] chhota hai, to us element ko union list me add karte hain (duplicate check ke saath) aur p2 ko increment karte hain.
//Agar dono elements equal hain, to ek hi element add kar dete hain aur dono pointers increment kar dete hain.
//Loop ke baad, agar kisi ek array me bache hue elements hon to unko bhi union list me duplicate check karke add karte hain.
//Is tarah, dono sorted arrays ka union mil jata hai bina duplicates ke.