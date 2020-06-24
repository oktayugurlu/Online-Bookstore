package com.BoomBook.Exceptions;

public class CommentChecker {
    private boolean isMakeComment=false;
    private boolean isMakePurchase=false;

    public boolean isMakeComment() {
        return isMakeComment;
    }

    public void setMakeComment(boolean makeComment) {
        isMakeComment = makeComment;
    }

    public boolean isMakePurchase() {
        return isMakePurchase;
    }

    public void setMakePurchase(boolean makePurchase) {
        isMakePurchase = makePurchase;
    }
}
