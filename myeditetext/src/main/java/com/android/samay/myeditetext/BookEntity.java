package com.android.samay.myeditetext;

import java.io.Serializable;

/**
 * Created by samay on 5/15/15.
 */
public class BookEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private String bookId;
    private String bookName;

    public BookEntity(String bookName,String bookId) {
        super();
        this.bookName=bookName;
        this.bookId=bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
