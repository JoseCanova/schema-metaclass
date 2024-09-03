package org.nanotek.meta.util;

public record CharPair(Character left , Character right)
implements DistinctMemberPair<Character , Character> {
    public static CharPair from(Character left , Character right){
        return new CharPair(left,right);
    }
}
