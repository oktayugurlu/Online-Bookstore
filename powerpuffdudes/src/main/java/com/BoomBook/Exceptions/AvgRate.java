package com.BoomBook.Exceptions;

import com.BoomBook.Model.BookComment;

import java.util.List;

public class AvgRate {

    public int findAverageRateofBook(List<BookComment> comments) {

        if (comments.size()>0){

            int total = 0;
            for(BookComment comment:comments){

                total += comment.getRate();
            }

            return Math.round(total/(float)comments.size());
        }
        return -1;
    }
}
