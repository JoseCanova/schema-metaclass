package org.nanotek.meta.util;

public record StringPair(String left , String right)
implements DistinctMemberPair<String,String> {

    public static StringPair from(String left , String right){
        return new StringPair(left,right);
    }

}