package com.oops;

import java.util.Queue;

public class Index {
    private int index_no;
    private int vacancy;
    private Session lesson[];
    private Queue<Index> waitingList;
    private int courseId;
    public Index(int courseId, boolean hasLab, boolean hasTut){
        this.courseId = courseId;

    }

    public int Waiting(){
        int wait_no = 0;
        return wait_no;
    }

    public int getVacancy(){
        return vacancy;
    }
}
