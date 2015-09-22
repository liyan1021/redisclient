package com.liyan.redis.common;

import java.io.Serializable;
import java.util.List;

public class Pagination<E> implements Serializable {

    private static final long serialVersionUID = -678664722059756045L;

    private List<E> resultList;
    private Integer pageSize;
    private Integer pageNo;
    private Integer recordCount;

    private Integer pageCount;
    private Integer fromIndex ; 
    private Integer toIndex ; 
    public List<E> getResultList() {
        return resultList;
    }

    public void setResultList(List<E> resultList) {
        this.resultList = resultList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            this.pageSize = 0;
        } else {
            this.pageSize = pageSize < 0 ? 0 : pageSize;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo == null) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo <= 0 ? 1 : pageNo;
        }
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }


    public Integer getPageCount() {
        if (this.pageSize > 0) {
            this.pageCount = this.recordCount / this.pageSize;
            if ((this.recordCount % this.pageSize) > 0) {
                this.pageCount++;
            }
        }

        return this.pageCount;
    }

	public Integer getFromIndex() {
		return getPageSize() * (getPageNo() - 1);
	}

	public Integer getToIndex() {
		return getPageSize()*getPageNo();
	}


}
