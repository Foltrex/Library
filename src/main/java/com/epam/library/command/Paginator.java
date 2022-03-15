package com.epam.library.command;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Identifable;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.EntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Paginator {

    public static final String TOTAL_SUM = "totalSum";
    public static final String RECORDS_PER_PAGE = "recordsPerPage";
    public static final String PAGE_NO = "pageNo";

    private final EntityService service;
    private final int recordsPerPage;

    public Paginator(EntityService service, int recordsPerPage) {
        this.service = service;
        this.recordsPerPage = recordsPerPage;
    }

    public void setPaginationParameters(HttpServletRequest req) throws ServiceException {
        int pageNo = findPageNo(req);
        int totalSum = service.calculateNumberOfRows();

        req.setAttribute(RECORDS_PER_PAGE, recordsPerPage);
        req.setAttribute(TOTAL_SUM, totalSum);
        req.setAttribute(PAGE_NO, pageNo);
    }

    public int findPageNo(HttpServletRequest req) {
        int currentPage;
        if (req.getParameter(PAGE_NO) != null) {
            currentPage = Integer.parseInt(req.getParameter(PAGE_NO));
        } else {
            currentPage = 1;
        }

        return currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

}